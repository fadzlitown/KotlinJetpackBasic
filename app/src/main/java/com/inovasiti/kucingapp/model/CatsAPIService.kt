package com.inovasiti.kucingapp.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CatsAPIService{
    private val BASE_URL ="https://raw.githubusercontent.com"

    //allow to call the endpoint & get response
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
            //Gson google libr use to convert the model
        .addConverterFactory(GsonConverterFactory.create())
        // convert info from Gson into Single reactivex
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CatsApi::class.java)

    //convert a list of our element into
    // single = DisposableSingleObserver  Reactive Pattern for a single value response.
    fun getCats() : Single<List<CatSiam>> {
        return api.getCats()
    }
}