package com.felix.felixstore.ui.detail

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.felix.felixstore.R
import com.felix.lib_arch.mvpvm.AbsBaseViewDelegate
import com.felix.lib_store.base.util.AdmPkg
import com.felix.lib_store.base.util.AppUrl
import kotlinx.android.synthetic.main.detail_activity.*

class DetailViewDelegate : AbsBaseViewDelegate<DetailPresenterImpl, DetailViewModel>(), DetailView {
    val screenshotAdp = ScreenshotAdp()
    override fun onActivityCreated() {
        super.onActivityCreated()
        setContentView(R.layout.detail_activity)
        rvAppScreenshot.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        rvAppScreenshot.adapter = screenshotAdp
        btnAppInstall.setOnClickListener {
            //安装按钮，暂时跳转到详情下载
            viewModel.appDetailBean.value?.let {
                Log.i(TAG, "onActivityCreated: ${it.appDownloadUrl}")
            }
            viewModel.appItem.value?.let { appItem ->
                val uri: Uri = Uri.parse(AppUrl + appItem.appDetailUrl)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.packageManager.getInstalledPackages(0).any {
                    AdmPkg == it.packageName
                }.takeIf {
                    it
                }?.let {
                    intent.setPackage(AdmPkg)
                }
                context.startActivity(intent)
            }
        }
        observe(viewModel.appItem) {
            Glide.with(context).load(it.appIcon).into(ivAppIcon)
            tvAppName.text = it.appName
            tvAppCategory.text = it.appCategoryName
            tvAppPkg.text = it.appPkgName
            presenter.getAppDetail(it.appPkgName)
        }
        observe(viewModel.appDetailBean) { appDetailBean ->
            Glide.with(context).load(appDetailBean.appIcon).into(ivAppIcon)
//            tvAppName.text=appDetailBean.appName
//            tvAppCategory.text=appDetailBean.appCategoryName
//            tvAppPkg.text=appDetailBean.appPkgName

            tvAppCompany.text = appDetailBean.appCompanyTip

            tvAppRemarkNum.text = appDetailBean.appRemarkNum.takeIf { it > 10_000 }?.let {
                (it / 1000).toString() + "k"
            } ?: kotlin.run { appDetailBean.appRemarkNum.toString() }

            tvAppStar.text = String.format("%.1f", appDetailBean.appStartNum / 2f)
            tvAppSize.text = appDetailBean.appSize.let {
                var ch = arrayOf('K', 'M', 'G', 'T', 'P')
                var index = -1
                var size = it.toFloat()
                while (size > 1024) {
                    size /= 1024
                    index++
                }
                (index.takeIf { it >= 0 }?.let {
                    ch[it]
                } ?: ' ').let {
                    String.format("%.2f%c", size, it)
                }
            }
            tvAppFeature.text = appDetailBean.appFeature
            tvAppDescription.text = appDetailBean.appDescription

            appDetailBean?.appScreenshotList?.let {
                screenshotAdp.datas = it
            }
        }
    }
}