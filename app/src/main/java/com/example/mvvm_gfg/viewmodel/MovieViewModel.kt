package com.example.mvvm_gfg.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm_gfg.repository.MainRepository
import com.example.mvvm_gfg.model.Movies
import com.example.mvvm_gfg.model.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private var movieLiveData = MutableLiveData<List<Result>>()
    //private val errorMessage = MutableLiveData<String>()

    fun getPopularMovies() {
        val response = mainRepository.getAllMovies()
        response.enqueue(object :
            Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.body()!=null){
                    movieLiveData.value = response.body()?.results
                }
                else return
            }
            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Log.d("TAG",t.message.toString())
                //errorMessage.postValue(t.message)
            }

            //if you need set a value from a background thread, postValue() should be used.
        })
    }
    fun observeMovieLiveData() : LiveData<List<Result>> {
        return movieLiveData
    }
}

//MovieService.RetrofitInstance.api.getPopularMovies(API_KEY).enqueue(object :
//    Callback<Movies> {
//    override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
//        if (response.body()!=null){
//            movieLiveData.value = response.body()?.results
//        }
//        else return
//    }
//    override fun onFailure(call: Call<Movies>, t: Throwable) {
//        Log.d("TAG",t.message.toString())
//        errorMessage.postValue(t.message)
//    }
//})
//}
//fun observeMovieLiveData() : LiveData<List<Result>> {
//    return movieLiveData
//}

// ->to pass data without repository, call directly[above code] and
//    remove parameters from class MovieViewModel() : ViewModel()
