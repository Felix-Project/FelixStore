package com.felix.felixstore.ui.main.search

import android.content.Context.INPUT_METHOD_SERVICE
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.felix.felixstore.R
import com.felix.felixstore.ui.detail.DetailActivity
import com.felix.lib_arch.mvpvm.AbsBaseViewDelegate
import com.felix.felixstore.ui.main.AppListAdp
import kotlinx.android.synthetic.main.search_fragment.*


/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/12
 * @Des: HomveViewHelper
 */
class SearchDelegate : AbsBaseViewDelegate<SearchPresenterImpl, SearchViewModel>(), SearchView {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    var appListAdp = AppListAdp()
    override fun onActivityCreated() {
        super.onActivityCreated()

        rvAppList.layoutManager = LinearLayoutManager(context)
        rvAppList.adapter = appListAdp

        etKeyword.setOnEditorActionListener { v, actionId, event ->
            arrayOf(
                EditorInfo.IME_ACTION_SEARCH,
                EditorInfo.IME_ACTION_DONE,
                EditorInfo.IME_ACTION_UNSPECIFIED
            ).takeIf { actionId in it }?.let {
                searchCurrent()
            }
            true
        }
        etKeyword.setOnClickListener {
            etKeyword.selectAll()
        }

        //view model observe
        observe(viewModel.hotAppList) {
            appListAdp.datas = it
            dismissLoading()
        }
        appListAdp.onItemClickListener = { view, appItem, position, size ->
//            ToastDelegate.show("点击了详情")
//            val uri: Uri = Uri.parse(AppUrl + appItem.appDetailUrl)
//            val intent = Intent(Intent.ACTION_VIEW, uri)
//            context.packageManager.getInstalledPackages(0)?.any {
//                AdmPkg == it.packageName
//            }?.takeIf {
//                it
//            }?.let {
//                intent.setPackage(AdmPkg)
//            }
//            context.startActivity(intent)
            DetailActivity.start(context, appItem)
        }
        rvAppList.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            takeIf { Math.abs(scrollY - oldScrollY) > 10 }?.takeIf {
                inputManager.isActive
            }?.let {
                inputManager.hideSoftInputFromWindow(
                    v.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
                Log.i("hmf", "hide input manager.")
            }
        }
    }

    val inputManager by lazy {
        context.getSystemService(INPUT_METHOD_SERVICE).let {
            it as InputMethodManager
        }
    }

    private fun searchCurrent() {
        presenter.getSearch(etKeyword.text.toString())
        appListAdp.datas.clear()
        appListAdp.notifyDataSetChanged()
//        etKeyword.focusable= NOT_FOCUSABLE
//        rvAppList.requestFocus()
        takeIf {
            inputManager.isActive
        }?.let {
            inputManager.hideSoftInputFromWindow(
                containerView.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
        showLoading("加载中……")
    }
}