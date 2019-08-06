package kr.evalon.usingopenapi.aac

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kr.evalon.usingopenapi.api.ApiService
import kr.evalon.usingopenapi.api.unsplash.LimitOverException
import kr.evalon.usingopenapi.api.unsplash.ModelUnsplashPhoto
import retrofit2.HttpException
import retrofit2.Response
import java.io.Closeable
import java.lang.Exception


class PhotoDataSource(private val networkDisposable:CompositeDisposable,
                      private val exceptionLiveData :MutableLiveData<Throwable>) :
        PageKeyedDataSource<Long, PhotoViewModel>() {

    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<Long>,
                             callback: PageKeyedDataSource.LoadInitialCallback<Long, PhotoViewModel>) {

        ApiService.unSplash().loadPhoto()
                .handlingHttpException()
                .subscribe({
                    callback.onResult(it.body()!!.map(::PhotoViewModel), 0, it.headers()["X-Total"]!!.toInt(), 1, 2)
                }, {
                    if(it is LimitOverException){
                        callback.onResult(emptyList(), 0, 0, null, null)
                    }

                    exceptionLiveData.postValue(it)
                })
                .addTo(networkDisposable)

    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, PhotoViewModel>) {

    }

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<Long>, callback: PageKeyedDataSource.LoadCallback<Long, PhotoViewModel>) {

        ApiService.unSplash().loadPhoto(params.key)
                .handlingHttpException()
                .subscribe({
                    callback.onResult(it.body()!!.map(::PhotoViewModel), params.key + 1)
                }, {
                    exceptionLiveData.postValue(it)
                    if(it is LimitOverException)
                        callback.onResult(emptyList(), null)
                })
                .addTo(networkDisposable)
    }

    private fun Single<Response<List<ModelUnsplashPhoto>>>.handlingHttpException(): Single<Response<List<ModelUnsplashPhoto>>> {

        return flatMap {
            if(!it.isSuccessful)
                Single.error(HttpException(it))
            else
                Single.just(it)
        }
                .onErrorResumeNext { e:Throwable->
            if(e !is HttpException || e.code() != 403)
                return@onErrorResumeNext Single.error(e)

            Single.error(LimitOverException())
        }
    }

}
