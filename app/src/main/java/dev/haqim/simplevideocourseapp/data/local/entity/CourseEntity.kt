package dev.haqim.simplevideocourseapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import dev.haqim.simplevideocourseapp.data.remote.response.CoursesResponseItem
import dev.haqim.simplevideocourseapp.data.util.secondsToDuration
import dev.haqim.simplevideocourseapp.domain.model.Course

const val COURSE_TABLE = "course"
@Entity(tableName = COURSE_TABLE)
data class CourseEntity(
    @field:SerializedName("video_duration")
    val videoDuration: Int,
    @field:SerializedName("video_url")
    val videoUrl: String,
    val description: String,
    @field:SerializedName("presenter_name")
    val presenterName: String,
    @PrimaryKey
    val title: String,
    @field:SerializedName("thumbnail_url")
    val thumbnailUrl: String
)

fun CoursesResponseItem.toEntity() = CourseEntity(
    videoDuration, videoUrl, description, presenterName, title, thumbnailUrl
)

fun List<CoursesResponseItem>.toEntities() = this.map { it.toEntity() }

fun CourseEntity.toModel() = Course(
    videoDuration = secondsToDuration(this.videoDuration),
    videoUrl, description, presenterName, title, thumbnailUrl
)
