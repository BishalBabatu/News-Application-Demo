package com.example.newbees.data.sources.remote

import com.example.newbees.utils.AppConstants.AUTH_KEY
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class CustomInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val oldRequest = chain.request().newBuilder()

        val customReq = oldRequest.addHeader("X-Api-Key",AUTH_KEY).build()

       return chain.proceed(customReq)
    }

    fun authInterceptor():OkHttpClient{

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

      return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(CustomInterceptor())
          .build()
    }
}