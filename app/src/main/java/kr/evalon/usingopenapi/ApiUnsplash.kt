package kr.evalon.usingopenapi

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiUnsplash {

    @GET("/me")
    fun loadProfile(): Single<Any>

    companion object {

        fun create():ApiUnsplash{
            return Retrofit.Builder()
                .baseUrl("https://api.unsplash.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiUnsplash::class.java)
        }
    }
}