package com.nanaten.retrofitcoroutine.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {
    companion object {
        private const val API_READ_TIMEOUT: Long = 10
        private const val API_CONNECT_TIMEOUT: Long = 10

        private val gson =
            GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()

        private fun getOkhttpClient(): OkHttpClient {
            val logInterceptor = HttpLoggingInterceptor()
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                .addInterceptor {
                    val httpUrl = it.request().url
                    val requestBuilder = it.request().newBuilder().url(httpUrl)
                    it.proceed(requestBuilder.build())
                }
                .addInterceptor(logInterceptor)
                .readTimeout(API_READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(API_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .build()
        }

        private fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .client(getOkhttpClient())
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        fun get(): GithubApi {
            val retrofit = getRetrofit()
            return retrofit.create(GithubApi::class.java)
        }
    }
}