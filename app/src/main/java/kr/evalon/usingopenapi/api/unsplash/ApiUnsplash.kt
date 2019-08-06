package kr.evalon.usingopenapi.api.unsplash

import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.Result
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiUnsplash {

    @GET("/photos")
    fun loadPhoto(@Query("page") page:Long = 1):Single<Response<List<ModelUnsplashPhoto>>>

    companion object {

        fun create(): ApiUnsplash {
            return Retrofit.Builder()
                .baseUrl("https://api.unsplash.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(createClient())
                .build()
                .create(ApiUnsplash::class.java)
        }

        private fun createClient():OkHttpClient{
            return OkHttpClient.Builder()

                .addInterceptor {
                    val url = it.request().url.newBuilder()
                        .addQueryParameter("client_id","92448cb1d51196350ff106bc897517a384b8eba59f26e83c188f177ee0b23c1d")
                        .build()

                    val request = it.request().newBuilder()
                        .url(url)
                        .addHeader("Accept-Version","v1")
                        .build()

                    it.proceed(request)
                }
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        }
    }
}