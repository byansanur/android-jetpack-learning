package com.byandev.subjetpackpro1.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.byandev.subjetpackpro1.R
import com.byandev.subjetpackpro1.data.TvShowEntity
import kotlinx.android.synthetic.main.item_movie.view.*

class AdapterTvShow : RecyclerView.Adapter<AdapterTvShow.Holder>() {

    private var lsTvShow = ArrayList<TvShowEntity>()

    fun setTvShowEntity(tvShowEntity: List<TvShowEntity>?) {
        if (tvShowEntity == null) return
        lsTvShow.clear()
        lsTvShow.addAll(tvShowEntity)
    }


    class Holder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        fun bind(tvShowEntity: TvShowEntity) {
            with(itemView) {
                tvTitle.text = tvShowEntity.title
                Glide.with(context)
                    .load(tvShowEntity.imgPath)
                    .centerInside()
                    .centerCrop()
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_baseline_broken_image_24)
                            .error(R.drawable.ic_baseline_signal_cellular_connected_no_internet_4_bar_24)
                    )
                    .into(imgPath)
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
        holder.bind(lsTvShow[position])
    }

    override fun getItemCount(): Int = lsTvShow.size
}