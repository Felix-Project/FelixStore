package com.felix.felixstore.ui.main.hot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.felix.felixstore.R
import com.felix.felixstore.ext.lastUnVisibleCount
import com.felix.felixstore.ui.main.AppListAdp
import com.felix.lib_arch.mvvm.BaseMvvmFragment
import kotlinx.android.synthetic.main.hot_fragment.*

class HotFragment : BaseMvvmFragment<HotViewModel>() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hot_fragment, container, false)
    }

    var appListAdp = AppListAdp()
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rvAppList.layoutManager = LinearLayoutManager(context)
        rvAppList.adapter = appListAdp

        srlRefresh.setOnRefreshListener {
            viewModel.getHotAppList()
        }
        rvAppList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (rvAppList.lastUnVisibleCount <= 5) {
                    viewModel.getNextHotAppList()
                }
            }
        })

        //view model observe
        observe(viewModel.hotAppList) {
            appListAdp.datas = it
            srlRefresh.isRefreshing = false
        }
        viewModel.getHotAppList()

    }
}