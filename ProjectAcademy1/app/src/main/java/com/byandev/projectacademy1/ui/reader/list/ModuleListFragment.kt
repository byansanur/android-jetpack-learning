package com.byandev.projectacademy1.ui.reader.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.byandev.projectacademy1.R
import com.byandev.projectacademy1.data.source.local.entity.ModuleEntity
import com.byandev.projectacademy1.ui.reader.CourseReaderActivity
import com.byandev.projectacademy1.ui.reader.CourseReaderCallback
import com.byandev.projectacademy1.ui.reader.CourseReaderViewModel
import com.byandev.projectacademy1.ui.viewModels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_module_list.*

class ModuleListFragment : Fragment() , MyAdapterClickListener {

    private lateinit var viewModel: CourseReaderViewModel

    companion object {
        val TAG = ModuleListFragment::class.java.simpleName

        fun newInstance(): ModuleListFragment = ModuleListFragment()
    }

    private lateinit var adapterList: ModuleListAdapter
    private lateinit var courseReaderCallback: CourseReaderCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(
            requireActivity(),
            factory
        )[CourseReaderViewModel::class.java]
        adapterList = ModuleListAdapter(this)
        populateRecyclerView(viewModel.getModule())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        courseReaderCallback = context as CourseReaderActivity
    }

    private fun populateRecyclerView(modules: List<ModuleEntity>) {
        progress_bar.visibility = View.GONE
        adapterList.setModules(modules)
        with(rv_module) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = adapterList
        }
        val dividerItemDecoration = DividerItemDecoration(rv_module.context, DividerItemDecoration.VERTICAL)
        rv_module.addItemDecoration(dividerItemDecoration)
    }

    override fun onItemClicked(position: Int, moduleId: String) {
        courseReaderCallback.moveTo(position, moduleId)
        viewModel.setSelectedModule(moduleId)
    }


}