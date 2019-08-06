package kr.evalon.usingopenapi.aac

import android.util.Log
import androidx.paging.PageKeyedDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kr.evalon.usingopenapi.api.ApiService
import kr.evalon.usingopenapi.api.unsplash.ModelUnsplashPhoto
import java.io.Closeable


class PhotoDataSource : PageKeyedDataSource<Long, PhotoViewModel>(), Closeable {

    private val networkDisposable = CompositeDisposable()

    override fun loadInitial(params: PageKeyedDataSource.LoadInitialParams<Long>,
                             callback: PageKeyedDataSource.LoadInitialCallback<Long, PhotoViewModel>) {

        ApiService.unSplash().loadPhoto()
            .subscribe({
                callback.onResult(it.body()!!.map(::PhotoViewModel),0,it.headers()["X-Total"]!!.toInt(),1,2)
            },{

            })
            .addTo(networkDisposable)

    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, PhotoViewModel>) {

    }

    override fun loadAfter(params: PageKeyedDataSource.LoadParams<Long>, callback: PageKeyedDataSource.LoadCallback<Long, PhotoViewModel>) {

        ApiService.unSplash().loadPhoto(params.key)
            .subscribe({
                callback.onResult(it.body()!!.map(::PhotoViewModel),params.key + 1)
            },{

            })
            .addTo(networkDisposable)
    }

    override fun close() {
        networkDisposable.dispose()
    }
}
