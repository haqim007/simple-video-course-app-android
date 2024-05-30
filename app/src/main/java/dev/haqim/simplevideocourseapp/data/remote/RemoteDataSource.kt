package dev.haqim.simplevideocourseapp.data.remote

import dagger.hilt.android.scopes.ViewModelScoped
import dev.haqim.simplevideocourseapp.data.mechanism.getResult
import dev.haqim.simplevideocourseapp.data.remote.response.CoursesResponseItem
import dev.haqim.simplevideocourseapp.data.remote.service.CourseService
import javax.inject.Inject

@ViewModelScoped
class RemoteDataSource @Inject constructor(
    private val service: CourseService
) {
    suspend fun getCourses(): Result<List<CoursesResponseItem>> = getResult {
        service.getCourses()
    }
}