package dev.haqim.simplevideocourseapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * To store information of fetched latest page
 *
 * @property id
 * @constructor Create empty Remote keys
 */
@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val prevKey: Int?,
    val nextKey: Int?
)