package com.felix.lib_store.base.conveter

import okhttp3.ResponseBody
import org.jsoup.Jsoup
import retrofit2.Retrofit
import java.lang.reflect.Type

class AppCategoryConveter : BaseConverter<String>() {
    override fun match(type: Type, annotations: Array<Annotation>, retrofit: Retrofit) =
        type == String::class.java


    override fun converter(responseBody: ResponseBody): String? {
        return responseBody?.let {
            it.string()
        }.let {
            Jsoup.parse(it)
        }?.select(".applist-wrap")?.firstOrNull()?.takeIf {
            it.select(".applist").firstOrNull()?.select("li")?.size ?: 0 > 0
        }?.select(".main-h")?.firstOrNull()?.select("h3")?.firstOrNull()?.text()
    }
}