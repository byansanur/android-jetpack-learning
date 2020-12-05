package com.byandev.projectacademy1.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.byandev.projectacademy1.R
import com.byandev.projectacademy1.data.source.local.entity.CourseEntity
import com.byandev.projectacademy1.ui.viewModels.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_bookmark.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class BookmarkFragment : Fragment(),
    BookmarkFragmentCallback {

    private lateinit var adapter: BookmarkAdapter
    private lateinit var viewModel: BookmarkViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        itemTouchHelper.attachToRecyclerView(rv_bookmark)

        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[BookmarkViewModel::class.java]

            adapter = BookmarkAdapter(this)
            progress_bar.visibility = View.VISIBLE
            viewModel.getBookmark().observe(this, { courses ->
                progress_bar.visibility = View.GONE
                adapter.submitList(courses)
                adapter.notifyDataSetChanged()
            })
            rv_bookmark.layoutManager = LinearLayoutManager(context)
            rv_bookmark.setHasFixedSize(true)
            rv_bookmark.adapter = adapter
        }
    }
    override fun onShareClick(course: CourseEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder.from(requireActivity()).apply {
                setType(mimeType)
                setChooserTitle("Bagikan aplikasi ini sekarang.")
                setText(resources.getString(R.string.share_text, course.title))
                startChooser()
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean = true
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val courseEntity = adapter.getSwipedData(swipedPosition)
                courseEntity?.let { viewModel.setBookmark(it) }
                val snackbar = Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    courseEntity?.let { viewModel.setBookmark(it) }
                }
                snackbar.show()
            }
        }
    })

}