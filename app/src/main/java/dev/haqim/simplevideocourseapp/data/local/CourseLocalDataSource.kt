package dev.haqim.simplevideocourseapp.data.local

import androidx.paging.PagingSource
import androidx.room.withTransaction
import dev.haqim.simplevideocourseapp.data.local.entity.CourseEntity
import dev.haqim.simplevideocourseapp.data.local.entity.RemoteKeys
import dev.haqim.simplevideocourseapp.data.local.room.AppDatabase
import javax.inject.Inject

/**
 * Courses local data source
 *
 * @property database
 * @constructor Create empty Courses local data source
 */

class CourseLocalDataSource @Inject constructor(
    private val database: AppDatabase
) {
    
    suspend fun getRemoteKeyByTitle(title: String): RemoteKeys? {
        return database.remoteKeysDao().getRemoteKeyByTitle(title)
    }
    
    fun getPaging(): PagingSource<Int, CourseEntity>{
        return database.courseDao().getPaging()
    }

    suspend fun getByTitle(title: String): CourseEntity?{
        return database.courseDao().getByTitle(title)
    }

    suspend fun clearAll(){
        database.courseDao().clearAll()
    }
    
    suspend fun insertAllAndRemoteKeys(
        remoteKeys: List<RemoteKeys>, 
        courses: List<CourseEntity>,
        isRefresh: Boolean = false
    ){
        database.withTransaction { 
            if (isRefresh){
                database.remoteKeysDao().clearRemoteKeys()
                database.courseDao().clearAll()
            }
            database.remoteKeysDao().insertAll(remoteKeys)
            database.courseDao().insertAll(courses)
        }
    }

    
    suspend fun countCourses() = database.courseDao().countAll()
}