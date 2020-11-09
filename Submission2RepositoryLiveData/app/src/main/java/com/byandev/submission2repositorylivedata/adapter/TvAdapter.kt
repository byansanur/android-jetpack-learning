package com.byandev.submission2repositorylivedata.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.byandev.submission2repositorylivedata.R
import com.byandev.submission2repositorylivedata.data.repository.remote.NowPlayingResult
import com.byandev.submission2repositorylivedata.data.repository.remote.TvResult
import com.byandev.submission2repositorylivedata.ui.DetailActivity
import com.byandev.submission2repositorylivedata.utils.Constant.Companion.IMAGE_URL
import com.byandev.submission2repositorylivedata.utils.ConvertDate
import kotlinx.android.synthetic.main.imgae_rounded.view.*
import kotlinx.android.synthetic.main.item_movie.view.*

class TvAdapter(private val context: Context?) : RecyclerView.Adapter<TvAdapter.Holder>()  {

    private var lsTv: List<TvResult> = emptyList()
    fun addList(movie: List<TvResult>) {
        this.lsTv = movie
        notifyDataSetChanged()
    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvList: TvResult) {
            with(itemView) {
                tvTitle.text = tvList.name
                Glide.with(context)
                    .load("${IMAGE_URL}w342${tvList.poster_path}")
                    .into(imgThumb)
                tvDate.text = ConvertDate.convertReleaseDate(tvList.first_air_date)
                tvRating.text = tvList.vote_average.toString()
                setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("idTvShow", tvList.id.toString())
                    context?.startActivity(intent)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(lsTv[position])
    }

    override fun getItemCount(): Int = lsTv.size
}