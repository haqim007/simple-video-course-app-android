package dev.haqim.simplevideocourseapp.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.haqim.simplevideocourseapp.data.local.entity.CourseEntity
import dev.haqim.simplevideocourseapp.data.local.entity.RemoteKeys
import dev.haqim.simplevideocourseapp.data.local.room.dao.CourseDao
import dev.haqim.simplevideocourseapp.data.local.room.dao.RemoteKeysDao

@Database(
    entities = [
        CourseEntity::class,
        RemoteKeys::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun courseDao(): CourseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "simplevideo.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { 
                        INSTANCE = it
                    }
            }
        }
    }
}