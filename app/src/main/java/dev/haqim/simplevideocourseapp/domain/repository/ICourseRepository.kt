package dev.haqim.simplevideocourseapp.domain.repository

import androidx.paging.PagingData
import dev.haqim.simplevideocourseapp.data.mechanism.Resource
import dev.haqim.simplevideocourseapp.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface ICourseRepository {
    fun getCourses(): Flow<PagingData<Course>>
    fun getCourse(title: String): Flow<Resource<Course?>>
}