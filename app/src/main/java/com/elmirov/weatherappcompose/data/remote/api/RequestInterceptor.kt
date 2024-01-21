package com.elmirov.weatherappcompose.data.remote.api

import com.elmirov.weatherappcompose.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale
import javax.inject.Inject

class RequestInterceptor @Inject constructor() : Interceptor {

    companion object {
        private const val KEY = "key"
        private const val LANG = "lang"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newUrl = originalRequest.url().newBuilder()
            .addQueryParameter(KEY, BuildConfig.WEATHER_API_KEY)
            .addQueryParameter(LANG, Locale.getDefault().language)
            .build()

        val newRequest = originalRequest.newBuilder().url(newUrl).build()

        return chain.proceed(newRequest)
    }
}