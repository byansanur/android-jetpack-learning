package com.byandev.projectacademy1.ui.academy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.byandev.projectacademy1.R
import com.byandev.projectacademy1.ui.viewModels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_academy.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AcademyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_academy, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[AcademyViewModel::class.java]


//            val courses = DataDummy.generateDummyCourse()
            val courses = viewModel.getCourse() // change
            val academyAdapter = AcademyAdapter()
            academyAdapter.setCourses(courses)
            with(rv_academy) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
            }
        }
    }
}