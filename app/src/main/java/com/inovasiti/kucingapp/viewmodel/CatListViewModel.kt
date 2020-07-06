package com.inovasiti.kucingapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inovasiti.kucingapp.SharePrefHelper
import com.inovasiti.kucingapp.model.CatDatabase
import com.inovasiti.kucingapp.model.CatSiam
import com.inovasiti.kucingapp.model.CatsAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class CatListViewModel(app: Application) : BaseViewModel(app) {
    private var prefHelper = SharePrefHelper(getApplication())

    //cache time: get info from backend after 5 min only, otherwise  use from local data
    private var refreshTime = 5 * 60 * 1000 * 1000 * 1000L

    val cats = MutableLiveData<List<CatSiam>>()
    val catLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    private val catsService = CatsAPIService()
    private val disposable = CompositeDisposable()

    fun refresh() {
        val updateTime: Long? = prefHelper.getUpdateTime()

        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime)
            fetchFromDB()
        else
            fetchFromRemote()
    }

    fun pullToRefreshFetchRemote(){
        fetchFromRemote()
    }

    private fun fetchFromDB() {
        //anything which need to call the DB, need to call in bg thread /  CoroutineScope
        loading.value = true
        launch {
            val cats = CatDatabase(getApplication()).catDao().getAllCats()
            catsRetrieved(cats)
            Toast.makeText(getApplication(),"Cats Dah dapat!", Toast.LENGTH_LONG).show()
        }
    }

    fun fetchFromRemote() {
        loading.value = true
        disposable.add(
            catsService.getCats()
                //calling API from bg thread
                .subscribeOn(Schedulers.newThread())
                //after get the response it will process on Main Thread (UI thread)
                .observeOn(AndroidSchedulers.mainThread())
                //DisposableSingleObserver will get a list of cats
                .subscribeWith(object : DisposableSingleObserver<List<CatSiam>>() {
                    override fun onSuccess(catList: List<CatSiam>) {
                        storeCatsLocally(catList)
                        Toast.makeText(getApplication(),"Cats Dah dapat from API", Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        catLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun catsRetrieved(catsList: List<CatSiam>) {
        cats.value = catsList
        catLoadError.value = false
        loading.value = false
    }

    private fun storeCatsLocally(list: List<CatSiam>) {
        //Android db not allow to be access in UI thread, coz  will block our UI user
        //handle this by using Coroutine

        // this launch, will run these in Coroutine on seperate thread
        launch {
            val dao = CatDatabase(getApplication()).catDao()
            dao.deleteAllCats()

            //* will get a list & containing all of the elements of this cats
            // & can pass into CatSiam to get new uuid
            val result = dao.addAll(*list.toTypedArray())
            var i = 0
            while (i < list.size) {
                list[i].uuid = result[i].toLong()
                ++i
            }
            catsRetrieved(list)
        }
        prefHelper.saveUpdateTime(System.nanoTime())
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}