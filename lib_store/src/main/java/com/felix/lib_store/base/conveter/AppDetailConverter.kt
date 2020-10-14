package com.felix.lib_store.base.conveter

import com.felix.lib_store.base.bean.AppDetailBean
import com.felix.lib_store.base.util.*
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Retrofit
import java.lang.reflect.Type

class AppDetailConverter : BaseConverter<AppDetailBean>() {
    override fun match(type: Type, annotations: Array<Annotation>, retrofit: Retrofit) =
        type == AppDetailBean::class.java

    override fun converter(responseBody: ResponseBody) = responseBody.string().let {
        Jsoup.parse(it)
    }?.select(".container.cf")?.firstOrNull()?.let { doc ->
        AppDetailBean().apply {
            //app基本信息
            doc.select(".app-info").firstOrNull()?.also {
                this.appIcon = it.select("img").first().attr("src")
            }?.select(".intro-titles")?.firstOrNull()?.also {
                //应用公司简介
                this.appCompanyTip = it.select("p").firstOrNull()?.text()
                //应用名称
                this.appName = it.select("h3").firstOrNull()?.text()
                //评分
                this.appStartNum = it.select(".star1-empty").firstOrNull()?.let {
                    it.children().firstOrNull()?.className()
                }.runCatching {
                    this?.last()?.let {
                        it.toInt() - '0'.toInt()
                    } ?: 0
                }.getOrDefault(0)
                //评论数
                this.appRemarkNum = it.select(".app-intro-comment").firstOrNull()?.text()?.let {
                    CommentNumPattern.matcher(it).takeIf { it.find() }?.group()
                }.runCatching {
                    this?.toInt() ?: 0
                }.getOrDefault(0)
                //下载链接
                this.appDownloadUrl = it.select(".app-info-down").firstOrNull()?.let {
                    it.select("a")?.firstOrNull()
                }?.let {
                    AppUrl + it.attr("href")
                }
            }
            //应用详细信息
            doc.select(".float-left")?.mapNotNull {
                it.select("div").lastOrNull()?.text()?.trim()
            }?.forEach {
                when {
                    AppSizePattern.matcher(it).matches() -> {
                        val size =
                            it.substring(0, it.length - 2)
                                .replace(" ", "")
                                .replace(",", "")
                                .toDoubleOrNull() ?: 0.0
                        this.appSize = it.trimEnd().last().let {
                            if (it in arrayOf('k', 'K')) {
                                (size * 1024).toLong()
                            } else if (it in arrayOf('m', 'M')) {
                                (size * 1024 * 1024).toLong()
                            } else if (it in arrayOf('g', 'G')) {
                                (size * 1024 * 1024 * 1024).toLong()
                            } else {
                                size.toLong()
                            }
                        }
                    }
                    DatePattern.matcher(it).matches() -> {
                        this.appLastUpdateDate = it
                    }
                    PkgPattern.matcher(it).matches() -> {
                        this.appPkgName = it
                    }
                }
            }
            //app的权限信息
//            it.select(".second-ul").firstOrNull()?.select("li")?.mapNotNull {
//                it.text()
//            }?.map {
//                it.replace("▪ ", "").trim()
//            }?.let {
//                this.appPermissionList = it
//            }


            //截图信息
            doc.getElementById("J_thumbnail_wrap")?.let {
                it.select("img")?.filterNotNull()
            }?.mapNotNull {
                it.attr("src")
            }?.let {
                this.appScreenshotList = it
            }

            //应用介绍信息
            doc.getElementsByClass("app-text").firstOrNull()?.select(".pslide")?.filterNotNull()
                ?.let {
                    val des = StringBuilder()
                    it.getOrNull(0)?.textNodes()?.filterNotNull()?.mapNotNull {
                        it.text()
                    }?.forEach {
                        if (it.isBlank()) {
                            des.append("\n")
                        } else {
                            des.append(it.trim())
                        }
                    }
                    this.appDescription = des.toString()


                    val feature = StringBuilder()
                    it.getOrNull(0)?.textNodes()?.filterNotNull()?.mapNotNull {
                        it.text()
                    }?.forEach {
                        if (it.isBlank()) {
                            feature.append("\n")
                        } else {
                            feature.append(it.trim())
                        }
                    }
                    this.appFeature = feature.toString()
                }
        }
    }
}