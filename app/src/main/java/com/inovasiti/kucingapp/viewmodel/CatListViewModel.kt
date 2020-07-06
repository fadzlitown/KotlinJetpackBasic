package com.inovasiti.kucingapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inovasiti.kucingapp.model.CatSiam
import com.inovasiti.kucingapp.model.CatsAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CatListViewModel : ViewModel(){
    val cats = MutableLiveData<List<CatSiam>>()
    val catLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    private val catsService = CatsAPIService()
    private val disposable = CompositeDisposable()

    fun refresh(){
        val cat1 = CatSiam("1","Hitam Siam", "5 Tahun", "Siam Group",
            "Bred siam","","")

        val cat2 = CatSiam("2","Putih Siam", "7 Tahun", "Siam Group",
            "Bred siam","","")

        val cat3 = CatSiam("2","Tompok Siam", "3 Tahun", "Siam Group",
            "Bred siam","","")

        val catList = arrayListOf<CatSiam>(cat1,cat2,cat3)

        cats.value = catList
        catLoadError.value = false
        loading.value = false
    }

    fun fetchFromRemote(){
        loading.value = true
        disposable.add(
            catsService.getCats()
                    //calling API from bg thread
                .subscribeOn(Schedulers.newThread())
                    //after get the response it will process on Main Thread (UI thread)
                .observeOn(AndroidSchedulers.mainThread())
                    //DisposableSingleObserver will get a list of cats
                .subscribeWith(object : DisposableSingleObserver<List<CatSiam>>(){
                    override fun onSuccess(catList: List<CatSiam>) {
                        cats.value = catList
                        catLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        catLoadError.value = true
                        loading.value = false
                    e.printStackTrace()
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}