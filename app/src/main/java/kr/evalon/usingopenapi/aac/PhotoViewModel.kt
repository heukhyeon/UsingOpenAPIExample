package kr.evalon.usingopenapi.aac

import androidx.recyclerview.widget.DiffUtil
import kr.evalon.usingopenapi.api.unsplash.ModelUnsplashPhoto

class PhotoViewModel(private val model : ModelUnsplashPhoto) {

    companion object {
        val DIFF_CALLBACK by lazy {
            object : DiffUtil.ItemCallback<PhotoViewModel>() {
                override fun areItemsTheSame(oldItem: PhotoViewModel, newItem: PhotoViewModel): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: PhotoViewModel, newItem: PhotoViewModel): Boolean {
                    return true
                }
            }
        }
    }
}