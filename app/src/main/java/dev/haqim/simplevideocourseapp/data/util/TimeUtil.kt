package dev.haqim.simplevideocourseapp.data.util

import dev.haqim.simplevideocourseapp.domain.model.Course

fun secondsToDuration(totalSeconds: Int): Course.Duration {
    val hour = (totalSeconds.toDouble() / 3600).toInt()
    val remainingSecondsAfterHour = totalSeconds % 3600
    val minutes = remainingSecondsAfterHour / 60
    val seconds = remainingSecondsAfterHour % 60

    return Course.Duration(hour, minutes, seconds, totalSeconds)
}