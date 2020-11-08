package com.byandev.submission2repositorylivedata.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.byandev.submission2repositorylivedata.data.repository.RepositoryApp
import com.byandev.submission2repositorylivedata.data.repository.remote.MovieDetail
import com.byandev.submission2repositorylivedata.data.repository.remote.NowPlayingResult

class MovieViewModel(
    private val reposApp: RepositoryApp
) : ViewModel() {

    val movie : LiveData<List<NowPlayingResult>> = reposApp.getMovie()

    fun getMovieDetail(movieId: Long) : LiveData<MovieDetail> = reposApp.getMovieDetail(movieId)

}