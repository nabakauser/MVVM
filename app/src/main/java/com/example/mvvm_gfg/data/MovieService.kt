package com.example.mvvm_gfg.data

import com.example.mvvm_gfg.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieService {

    object RetrofitInstance {
        val api: MovieInterface by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getRetrofitClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieInterface::class.java)
        }

        private fun getRetrofitClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor())
                .addInterceptor { chain ->
                val builder = chain.request().newBuilder()
                return@addInterceptor chain.proceed(builder.build())
            }
                .build()
        }

        private fun httpLoggingInterceptor() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}
