package com.byandev.submission2repositorylivedata.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.byandev.submission2repositorylivedata.R
import com.byandev.submission2repositorylivedata.data.repository.remote.MovieListResult
import com.byandev.submission2repositorylivedata.ui.activity.DetailActivity
import com.byandev.submission2repositorylivedata.utils.Constant.Companion.IMAGE_URL
import com.byandev.submission2repositorylivedata.utils.ConvertDate
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val context: Context?) : RecyclerView.Adapter<MovieAdapter.Holder>()  {

    private var lsMovie: List<MovieListResult> = emptyList()
    fun addList(movie: List<MovieListResult>) {
        this.lsMovie = movie
        notifyDataSetChanged()
    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movieList: MovieListResult) {
            with(itemView) {
                tvTitle.text = movieList.title
                Glide.with(context)
                    .load("${IMAGE_URL}w342${movieList.poster_path}")
                    .into(imgThumb)
                tvDate.text = ConvertDate.convertReleaseDate(movieList.release_date)
                tvRating.text = movieList.vote_average.toString()
                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("idMovie", movieList.id.toString())
                    context?.startActivity(intent)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(lsMovie[position])
    }

    override fun getItemCount(): Int = lsMovie.size
}