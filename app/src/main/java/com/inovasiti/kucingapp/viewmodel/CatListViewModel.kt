package com.inovasiti.kucingapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inovasiti.kucingapp.model.CatSiam

class CatListViewModel : ViewModel(){
    val cats = MutableLiveData<List<CatSiam>>()
    val catLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

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
}