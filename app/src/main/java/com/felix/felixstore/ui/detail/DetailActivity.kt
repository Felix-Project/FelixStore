package com.felix.felixstore.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.felix.lib_arch.mvpvm.BaseMvpActivity
import com.felix.lib_store.base.bean.AppItem

class DetailActivity :
    BaseMvpActivity<DetailView, DetailViewModel, DetailPresenterImpl, DetailViewDelegate>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.extras?.getParcelable<AppItem>(KEY_APP_ITEM)?.let {
            viewModel.appItem.postValue(it)
        }
    }
    companion object{
        private const val KEY_APP_ITEM = "APP_ITEM"
        @JvmStatic
        fun start(context: Context,appItem: AppItem) {
            val starter = Intent(context, DetailActivity::class.java)
                .putExtra(KEY_APP_ITEM,appItem)
            context.startActivity(starter)
        }
    }

}