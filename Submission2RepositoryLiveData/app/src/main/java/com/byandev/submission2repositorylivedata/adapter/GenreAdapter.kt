package com.byandev.submission2repositorylivedata.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.byandev.submission2repositorylivedata.R
import com.byandev.submission2repositorylivedata.data.repository.remote.GenreDetail
import kotlinx.android.synthetic.main.item_genre.view.*

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.Holder>() {

    private var lsGenre: List<GenreDetail> = emptyList()

    fun addList(genre: List<GenreDetail>) {
        this.lsGenre = genre
        notifyDataSetChanged()
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(genreList: GenreDetail) {
            with(itemView) {
                tvGenres.text = genreList.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_genre, parent, false))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(lsGenre[position])
    }

    override fun getItemCount(): Int = lsGenre.size
}