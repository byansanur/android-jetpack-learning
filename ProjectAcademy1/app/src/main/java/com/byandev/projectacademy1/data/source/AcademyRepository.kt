package com.byandev.projectacademy1.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.byandev.projectacademy1.data.source.local.LocalDataSource
import com.byandev.projectacademy1.data.source.local.entity.CourseEntity
import com.byandev.projectacademy1.data.source.local.entity.CourseWithModule
import com.byandev.projectacademy1.data.source.local.entity.ModuleEntity
import com.byandev.projectacademy1.data.source.remote.ApiResponse
import com.byandev.projectacademy1.data.source.remote.RemoteDataSource
import com.byandev.projectacademy1.data.source.remote.response.ContentResponse
import com.byandev.projectacademy1.data.source.remote.response.CourseResponse
import com.byandev.projectacademy1.data.source.remote.response.ModuleResponse
import com.byandev.projectacademy1.utils.AppExecutors
import com.byandev.projectacademy1.value_object.Resource

class AcademyRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : AcademyDataSource {

    companion object {
        @Volatile
        private var instance: AcademyRepository? = null

        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): AcademyRepository =
            instance ?: synchronized(this) {
                instance ?: AcademyRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllCourses(): LiveData<Resource<PagedList<CourseEntity>>> {
        return object : NetworkBoundResource<PagedList<CourseEntity>, List<CourseResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<CourseEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllCourses(), config).build()
            }
            // Jadi kode di atas digunakan untuk membaca getAllCourses dari LocalDataSource.
            // Kemudian akan diteruskan di sini:
            override fun shouldFetch(data: PagedList<CourseEntity>?): Boolean =
                data == null || data.isEmpty()
            // Pada bagian shoudFetch ini dilakukan pengecekan apakah ada datanya atau tidak.
            // Jika balikan dari pengecekan itu true maka akan memanggil fungsi berikut:
            public override fun createCall(): LiveData<ApiResponse<List<CourseResponse>>> =
                remoteDataSource.getAllCourses()
            // Karena data dari LocalDataSource null atau empty, maka akan dilakukan pengambilan
            // data dari RemoteDataSource. Dan selanjutnya akan dilakukan proses inserting di bagian ini:
            public override fun saveCallResult(data: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                for (response in data) {
                    val course = CourseEntity(response.id,
                        response.title,
                        response.description,
                        response.date,
                        false,
                        response.imagePath)
                    courseList.add(course)
                }

                localDataSource.insertCourses(courseList)
            }
        }.asLiveData()
        // Metode saveCallResult akan terpanggil dan akan melakukan proses insert data yang berasal dari RemoteDataSource.
        // Kemudian hasilnya dari NetworkBoundResource dibungkus dengan kelas Resource,
        // sehingga di bagian Activity atau Fragment bisa jadi seperti ini: (cek kelas fragment atau activity)
    }

    override fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>> {
        return object : NetworkBoundResource<CourseWithModule, List<ModuleResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<CourseWithModule> =
                localDataSource.getCourseWithModules(courseId)

            override fun shouldFetch(data: CourseWithModule?): Boolean =
                data?.mModules == null || data.mModules.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> =
                remoteDataSource.getModule(courseId)

            override fun saveCallResult(data: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for (response in data) {
                    val course = ModuleEntity(response.moduleId,
                        response.courseId,
                        response.title,
                        response.position,
                        false)
                    moduleList.add(course)
                }
                localDataSource.insertModules(moduleList)
            }
        }.asLiveData()
    }

    override fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>> {
        return object : NetworkBoundResource<List<ModuleEntity>, List<ModuleResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<ModuleEntity>> =
                localDataSource.getAllModulesByCourse(courseId)

            override fun shouldFetch(data: List<ModuleEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ModuleResponse>>> =
                remoteDataSource.getModule(courseId)

            override fun saveCallResult(data: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                for (response in data) {
                    val course = ModuleEntity(response.moduleId,
                        response.courseId,
                        response.title,
                        response.position,
                        false)

                    moduleList.add(course)
                }

                localDataSource.insertModules(moduleList)
            }
        }.asLiveData()
    }

    override fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>> {
        return object : NetworkBoundResource<ModuleEntity, ContentResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<ModuleEntity> =
                localDataSource.getModuleWithContent(moduleId)

            override fun shouldFetch(data: ModuleEntity?): Boolean =
                data?.contentEntity == null

            override fun createCall(): LiveData<ApiResponse<ContentResponse>> =
                remoteDataSource.getContent(moduleId)

            override fun saveCallResult(data: ContentResponse) =
                localDataSource.updateContent(data.content.toString(), moduleId)
        }.asLiveData()
    }

    override fun getBookmarkedCourses(): LiveData<PagedList<CourseEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getBookmarkedCourses(), config).build()
    }

    override fun setCourseBookmark(course: CourseEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setCourseBookmark(course, state) }

    override fun setReadModule(module: ModuleEntity) =
        appExecutors.diskIO().execute { localDataSource.setReadModule(module) }

    /* deprecation
//    override fun getAllCourse(): LiveData<Resource<List<CourseEntity>>> {
//        return object : NetworkBoundResource<List<CourseEntity>, List<CourseResponse>>(appExecutors) {
//            public override fun loadFromDB(): LiveData<List<CourseEntity>> =
//                localDataSource.getAllCourses()
//            override fun shouldFetch(data: List<CourseEntity>?): Boolean =
//                data == null || data.isEmpty()
//            public override fun createCall(): LiveData<ApiResponse<List<CourseResponse>>> =
//                remoteDataSource.getAllCourses()
//            public override fun saveCallResult(courseResponses: List<CourseResponse>) {
//                val courseList = ArrayList<CourseEntity>()
//                for (response in courseResponses) {
//                    val course = CourseEntity(response.id,
//                        response.title,
//                        response.description,
//                        response.date,
//                        false,
//                        response.imagePath)
//                    courseList.add(course)
//                }
//
//                localDataSource.insertCourses(courseList)
//            }
//        }.asLiveData()
//    }
//
//    override fun getBookmarkedCourse(): LiveData<List<CourseEntity>> {
//        val courseResult = MutableLiveData<List<CourseEntity>>()
//        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCourseCallback {
//            override fun onAllCourseReceived(courseResponse: List<CourseResponse>) {
//                val courseList = ArrayList<CourseEntity>()
//                for (response in courseResponse) {
//                    val course = CourseEntity(
//                        response.id,
//                        response.title,
//                        response.description,
//                        response.date,
//                        false,
//                        response.imagePath
//                    )
//                    courseList.add(course)
//                }
//                courseResult.postValue(courseList)
//            }
//        })
//        return courseResult
//    }
//
//    override fun getCourseWithModules(courseId: String): LiveData<CourseEntity> {
//        val courseResult = MutableLiveData<CourseEntity>()
//        remoteDataSource.getAllCourses(object : RemoteDataSource.LoadCourseCallback {
//            override fun onAllCourseReceived(courseResponse: List<CourseResponse>) {
//                lateinit var course : CourseEntity
//                for (response in courseResponse) {
//                    if (response.id == courseId) {
//                        course = CourseEntity(
//                            response.id,
//                            response.title,
//                            response.description,
//                            response.date,
//                            false,
//                            response.imagePath
//                        )
//                    }
//                }
//                courseResult.postValue(course)
//            }
//
//        })
//        return courseResult
//    }
//
//    override fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>> {
//        val moduleResults = MutableLiveData<List<ModuleEntity>>()
//        remoteDataSource.getModule(courseId, object : RemoteDataSource.LoadModulesCallback {
//            override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>) {
//                val moduleList = ArrayList<ModuleEntity>()
//                for(response in moduleResponses) {
//                    val course = ModuleEntity(response.moduleId,
//                        response.courseId,
//                        response.title,
//                        response.position,
//                        false
//                    )
//                    moduleList.add(course)
//                }
//                moduleResults.postValue(moduleList)
//            }
//        })
//        return moduleResults
//    }
//
//    override fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity> {
//        val moduleResult = MutableLiveData<ModuleEntity>()
//        remoteDataSource.getModule(courseId, object : RemoteDataSource.LoadModulesCallback {
//            override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>) {
//                lateinit var module: ModuleEntity
//                for (response in moduleResponses) {
//                    if (response.moduleId == moduleId) {
//                        module = ModuleEntity(response.moduleId,
//                            response.courseId,
//                            response.title,
//                            response.position,
//                            false)
//                        remoteDataSource.getContent(moduleId, object : RemoteDataSource.LoadContentCallback {
//                            override fun onContentReceived(contentResponse: ContentResponse) {
//                                module.contentEntity = ContentEntity(contentResponse.content)
//                                moduleResult.postValue(module)
//                            }
//                        })
//                        break
//                    }
//                }
//            }
//        })
//        return moduleResult
//    }
     */

}