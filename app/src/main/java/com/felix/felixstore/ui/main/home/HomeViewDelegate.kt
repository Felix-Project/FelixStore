package com.felix.felixstore.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.felix.felixstore.R
import com.felix.felixstore.mvpvm.AbsBaseViewDelegate
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/12
 * @Des: HomveViewHelper
 */
class HomeViewDelegate : AbsBaseViewDelegate<HomeViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //view model observe
        observe(viewModel.text) {
            text_home.text = it
        }

        observe(viewModel.ch) {
            text_home.text = text_home.text.toString() + "    " + it
        }
        //view event
        text_home.setOnClickListener {
            viewModel.text.takeIf { it is MutableLiveData }?.let {
                it as MutableLiveData
            }?.let {
                it.postValue("heheh")
            }
        }

    }
}