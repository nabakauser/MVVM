package com.example.mvvm_gfg.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mvvm_gfg.repository.MainRepository
import com.example.mvvm_gfg.viewmodel.MyViewModelFactory
import com.example.mvvm_gfg.data.MovieService
import com.example.mvvm_gfg.viewmodel.MovieViewModel
import com.example.mvvm_gfg.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var viewModel: MovieViewModel? = null
    //private val retrofitService = MovieService.RetrofitInstance.api.getPopularMovies(API_KEY)
    private var movieAdapter = MovieAdapter()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setUpRecyclerView()
        setUpViewModel()
    }

    private fun setUpRecyclerView() {
        movieAdapter = MovieAdapter()
        binding?.uiRvMovies?.apply {
            adapter = movieAdapter
        }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(MovieService()))
        )[MovieViewModel::class.java]

        viewModel?.getPopularMovies()
        viewModel?.observeMovieLiveData()?.observe(this, { movieList ->
            movieAdapter.setMovieList(movieList)
        })
    }
}
