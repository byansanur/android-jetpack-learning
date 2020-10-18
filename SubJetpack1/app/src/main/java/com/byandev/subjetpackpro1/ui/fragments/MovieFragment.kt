package com.byandev.subjetpackpro1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.byandev.subjetpackpro1.R
import com.byandev.subjetpackpro1.ui.adapter.AdapterMovies
import com.byandev.subjetpackpro1.ui.viewModels.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class MovieFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {


            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[MoviesViewModel::class.java]

            val movie = viewModel.getMovie()
            val adapterMovies = AdapterMovies()
            if (movie.isNullOrEmpty()) {
                rvMovie.visibility = View.GONE
                progress_bar.visibility = View.VISIBLE
            } else {
                adapterMovies.setMovieEntity(movie)
                rvMovie.apply {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = adapterMovies
                }
                progress_bar.visibility = View.GONE
            }
        }
    }


}