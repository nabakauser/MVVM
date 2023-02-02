package com.example.mvvm_gfg.repository

import com.example.mvvm_gfg.Constants.API_KEY
import com.example.mvvm_gfg.data.MovieService

class MainRepository constructor(private val retrofitService: MovieService) {

    fun getAllMovies() = MovieService.RetrofitInstance.api.getPopularMovies(API_KEY)
}

//      Because the app will be able to get data from the network as well as keep an offline
// cache of previously downloaded results, you'll need a way for your app to organize
// these multiple sources of data. You'll do this by implementing a repository class,
// which will serve as a single source of truth for the app's data, and abstract
// the source of the data (network, cache, etc.) out of the view model.


//Inside the below repository class, we need to pass the retrofit service instance
// to perform the network call. The repository class will only interact with the network source,
// the response of the network call will be handled later in ViewModel.