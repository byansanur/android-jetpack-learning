package com.byandev.subjetpackpro1.ui.viewModels

import androidx.lifecycle.ViewModel
import com.byandev.subjetpackpro1.data.MovieEntity
import com.byandev.subjetpackpro1.utils.DataDummy

class MoviesViewModel : ViewModel() {
    fun getMovie() : List<MovieEntity> = DataDummy.generateDataMovie()
}