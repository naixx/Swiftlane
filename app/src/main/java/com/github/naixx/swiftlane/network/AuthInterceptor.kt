package com.github.naixx.swiftlane.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url().newBuilder()
                .setQueryParameter("key", "12887834-e4336495d6ff69e659fa87748").build()
        return chain.proceed(chain.request().newBuilder().url(url).build())
    }
}
