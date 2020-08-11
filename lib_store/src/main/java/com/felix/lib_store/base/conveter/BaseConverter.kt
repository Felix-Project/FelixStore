package com.felix.lib_store.base.conveter

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

abstract class BaseConverter<T> {
    abstract fun match(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Boolean
    fun getConverter(): Converter<ResponseBody, T>? {
        return object : Converter<ResponseBody, T> {
            override fun convert(value: ResponseBody): T? {
                return converter(value)
            }
        }
    }

    protected abstract fun converter(responseBody: ResponseBody): T?
}