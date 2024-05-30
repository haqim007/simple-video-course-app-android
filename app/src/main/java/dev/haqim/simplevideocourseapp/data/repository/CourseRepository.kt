package dev.haqim.simplevideocourseapp.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import dev.haqim.simplevideocourseapp.data.local.CourseLocalDataSource
import dev.haqim.simplevideocourseapp.data.local.entity.toModel
import dev.haqim.simplevideocourseapp.data.mechanism.Resource
import dev.haqim.simplevideocourseapp.data.pagingsource.CourseRemoteMediator
import dev.haqim.simplevideocourseapp.data.remote.RemoteDataSource
import dev.haqim.simplevideocourseapp.di.DispatcherIO
import dev.haqim.simplevideocourseapp.domain.model.Course
import dev.haqim.simplevideocourseapp.domain.repository.ICourseRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

const val NETWORK_PAGE_SIZE = 30

class CourseRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: CourseLocalDataSource,
    @DispatcherIO
    private val dispatcher: CoroutineDispatcher
): ICourseRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getCourses(): Flow<PagingData<Course>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            remoteMediator = CourseRemoteMediator(localDataSource, remoteDataSource),
            pagingSourceFactory = {
                localDataSource.getPaging()
            }
        ).flow.map {
            it.map { course ->
                course.toModel()
            }
        }
    }

    override fun getCourse(title: String): Flow<Resource<Course?>> {
        return flow{
            emit(Resource.Loading())
            try {
                val res = localDataSource.getByTitle(title)
                emit(Resource.Success(data = res?.toModel()))
            }catch (e: Exception){
                emit(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
            }
        }
    }
}