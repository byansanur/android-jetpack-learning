package com.byandev.submission2repositorylivedata.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.byandev.submission2repositorylivedata.data.repository.RepositoryApp
import com.byandev.submission2repositorylivedata.data.repository.remote.GenreDetail
import com.byandev.submission2repositorylivedata.data.repository.remote.MovieDetail
import com.byandev.submission2repositorylivedata.data.repository.remote.MovieListResult

class MovieViewModel(
    private val reposApp: RepositoryApp
) : ViewModel() {

    val movie : LiveData<List<MovieListResult>> = reposApp.getMovie()

    fun getMovieDetail(movieId: Long) : LiveData<MovieDetail> = reposApp.getMovieDetail(movieId)

    fun getGenres(movieId: Long) : LiveData<List<GenreDetail>> = reposApp.getGenreDetailMovie(movieId)

}