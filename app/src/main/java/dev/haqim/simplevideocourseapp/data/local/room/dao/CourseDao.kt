package dev.haqim.simplevideocourseapp.data.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import dev.haqim.simplevideocourseapp.data.local.entity.COURSE_TABLE
import dev.haqim.simplevideocourseapp.data.local.entity.CourseEntity

@Dao
interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(course: List<CourseEntity>)
    
    @Query("SELECT * FROM $COURSE_TABLE")
    fun getPaging(): PagingSource<Int, CourseEntity>

    @Query("SELECT * FROM $COURSE_TABLE where title = :title")
    suspend fun getByTitle(title: String): CourseEntity?

    @Query("DELETE FROM $COURSE_TABLE")
    suspend fun clearAll()

    @Update
    suspend fun update(course: CourseEntity)

    @Query("SELECT COUNT(*) FROM $COURSE_TABLE")
    suspend fun countAll(): Int
}