package com.inovasiti.kucingapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inovasiti.kucingapp.model.CatDatabase
import com.inovasiti.kucingapp.model.CatSiam
import kotlinx.coroutines.launch

class CatDetailViewModel(app: Application) : BaseViewModel(app) {
    val catLiveData = MutableLiveData<CatSiam>()

    fun addCat(){
        val cat1 = CatSiam("1","Hitam Siam", "5 Tahun", "Siam Group",
            "Bred siam","asdadas","R.drawable.ic_launcher_background")
        catLiveData.value = cat1
    }

    //retrieve the cat fro db
     fun fetchFromDBByUid(uuid : Long){
        launch {
            val cat = CatDatabase(getApplication()).catDao().getCat(uuid);
            catLiveData.value = cat
        }
    }


}