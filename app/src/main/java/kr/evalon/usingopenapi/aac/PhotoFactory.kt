package kr.evalon.usingopenapi.aac

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import io.reactivex.disposables.CompositeDisposable

class PhotoFactory : DataSource.Factory<Long, PhotoViewModel>() {

    val disposables = CompositeDisposable()
    val exceptionLiveData = MutableLiveData<Throwable>()
    private val source = PhotoDataSource(disposables, exceptionLiveData)

    fun getSource() = source

    override fun create(): DataSource<Long, PhotoViewModel> {
        return source
    }
}