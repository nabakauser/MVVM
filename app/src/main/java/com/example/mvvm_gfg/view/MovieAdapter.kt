package com.example.mvvm_gfg.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_gfg.Constants.IMAGE_URL
import com.example.mvvm_gfg.model.Result
import com.example.mvvm_gfg.databinding.MovieLayoutBinding

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movieList = ArrayList<Result>()

    fun setMovieList(movieList: List<Result>) {
        this.movieList = movieList as ArrayList<Result>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: MovieLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieLayoutBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val listPosition = movieList[position]

            holder.binding.uiTvTitle.text = listPosition.title
            Glide.with(holder.itemView)
                .load(IMAGE_URL + listPosition.poster_path)
                .into(holder.binding.uiIvMovieImage)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}
