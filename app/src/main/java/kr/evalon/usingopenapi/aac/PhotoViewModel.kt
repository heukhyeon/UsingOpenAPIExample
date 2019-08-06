package kr.evalon.usingopenapi.aac

import android.graphics.Bitmap
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import kr.evalon.usingopenapi.api.unsplash.ModelUnsplashPhoto

class PhotoViewModel(private val model : ModelUnsplashPhoto) {

    val photoUrl:String = model.urls.regular
    val userPhotoUrl:String = model.user.profile_image.medium
    val userName = model.user.name

    fun createUserPhotoTransformation():Transformation<Bitmap>{
        return CircleCrop()
    }

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