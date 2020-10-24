package com.byandev.projectacademy1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.byandev.projectacademy1.R
import com.byandev.projectacademy1.data.source.local.entity.ModuleEntity
import com.byandev.projectacademy1.ui.viewModels.CourseReaderViewModel
import com.byandev.projectacademy1.ui.viewModels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_module_content.*

class ModuleContentFragment : Fragment() {

    companion object {
        val TAG = ModuleContentFragment::class.java.simpleName
        fun newInstance(): ModuleContentFragment = ModuleContentFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
//            val content = ContentEntity("<h3 class=\\\"fr-text-bordered\\\">Contoh Content</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(
                requireActivity(),
                factory
            )[CourseReaderViewModel::class.java]
            val module = viewModel.getSelectedModule()
            populateWebView(module)
        }
    }

    private fun populateWebView(content: ModuleEntity) {
        content.contentEntity?.content?.let {
            web_view.loadData(it, "text/html", "UTF-8")
        }
    }
}