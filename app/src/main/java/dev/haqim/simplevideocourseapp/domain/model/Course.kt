package dev.haqim.simplevideocourseapp.domain.model

data class Course(
	val videoDuration: Duration,
	val videoUrl: String,
	val description: String,
	val presenterName: String,
	val title: String,
	val thumbnailUrl: String
){
	data class Duration(
		val hour: Int,
		val minutes: Int,
		val seconds: Int,
		val allInSeconds: Int
	)
}

