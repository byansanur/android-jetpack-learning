package com.byandev.projectacademy1.ui.academy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.byandev.projectacademy1.R
import com.byandev.projectacademy1.ui.viewModels.ViewModelFactory
import com.byandev.projectacademy1.value_object.Status
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

            val academyAdapter = AcademyAdapter()
            // berubah menjadi seperti ini
            viewModel.getCourse().observe(this, { courses ->
                if (courses != null) {
                    when (courses.status) {
                        Status.LOADING -> progress_bar?.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            progress_bar?.visibility = View.GONE
                            academyAdapter.submitList(courses.data)
                            academyAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            progress_bar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            rv_academy.layoutManager = LinearLayoutManager(context)
            rv_academy.setHasFixedSize(true)
            rv_academy.adapter = academyAdapter
        }
    }
}