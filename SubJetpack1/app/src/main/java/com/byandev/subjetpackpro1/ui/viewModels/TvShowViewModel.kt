package com.byandev.subjetpackpro1.ui.viewModels

import androidx.lifecycle.ViewModel
import com.byandev.subjetpackpro1.data.TvShowEntity
import com.byandev.subjetpackpro1.utils.DataDummy

class TvShowViewModel : ViewModel() {
    fun getTvShow() : List<TvShowEntity> = DataDummy.generateDataTvShow()
}