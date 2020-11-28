package com.byandev.submission2repositorylivedata.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.byandev.submission2repositorylivedata.R
import com.byandev.submission2repositorylivedata.adapter.TvAdapter
import com.byandev.submission2repositorylivedata.data.repository.remote.TvListResult
import com.byandev.submission2repositorylivedata.ui.viewModel.TvViewModel
import com.byandev.submission2repositorylivedata.ui.viewModel.ViewModelFactory
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import kotlinx.android.synthetic.main.fragment_tv.*

class TvFragment : Fragment() {

    private lateinit var adapterTv: TvAdapter
    private var tvLs = listOf<TvListResult>()
    private val tvViewModel by lazy {
        val viewModelFactory = activity?.application?.let { ViewModelFactory.getInstance() }
        ViewModelProviders.of(this, viewModelFactory).get(TvViewModel::class.java)
    }
    private var skeleton: Skeleton? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pbLoad.visibility = View.GONE
        setRv()
        skeleton = rvList.applySkeleton(R.layout.item_movie, 6)
        skeleton?.showSkeleton()
        tvViewModel.tv.observe(viewLifecycleOwner, Observer {
            skeleton?.showOriginal()
            if (it.isNullOrEmpty()) {
                imgNoData.visibility = View.VISIBLE
                pbLoad.visibility = View.GONE
            } else {
                pbLoad.visibility = View.GONE
                tvLs = it
                adapterTv.addList(tvLs)
            }
        })
    }

    private fun setRv() {
        adapterTv = TvAdapter(context)
        adapterTv.notifyDataSetChanged()
        rvList.apply {
            adapter = adapterTv
            layoutManager = GridLayoutManager(context, 2)
        }
    }
}