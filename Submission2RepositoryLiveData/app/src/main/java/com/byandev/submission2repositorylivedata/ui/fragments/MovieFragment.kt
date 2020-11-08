package com.byandev.submission2repositorylivedata.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.byandev.submission2repositorylivedata.R
import com.byandev.submission2repositorylivedata.adapter.MovieAdapter
import com.byandev.submission2repositorylivedata.data.repository.remote.NowPlayingResult
import com.byandev.submission2repositorylivedata.ui.MainActivity
import com.byandev.submission2repositorylivedata.ui.viewModel.MovieViewModel
import com.byandev.submission2repositorylivedata.ui.viewModel.ViewModelFactory
import com.byandev.submission2repositorylivedata.utils.Constant.Companion.DELAYED
import com.byandev.submission2repositorylivedata.utils.Constant.Companion.PAGE_SIZE
import com.byandev.submission2repositorylivedata.utils.Resource
import kotlinx.android.synthetic.main.fragment_tv.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MovieFragment : Fragment() {

    private lateinit var adapterMovie: MovieAdapter

    private var movie = listOf<NowPlayingResult>()

    private val movieViewModel by lazy {
        val viewModelFactory = activity?.application?.let { ViewModelFactory.getInstance() }
        ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setRv()
        movieViewModel.movie.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                imgNoData.visibility = View.VISIBLE
                pbLoad.visibility = View.GONE
            } else {
                pbLoad.visibility = View.GONE
                movie = it
                adapterMovie.addList(movie)
            }
        })

    }

    private fun setRv() {
        adapterMovie = MovieAdapter(context)
        adapterMovie.notifyDataSetChanged()
        rvList.apply {
            adapter = adapterMovie
            layoutManager = GridLayoutManager(context, 2)
        }
    }
}