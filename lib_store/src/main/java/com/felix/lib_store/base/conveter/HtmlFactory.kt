package com.felix.lib_store.base.conveter

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class HtmlFactory : Converter.Factory() {
    val converterList = mutableListOf<BaseConverter<*>>()

    init {
        converterList.add(AppItemConverter())
        converterList.add(AppCategoryConveter())
        converterList.add(AppDetailConverter())
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        converterList.forEach {
            if (it.match(type, annotations, retrofit)) {
                return it.getConverter()
            }
        }
        return super.responseBodyConverter(type, annotations, retrofit)
    }
}