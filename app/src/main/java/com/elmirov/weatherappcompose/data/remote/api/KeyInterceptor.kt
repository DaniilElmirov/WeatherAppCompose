package com.elmirov.weatherappcompose.data.remote.api

import com.elmirov.weatherappcompose.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class KeyInterceptor @Inject constructor() : Interceptor {

    companion object {
        private const val KEY = "key"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newUrl = originalRequest.url().newBuilder()
            .addQueryParameter(KEY, BuildConfig.WEATHER_API_KEY)
            .build()

        val newRequest = originalRequest.newBuilder().url(newUrl).build()

        return chain.proceed(newRequest)
    }
}