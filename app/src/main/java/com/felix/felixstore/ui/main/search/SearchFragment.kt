package com.felix.felixstore.ui.main.search

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.felix.felixstore.R
import com.felix.felixstore.ui.main.AppListAdp
import com.felix.lib_arch.mvvm.BaseMvvmFragment
import kotlinx.android.synthetic.main.search_fragment.*
import kotlin.math.abs

class SearchFragment : BaseMvvmFragment<SearchViewModel>() {
    var appListAdp = AppListAdp()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
        rvAppList.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            takeIf { abs(scrollY - oldScrollY) > 10 }?.takeIf {
                inputManager.isActive
            }?.let {
                inputManager.hideSoftInputFromWindow(
                    v.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )
                Log.i("hmf", "hide input manager.")
            }
        }
        viewModel.getDefAppList()
    }

    private val inputManager by lazy {
        context?.getSystemService(Context.INPUT_METHOD_SERVICE).let {
            it as InputMethodManager
        }
    }

    private fun searchCurrent() {
        viewModel.getSearch(etKeyword.text.toString())
        appListAdp.datas.clear()
        appListAdp.notifyDataSetChanged()
        takeIf {
            inputManager.isActive
        }?.let {
            inputManager.hideSoftInputFromWindow(
                view?.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
        showLoading("加载中……")
    }
}