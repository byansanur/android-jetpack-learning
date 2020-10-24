package com.byandev.projectacademy1.ui.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.byandev.projectacademy1.data.source.AcademyRepository
import com.byandev.projectacademy1.injection.Injection
import com.byandev.projectacademy1.ui.academy.AcademyViewModel
import com.byandev.projectacademy1.ui.bookmark.BookmarkViewModel
import com.byandev.projectacademy1.ui.detail.DetailCourseViewModel
import com.byandev.projectacademy1.ui.reader.CourseReaderViewModel

class ViewModelFactory private constructor(
    private val mAcademyRepository: AcademyRepository
) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance : ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AcademyViewModel::class.java) -> {
                AcademyViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(DetailCourseViewModel::class.java) -> {
                DetailCourseViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(BookmarkViewModel::class.java) -> {
                BookmarkViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(CourseReaderViewModel::class.java) -> {
                CourseReaderViewModel(mAcademyRepository) as T
            }
            else -> throw Throwable("Unknown viewModel class: " + modelClass.name)
        }
    }
}