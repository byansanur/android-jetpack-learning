package com.byandev.subjetpackpro1.ui.viewModels

import androidx.lifecycle.ViewModel
import com.byandev.subjetpackpro1.data.MovieEntity
import com.byandev.subjetpackpro1.data.TvShowEntity
import com.byandev.subjetpackpro1.utils.DataDummy

class DetailViewModel : ViewModel() {

    private lateinit var movieId: String
    private lateinit var tvId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun setSelectedTv(tvId: String) {
        this.tvId = tvId
    }

    fun getMovie(): MovieEntity {
        lateinit var movie : MovieEntity
        val movieEntities = DataDummy.generateDataMovie()
        for (movieEntity in movieEntities) {
            if (movieEntity.movieId == movieId) {
                movie = movieEntity
            }
        }

        return movie
    }

    fun getTv() : TvShowEntity {
        lateinit var tv : TvShowEntity
        val tvEntities = DataDummy.generateDataTvShow()
        for (tvEntity in tvEntities) {
            if (tvEntity.tvShowId == tvId) {
                tv = tvEntity
            }
        }
        return tv
    }
}