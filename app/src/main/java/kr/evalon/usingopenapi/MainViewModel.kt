package kr.evalon.usingopenapi

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.PagedList
import kr.evalon.usingopenapi.aac.PhotoFactory
import androidx.paging.LivePagedListBuilder
import kr.evalon.usingopenapi.api.unsplash.ModelUnsplashPhoto
import java.util.concurrent.Executors
import android.provider.Contacts.Photos
import androidx.lifecycle.LiveData




class MainViewModel(app:Application) : AndroidViewModel(app) {

    private val factory = PhotoFactory()
    private val pagedListLiveData: LiveData<PagedList<Photos>>? = null

    init {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize(20)
            .setPrefetchDistance(4)
            .build()
        LivePagedListBuilder<Long, ModelUnsplashPhoto>(factory, config)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()
    }
}