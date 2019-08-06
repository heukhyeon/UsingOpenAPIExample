package kr.evalon.usingopenapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kr.evalon.usingopenapi.aac.MainViewModel
import kr.evalon.usingopenapi.aac.PhotoViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list_main.adapter = viewModel.listAdapter
        list_main.layoutManager = GridLayoutManager(this, 3)
        viewModel.pagedListLiveData.observe(this, Observer<PagedList<PhotoViewModel>> {
            viewModel.listAdapter.submitList(it)
        })
    }


}
