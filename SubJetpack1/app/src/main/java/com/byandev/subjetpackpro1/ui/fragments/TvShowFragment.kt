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
import kotlinx.android.synthetic.main.fragment_movie.progress_bar
import kotlinx.android.synthetic.main.fragment_tv_show.*

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
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[TvShowViewModel::class.java]

            val tvShow = viewModel.getTvShow()
            val adapterTvShow = AdapterTvShow()
            if (tvShow.isNullOrEmpty()) {
                progress_bar.visibility = View.VISIBLE
                rvTvShow.visibility = View.GONE
            } else {
                adapterTvShow.setTvShowEntity(tvShow)
                rvTvShow.apply {
                    layoutManager = LinearLayoutManager(context)
                    setHasFixedSize(true)
                    adapter = adapterTvShow
                }
                progress_bar.visibility = View.GONE

            }
        }
    }
}