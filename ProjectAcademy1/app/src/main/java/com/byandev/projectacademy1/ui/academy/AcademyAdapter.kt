package com.byandev.projectacademy1.ui.academy

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.byandev.projectacademy1.R
import com.byandev.projectacademy1.data.source.local.entity.CourseEntity
import com.byandev.projectacademy1.ui.detail.DetailCourseActivity
import kotlinx.android.synthetic.main.items_academy.view.*

class AcademyAdapter : RecyclerView.Adapter<AcademyAdapter.Holder>() {

    private var lsCourse = ArrayList<CourseEntity>()

    fun setCourses(courses: List<CourseEntity>?) {
        if (courses == null) return
        lsCourse.clear()
        lsCourse.addAll(courses)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_academy, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val course = lsCourse[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = lsCourse.size

    class Holder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        fun bind(courseEntity: CourseEntity) {
            with(itemView) {
                tv_item_title.text = courseEntity.title
                tv_item_description.text = courseEntity.description
                tv_item_date.text = resources.getString(R.string.deadline_date, courseEntity.deadline)
                setOnClickListener {
                    val intent = Intent(context, DetailCourseActivity::class.java).apply {
                        putExtra(DetailCourseActivity.EXTRA_COURSE, courseEntity.courseId)
                    }
                    context.startActivity(intent)
                }
                Glide.with(context)
                    .load(courseEntity.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(img_poster)
            }
        }
    }
}