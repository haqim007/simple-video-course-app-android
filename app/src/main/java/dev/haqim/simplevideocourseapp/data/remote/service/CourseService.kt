package dev.haqim.simplevideocourseapp.data.remote.service

import dev.haqim.simplevideocourseapp.data.remote.response.CoursesResponseItem
import retrofit2.http.GET

interface CourseService {
    @GET("playlist.json")
    suspend fun getCourses(): List<CoursesResponseItem>
}