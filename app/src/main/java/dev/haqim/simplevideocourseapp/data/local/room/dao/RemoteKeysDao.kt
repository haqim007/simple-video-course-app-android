package dev.haqim.simplevideocourseapp.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.haqim.simplevideocourseapp.data.local.entity.RemoteKeys

@Dao
interface RemoteKeysDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey:List<RemoteKeys>)

    @Query("SELECT * FROM remote_keys where title = :title")
    suspend fun getRemoteKeyByTitle(title: String): RemoteKeys?

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}