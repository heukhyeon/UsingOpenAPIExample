package kr.evalon.usingopenapi.aac

import androidx.paging.DataSource

class PhotoFactory : DataSource.Factory<Long, PhotoViewModel>() {

    override fun create(): DataSource<Long, PhotoViewModel> {
        return PhotoDataSource()
    }
}