package com.inovasiti.kucingapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inovasiti.kucingapp.model.CatSiam

class CatDetailViewModel : ViewModel() {
    val catLiveData = MutableLiveData<CatSiam>()

    fun addCat(){
        val cat1 = CatSiam("1","Hitam Siam", "5 Tahun", "Siam Group",
            "Bred siam","asdadas","R.drawable.ic_launcher_background")
        catLiveData.value = cat1
    }
}