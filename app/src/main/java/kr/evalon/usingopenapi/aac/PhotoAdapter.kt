package kr.evalon.usingopenapi.aac

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.evalon.usingopenapi.R

class PhotoAdapter : PagedListAdapter<PhotoViewModel, BindHolder>(PhotoViewModel.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindHolder {
        return BindHolder(R.layout.item_photo, parent)
    }

    override fun onBindViewHolder(holder: BindHolder, position: Int) {
        holder.setViewModel(
            requireNotNull(getItem(position))
        )
    }
}