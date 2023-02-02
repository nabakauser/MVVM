package com.example.mvvm_gfg.data

import com.example.mvvm_gfg.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {

    @GET("popular?")
    fun getPopularMovies(@Query("api_key") api_key: String,
    ): Call<Movies>

}
