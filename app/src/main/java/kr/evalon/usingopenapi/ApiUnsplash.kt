package kr.evalon.usingopenapi

import retrofit2.Retrofit
import retrofit2.http.GET

interface ApiUnsplash {

    @GET("/me")
    fun loadProfile():Any

    companion object {

        fun create():ApiUnsplash{
            return Retrofit.Builder()
                .baseUrl("https://api.unsplash.com/")
                .build()
                .create(ApiUnsplash::class.java)
        }
    }
}