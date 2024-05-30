package dev.haqim.simplevideocourseapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class CoursesResponse(

	@field:SerializedName("CoursesResponse")
	val coursesResponse: List<CoursesResponseItem>
)

data class CoursesResponseItem(

	@field:SerializedName("video_duration")
	val videoDuration: Int,

	@field:SerializedName("video_url")
	val videoUrl: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("presenter_name")
	val presenterName: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("thumbnail_url")
	val thumbnailUrl: String
)
