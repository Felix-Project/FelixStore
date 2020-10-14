package com.felix.felixstore.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.felix.felixstore.R
import com.felix.lib_arch.mvvm.BaseMvvmActivity
import com.felix.lib_store.base.bean.AppItem
import kotlinx.android.synthetic.main.detail_activity.*

class DetailActivity : BaseMvvmActivity<DetailViewModel>() {
    private val screenshotAdp = ScreenshotAdp()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        with(rvAppScreenshot) {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = screenshotAdp
        }
        btnAppInstall.setOnClickListener {
            //安装按钮，暂时跳转到详情下载
            viewModel.download()
        }
        observe(viewModel.appItem) {
            Glide.with(this).load(it.appIcon).into(ivAppIcon)
            tvAppName.text = it.appName
            tvAppCategory.text = it.appCategoryName
            tvAppPkg.text = it.appPkgName
            viewModel.getAppDetail(it.appPkgName)
        }
        observe(viewModel.appSize) {
            tvAppSize.text = it
        }
        observe(viewModel.appStar) {
            tvAppStar.text = it
        }
        observe(viewModel.appRemartNum) {
            tvAppRemarkNum.text = it
        }
        observe(viewModel.appDetailBean) { appDetailBean ->
            Glide.with(this)
                .load(appDetailBean.appIcon)
                .into(ivAppIcon)
            tvAppCompany.text = appDetailBean.appCompanyTip
            tvAppFeature.text = appDetailBean.appFeature
            tvAppDescription.text = appDetailBean.appDescription
            appDetailBean?.appScreenshotList?.let {
                screenshotAdp.datas = it
            }
        }
        intent.extras?.getParcelable<AppItem>(KEY_APP_ITEM)?.let {
            viewModel.appItem.postValue(it)
        }
    }

    companion object {
        private const val KEY_APP_ITEM = "APP_ITEM"

        @JvmStatic
        fun start(context: Context, appItem: AppItem) {
            val starter = Intent(context, DetailActivity::class.java)
                .putExtra(KEY_APP_ITEM, appItem)
            context.startActivity(starter)
        }
    }

}