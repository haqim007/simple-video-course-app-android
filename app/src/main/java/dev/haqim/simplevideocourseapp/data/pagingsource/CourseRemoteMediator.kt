package dev.haqim.simplevideocourseapp.data.pagingsource

import androidx.paging.LoadType
import dev.haqim.simplevideocourseapp.data.local.CourseLocalDataSource
import dev.haqim.simplevideocourseapp.data.local.entity.CourseEntity
import dev.haqim.simplevideocourseapp.data.local.entity.RemoteKeys
import dev.haqim.simplevideocourseapp.data.local.entity.toEntities
import dev.haqim.simplevideocourseapp.data.remote.RemoteDataSource


class CourseRemoteMediator (
    private val localDataSource: CourseLocalDataSource,
    private val remoteDataSource: RemoteDataSource
): BaseRemoteMediator<CourseEntity>(){

    override suspend fun processData(page: Int, loadType: LoadType): Boolean {
        // val offset = (page - 1) * state.config.pageSize
        val endOfPaginationReached = true //hardcoded because the API doesn't support pagination
        val prevKey = null //if(page == 1) null else page - 1
        val nextKey = null //if (endOfPaginationReached) null else page + 1
        val courses = remoteDataSource.getCourses().getOrThrow()
        val remoteKeys = courses.map {
            RemoteKeys(
                title = it.title,
                prevKey = prevKey,
                nextKey = nextKey
            )
        }

        localDataSource.insertAllAndRemoteKeys(
            remoteKeys,
            courses.toEntities(),
            loadType == LoadType.REFRESH
        )
        
        return endOfPaginationReached
    }

    override suspend fun getRemoteKeyById(data: CourseEntity): RemoteKeys? {
        return localDataSource.getRemoteKeyByTitle(data.title)
    }

}
