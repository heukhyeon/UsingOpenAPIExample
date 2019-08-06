package kr.evalon.usingopenapi.aac

import androidx.paging.PageKeyedDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kr.evalon.usingopenapi.api.ApiService
import kr.evalon.usingopenapi.api.unsplash.ModelUnsplashPhoto
import java.io.Closeable


class PhotoDataSource : PageKeyedDataSource<Long, ModelUnsplashPhoto>(), Closeable {

    private val networkDisposable = CompositeDisposable()

    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<Long>,
                             callback: PageKeyedDataSource.LoadInitialCallback<Long, ModelUnsplashPhoto>) {

        ApiService.unSplash().loadPhoto()
            .subscribe({
                callback.onResult(it.body()!!,0,it.headers()["X-Total"]!!.toInt(),null,null)
            },{

            })
            .addTo(networkDisposable)

    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, ModelUnsplashPhoto>) {

    }

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<Long>, callback: PageKeyedDataSource.LoadCallback<Long, ModelUnsplashPhoto>) {

        ApiService.unSplash().loadPhoto()
            .subscribe({
                callback.onResult(it.body()!!,params.key + 1)
            },{

            })
            .addTo(networkDisposable)
    }

    override fun close() {
        networkDisposable.dispose()
    }
}
