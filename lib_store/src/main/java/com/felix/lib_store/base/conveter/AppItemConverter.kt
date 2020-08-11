package com.felix.lib_store.base.conveter

import androidx.core.text.isDigitsOnly
import com.felix.lib_store.base.bean.AppItem
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class AppItemConverter : BaseConverter<List<AppItem>>() {
    override fun match(type: Type, annotations: Array<Annotation>, retrofit: Retrofit) =
        type.takeIf {
            it is ParameterizedType
        }?.let {
            it as ParameterizedType
        }?.let {
            it.actualTypeArguments
        }?.let {
            it.first()
        }?.let {
            it == AppItem::class.java
        } ?: false

    override fun converter(responseBody: ResponseBody): List<AppItem>? {
        var pages = 0
        return responseBody?.let {
            it.string()
        }?.let {
            Jsoup.parse(it)
        }?.also {
            it.select(".pages").firstOrNull()?.let {
                it.select("a")
            }?.forEach {
                it.text()?.takeIf { it.isDigitsOnly() }?.let {
                    it.toInt()
                }?.takeIf { it > pages }?.also {
                    pages = it
                }
            }
        }?.let {
            it.select(".applist").firstOrNull()
        }?.let {
            it.select("li")
        }?.map {
            AppItem().apply {
                it.select("h5").first()?.let {
                    appDetailUrl = it.select("a").firstOrNull()?.attr("href")
                    appPkgName = appDetailUrl.runCatching {
                        val firstLatter = "id="
                        substring(
                            this.indexOf(firstLatter) + firstLatter.length,
                            this.lastIndexOf("&").takeIf { it > 0 } ?: this.length
                        )
                    }.getOrNull()
                    appName = it.text()
                }
                it.select("a").firstOrNull()?.select("img")?.firstOrNull()?.let {
                    appIcon = it.attr("data-src").trim()
                }
                it.select("p").firstOrNull()?.select("a")?.firstOrNull()?.let {
                    appCategoryName = it.text()
                    appCategoryId = it.attr("href")?.let {
                        it.substring(
                            it.lastIndexOf("/") + 1,
                            it.length
                        )
                    }
                }
                this.allPage = pages
            }
        }
    }
}