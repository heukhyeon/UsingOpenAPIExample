package kr.evalon.usingopenapi.aac

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kr.evalon.usingopenapi.BR

class BindHolder(@LayoutRes layoutId:Int, parent:ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(layoutId,parent,false)
) {
    private val bind = requireNotNull(DataBindingUtil.bind<ViewDataBinding>(itemView))

    fun setViewModel(data:Any){
        bind.setVariable(BR.viewModel, data)
    }
}