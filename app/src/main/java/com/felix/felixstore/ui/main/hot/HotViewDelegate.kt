package com.felix.felixstore.ui.main.hot

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.felix.felixstore.R
import com.felix.lib_arch.mvpvm.AbsBaseViewDelegate
import com.felix.felixstore.ext.lastUnVisibleCount
import com.felix.felixstore.ui.main.AppListAdp
import com.felix.lib_store.base.util.AdmPkg
import com.felix.lib_store.base.util.AppUrl
import kotlinx.android.synthetic.main.hot_fragment.*


/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/12
 * @Des: HomveViewHelper
 */
class HotViewDelegate : AbsBaseViewDelegate<HotPresenterImpl, HotViewModel>(), HotView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): View? {
        return inflater.inflate(R.layout.hot_fragment, container, false)
    }

    var appListAdp = AppListAdp()
    override fun onActivityCreated() {
        super.onActivityCreated()

        rvAppList.layoutManager = LinearLayoutManager(context)
        rvAppList.adapter = appListAdp

        srlRefresh.setOnRefreshListener {
            presenter.getHotAppList()
        }
        rvAppList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (rvAppList.lastUnVisibleCount <= 5) {
                    presenter.getNextHotAppList()
                }
            }
        })

        //view model observe
        observe(viewModel.hotAppList) {
            appListAdp.datas = it
        }
        appListAdp.onItemClickListener = { view, appItem, position, size ->
//            ToastDelegate.show("点击了详情")
            val uri: Uri = Uri.parse(AppUrl + appItem.appDetailUrl)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.packageManager.getInstalledPackages(0)?.any {
                AdmPkg == it.packageName
            }?.takeIf {
                it
            }?.let {
                intent.setPackage(AdmPkg)
            }
            context.startActivity(intent)
        }
    }

    override fun onPageLoad(currentPage: Int, allPage: Int) {
        srlRefresh.isRefreshing = false
    }

    override fun onloadError(throwable: Throwable) {
        srlRefresh.isRefreshing = false
    }

}