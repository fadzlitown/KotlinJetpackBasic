package com.inovasiti.kucingapp.model

import io.reactivex.Single
import retrofit2.http.GET

interface CatsApi {
    @GET("DevTides/DogsApi/master/dogs.json")
    fun getCats() : Single<List<CatSiam>>


}