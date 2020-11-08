package com.byandev.submission2repositorylivedata.network

import com.byandev.submission2repositorylivedata.utils.Constant.Companion.ACCESS_TOKEN
import com.byandev.submission2repositorylivedata.utils.Constant.Companion.API_KEY
import com.byandev.submission2repositorylivedata.utils.Constant.Companion.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfig {
    fun getClient() : ApiEndpoint {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addNetworkInterceptor { chain ->
                val auth = ACCESS_TOKEN
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", auth)
                    .build()
                chain.proceed(request)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
        return retrofit.create(ApiEndpoint::class.java)
    }
}