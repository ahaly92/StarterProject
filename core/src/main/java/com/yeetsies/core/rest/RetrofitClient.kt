package com.yeetsies.core.rest

import com.yeetsies.core.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val CONNECTION_TIMEOUT = 30L
    private const val TIMBER_TAG = "HTTP"

    fun <T> createRestApi(baseUrl: String, service: Class<T>): T {
        val httpClient = newOkHttpClient()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(service)
    }

    private fun newOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor { chain ->
                @Suppress("ConstantConditionIf")
                if (BuildConfig.LOG_CONSOLE) {
                    val httpLogging = HttpLoggingInterceptor {
                        Timber.tag(TIMBER_TAG)
                        Timber.e(it)
                    }.apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                    httpLogging.intercept(chain)
                } else {
                    chain.proceed(chain.request())
                }
            }
            .readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .build()
}
