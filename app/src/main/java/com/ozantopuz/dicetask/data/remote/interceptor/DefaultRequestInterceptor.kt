package com.ozantopuz.dicetask.data.remote.interceptor

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class DefaultRequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        val url: HttpUrl =
            request.url.newBuilder().addQueryParameter(FORMAT_NAME, FORMAT_VALUE).build()
        request = request.newBuilder().addHeader(USER_AGENT_NAME, USER_AGENT_VALUE).url(url).build()
        return chain.proceed(request)
    }

    companion object {
        private const val USER_AGENT_NAME = "User-Agent"
        private const val USER_AGENT_VALUE = "BrainzPLaces/v1.0.0"
        private const val FORMAT_NAME = "fmt"
        private const val FORMAT_VALUE = "json"
    }
}
