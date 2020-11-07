package com.byandev.projectacademy1.data.source.remote

import android.os.Handler
import com.byandev.projectacademy1.data.source.remote.response.ContentResponse
import com.byandev.projectacademy1.data.source.remote.response.CourseResponse
import com.byandev.projectacademy1.data.source.remote.response.ModuleResponse
import com.byandev.projectacademy1.utils.EspressoIdlingResource
import com.byandev.projectacademy1.utils.helper.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    // implement live data
    private val handler = Handler()
    /*
    Catatan:
    penggunaan Handler untuk delay, seperti yang dilakukan pada project ini merupakah hal yang tidak disarankan.
    Karena proyek yang kita buat hanyalah simulasi, di mana data yang diperoleh disimulasikan berasal
    dari server atau API. Maka dari itu, penggunaan Handler pada proyek Academy digunakan untuk
    mensimulasikan proses asynchonous yang terjadi.
     */

    companion object {

        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper) : RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    /*
    deprecation

    fun getAllCourses(): List<CourseResponse> = jsonHelper.loadCourses()

    fun getModule(courseId: String): List<ModuleResponse> = jsonHelper.loadModule(courseId)

    fun getContent(moduleId: String): ContentResponse = jsonHelper.loadContent(moduleId)
     */

    fun getAllCourses(callback: LoadCourseCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onAllCourseReceived(jsonHelper.loadCourses())
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getModule(courseId: String, callback: LoadModulesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onAllModulesReceived(jsonHelper.loadModule(courseId))
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getContent(moduleId: String, callback: LoadContentCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed(
            {
                callback.onContentReceived(jsonHelper.loadContent(moduleId))
                EspressoIdlingResource.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    interface LoadCourseCallback {
        fun onAllCourseReceived(courseResponse: List<CourseResponse>)
    }

    interface LoadModulesCallback {
        fun onAllModulesReceived(moduleResponses: List<ModuleResponse>)
    }

    interface LoadContentCallback {
        fun onContentReceived(contentResponse: ContentResponse)
    }
}