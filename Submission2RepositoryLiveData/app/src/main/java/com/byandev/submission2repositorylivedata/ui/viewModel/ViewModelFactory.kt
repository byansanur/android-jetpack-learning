package com.byandev.submission2repositorylivedata.ui.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.byandev.submission2repositorylivedata.data.repository.RepositoryApp
import com.byandev.submission2repositorylivedata.di.Injection

class ViewModelFactory(
    private val reposApp: RepositoryApp
) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(reposApp) as T
            }
            modelClass.isAssignableFrom(TvViewModel::class.java) -> {
                TvViewModel(reposApp) as T
            }
            else -> throw Throwable("Unknow viewModel class" + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory? {
            if (instance == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (instance == null) {
                        instance = Injection.appInject()?.let { ViewModelFactory(it) }
                    }
                }
            }
            return instance
        }
    }


}