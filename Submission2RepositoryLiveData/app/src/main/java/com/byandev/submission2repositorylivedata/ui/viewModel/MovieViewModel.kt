package com.byandev.submission2repositorylivedata.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.byandev.submission2repositorylivedata.data.repository.DataRepository
import com.byandev.submission2repositorylivedata.data.repository.remote.GenreDetail
import com.byandev.submission2repositorylivedata.data.repository.remote.MovieDetail
import com.byandev.submission2repositorylivedata.data.repository.remote.MovieListResult

class MovieViewModel(
    private val dataRepository: DataRepository
) : ViewModel() {

    val movie : LiveData<List<MovieListResult>> = dataRepository.getMovie()

    fun getMovieDetail(movieId: Long) : LiveData<MovieDetail> = dataRepository.getMovieDetail(movieId)

    fun getGenres(movieId: Long) : LiveData<List<GenreDetail>> = dataRepository.getGenreDetailMovie(movieId)

}