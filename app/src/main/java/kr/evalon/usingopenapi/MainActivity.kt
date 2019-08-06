package kr.evalon.usingopenapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kr.evalon.usingopenapi.aac.MainViewModel
import kr.evalon.usingopenapi.aac.PhotoViewModel
import kr.evalon.usingopenapi.api.unsplash.LimitOverException

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
        viewModel.factory.exceptionLiveData.observe(this, Observer { e ->
            val msg =
            if(e is LimitOverException)
                "사용 횟수를 초과했습니다. 잠시후 다시 시도해주세요"
            else
                "에러가 발생했습니다 : ${e.message}"

            AlertDialog.Builder(this)
                    .setTitle("통신 오류")
                    .setMessage(msg)
                    .setOnDismissListener {
                        if(e !is LimitOverException)
                            viewModel.factory.getSource().invalidate()
                    }
                    .show()

        })
    }

    override fun onDestroy() {
        viewModel.factory.disposables.clear()
        super.onDestroy()
    }
}
