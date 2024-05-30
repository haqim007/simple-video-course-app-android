package dev.haqim.simplevideocourseapp.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import dev.haqim.simplevideocourseapp.R
import dev.haqim.simplevideocourseapp.domain.model.Course

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CourseListItemView(
    course: Course,
    onClick: (course: Course) -> Unit
){
    Card(
        onClick = { onClick(course) },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.card_side_margin))
            .padding(bottom = dimensionResource(id = R.dimen.card_bottom_margin))
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            GlideImage(
                model = course.thumbnailUrl,
                contentDescription = stringResource(R.string.course_item_image),
                Modifier
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.card_side_margin))
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(id = R.dimen.margin_normal))
            ) {

                CourseDurationView(course.videoDuration)

                Text(
                    text = course.title,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                )

                Text(
                    text = course.description,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = course.presenterName,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Light,
                        fontSize = 15.sp
                    )
                )
            }
        }
    }
}

@Composable
fun CourseDurationView(duration: Course.Duration) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        val commonTextStyle = MaterialTheme.typography.titleSmall.copy(
            fontWeight = FontWeight.Bold,
            color = Color(91, 161, 147),
            fontSize = 12.sp
        )
        Text(
            text = "${duration.hour}h",
            maxLines = 1,
            style = commonTextStyle
        )
        Text(
            text = "${duration.minutes}m",
            maxLines = 1,
            style = commonTextStyle
        )
        Text(
            text = "${duration.seconds}s",
            maxLines = 1,
            style = commonTextStyle
        )
    }
}

@Preview
@Composable
private fun CourseListItemViewPreview() {
    CourseListItemView(
        course = Course(
            videoDuration = Course.Duration(hour = 1, minutes = 0, seconds = 0, allInSeconds = 3600),
            description = "90 seconds exercise for Chemistry",
            presenterName = "Kaoru Sakata",
            thumbnailUrl = "https://quipper.github.io/native-technical-exam/images/sakata.jpg",
            videoUrl = "https://quipper.github.io/native-technical-exam/videos/sakata.mp4",
            title = "G12 Chemistry"
        ),
        onClick = {}
    )
}