package com.byandev.subjetpackpro1.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.byandev.subjetpackpro1.R
import com.byandev.subjetpackpro1.data.MovieEntity
import com.byandev.subjetpackpro1.ui.activity.DetailActivity
import kotlinx.android.synthetic.main.item_movie.view.*

class AdapterMovies : RecyclerView.Adapter<AdapterMovies.Holder>() {

    private var lsMovieEntity = ArrayList<MovieEntity>()

    fun setMovieEntity(movieEntity: List<MovieEntity>?) {
        if (movieEntity == null) return
        lsMovieEntity.clear()
        lsMovieEntity.addAll(movieEntity)
    }


    class Holder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        fun bind(movieEntity: MovieEntity) {
            with(itemView) {
                tvTitle.text = movieEntity.title
                Glide.with(context)
                    .load(movieEntity.imgPath)
                    .centerInside()
                    .centerCrop()
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_baseline_broken_image_24)
                            .error(R.drawable.ic_baseline_signal_cellular_connected_no_internet_4_bar_24)
                    )
                    .into(imgPath)
                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_MOVIE, movieEntity.movieId)
                    }
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_movie,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(lsMovieEntity[position])
    }

    override fun getItemCount(): Int = lsMovieEntity.size
}