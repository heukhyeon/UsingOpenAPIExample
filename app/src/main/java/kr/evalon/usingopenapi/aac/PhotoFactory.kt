package kr.evalon.usingopenapi.aac

import androidx.paging.DataSource
import io.reactivex.subjects.PublishSubject
import kr.evalon.usingopenapi.api.unsplash.ModelUnsplashPhoto

class PhotoFactory : DataSource.Factory<Long, PhotoViewModel>() {

    val subject = PublishSubject.create<PhotoDataSource>()

    override fun create(): DataSource<Long, PhotoViewModel> {
        val data = PhotoDataSource()
        subject.onNext(data)
        return data
    }
}