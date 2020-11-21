package com.felix.lib_arch.mvvm

import android.app.ProgressDialog
import android.content.Context
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment(), IloadDialog,
    ITAG {
    override var ctx: Context? = null
        get() = context
    override var dialog: ProgressDialog? = null
    override fun onDestroyView() {
        super.onDestroyView()
        dismissLoading()
    }
}