package com.felix.lib_arch.mvpvm

import android.app.ProgressDialog
import android.content.Context

/**
 * @Author: Mingfa.Huang
 * @Date: 2020/8/16
 * @Des: IloadDialog
 */
interface IloadDialog {
    var ctx: Context?
    var dialog: ProgressDialog?

    fun showLoading(content: String) {
        (dialog ?: kotlin.run {
            ProgressDialog(ctx).also {
                it.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            }
        }).also {
            dialog = it
        }.also {
            it.setMessage(content)
        }.show()
    }

    fun dismissLoading() {
        dialog?.dismiss()
    }
}