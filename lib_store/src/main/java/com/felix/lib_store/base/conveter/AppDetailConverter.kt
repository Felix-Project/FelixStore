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

    override fun converter(responseBody: ResponseBody) = responseBody?.let {
        it.string()
    }?.let {
        Jsoup.parse(it)
    }?.let {
        it.select(".container.cf").firstOrNull()
    }?.let { doc ->
        AppDetailBean().apply {
            //app基本信息
            doc.select(".app-info").firstOrNull()?.also {
                this.appIcon = it.select("img").first().attr("src")
            }?.let {
                it.select(".intro-titles").firstOrNull()
            }?.also {
                //应用公司简介
                this.appCompanyTip = it.select("p").firstOrNull()?.text()
                //应用名称
                this.appName = it.select("h3").firstOrNull()?.text()
                //评分
                this.appStartNum = it.select(".star1-empty").firstOrNull()?.let {
                    it.children().firstOrNull()?.className()
                }.runCatching {
                    this?.last()?.let {
                        it.toInt()-'0'.toInt()
                    }?:0
                }.getOrDefault(0)
                //评论数
                this.appRemarkNum = it.select(".app-intro-comment").firstOrNull()?.text()?.let {
                    CommentNumPattern.matcher(it).takeIf { it.find() }?.let {
                        it.group()
                    }
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
            doc.select(".details").firstOrNull()?.let {
                //app的基本文件信息
                it.select(".cf").firstOrNull()?.select("li")?.filter {
                    it.className().isNullOrEmpty()
                }?.map {
                    it.text()
                }?.filterNotNull()?.forEach {
                    if (AppSizePattern.matcher(it).matches()) {
                        val size =
                            it.substring(0, it.length - 2)
                                .replace(" ", "")
                                .replace(",","")
                                .toDoubleOrNull() ?: 0.0
                        this.appSize = it.trimEnd().last().let {
                            if (it in arrayOf('k', 'K')) {
                                (size * 1024).toInt()
                            } else if (it in arrayOf('m', 'M')) {
                                (size * 1024 * 1024).toInt()
                            } else if (it in arrayOf('g', 'G')) {
                                (size * 1024 * 1024 * 1024).toInt()
                            } else {
                                size.toInt()
                            }
                        }
                    } else if (DatePattern.matcher(it).matches()) {
                        this.appLastUpdateDate = it
                    } else if (PkgPattern.matcher(it).matches()) {
                        this.appPkgName = it
                    }
                }
                //app的权限信息
                it.select(".second-ul").firstOrNull()?.let {
                    it.select("li")
                }?.map {
                    it.text()
                }?.filterNotNull()?.map {
                    it.replace("▪ ", "").trim()
                }?.let {
                    this.appPermissionList = it
                }
            }

            //截图信息
            doc.getElementById("J_thumbnail_wrap")?.let {
                it.select("img")?.filterNotNull()
            }?.map {
                it.attr("src")
            }?.filterNotNull()?.let {
                this.appScreenshotList = it
            }

            //应用介绍信息
            doc.getElementsByClass("app-text").firstOrNull()?.let {
                it.select(".pslide").filterNotNull()
            }?.let {
                it.getOrNull(0)?.text()?.let {
                    it.replace("<br>", "\n")
                }?.let {
                    it.replace("</br>", "\n")
                }?.let {
                    this.appDescription = it
                }

                it.getOrNull(1)?.text()?.let {
                    it.replace("<br>", "\n")
                }?.let {
                    it.replace("</br>", "\n")
                }?.let {
                    this.appFeature = it
                }
            }
        }
    }
}