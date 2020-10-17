package com.byandev.subjetpackpro1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.byandev.subjetpackpro1.R
import com.byandev.subjetpackpro1.ui.adapter.AdapterTvShow
import com.byandev.subjetpackpro1.ui.viewModels.TvShowViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class TvShowFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progress_bar.visibility = View.VISIBLE
        progress_bar.isShown
        if (activity != null) {

            progress_bar.visibility = View.GONE

            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[TvShowViewModel::class.java]

            val tvShow = viewModel.getTvShow()
            val adapterTvShow = AdapterTvShow()
            adapterTvShow.setTvShowEntity(tvShow)
            rv_academy.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = adapterTvShow
            }
        }
    }
}