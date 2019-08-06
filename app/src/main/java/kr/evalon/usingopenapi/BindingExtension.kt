package kr.evalon.usingopenapi

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.Transformation


object BindingExtension {

    interface GlideListener {
        fun accept():Transformation<Bitmap>
    }

    @JvmStatic
    @BindingAdapter(value = ["android:src","transform"],requireAll = false)
    fun imageBind(v:ImageView, url:String?, transform:GlideListener?){
        if(url.isNullOrEmpty())
            return
        if(transform == null){
            GlideApp.with(v)
                .load(url)
                .into(v)
            return
        }

        GlideApp.with(v)
            .load(url)
            .transform(transform.accept())
            .into(v)
    }

}