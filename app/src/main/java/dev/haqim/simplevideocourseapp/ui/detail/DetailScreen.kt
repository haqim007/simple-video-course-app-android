package dev.haqim.simplevideocourseapp.ui.detail

import android.app.Activity
import android.content.pm.ActivityInfo
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.haqim.simplevideocourseapp.R
import dev.haqim.simplevideocourseapp.data.mechanism.Resource
import dev.haqim.simplevideocourseapp.ui.VideoPlayerView
import dev.haqim.simplevideocourseapp.ui.list.CourseDurationView

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    title: String,
    onBackClick: () -> Unit
){
    var isFullScreenState by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getCourse(title)
    }

    val uiState by viewModel.state.collectAsState()
    val derivedTitle by remember(uiState.course.data) {
        derivedStateOf {
            // Compute the value of the derived state based on the value of the main state
            // Example: If mainState is true, set derivedState to "State A", otherwise set it to "State B"
            if (uiState.course.data != null) {
                uiState.course.data!!.title
            } else {
                "Unknown Course"
            }
        }
    }

    val activity = LocalContext.current as Activity
    LaunchedEffect(key1 = isFullScreenState) {
        if (isFullScreenState) {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        } else {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    Scaffold(
        topBar = {
            DetailTopAppBar(title = derivedTitle, onBackClick = onBackClick)
        }
    ) { paddingValues ->
        when (uiState.course) {
            is Resource.Success -> {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                ){
                    val data = uiState.course.data

                    data?.let {
                        VideoPlayerView(
                            Uri.parse(data.videoUrl),
                            onFullScreen = {isFullScreen ->
                                isFullScreenState = isFullScreen
                            }
                        )

                        if (!isFullScreenState) {
                            Column(
                                modifier = Modifier
                                    .padding(dimensionResource(id = R.dimen.card_side_margin))
                            ) {
                                Text(
                                    text = data.title,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 18.sp
                                    )
                                )

                                Text(
                                    modifier = Modifier.padding(top = 8.dp),
                                    text = "Duration",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                )

                                CourseDurationView(data.videoDuration)

                                Text(
                                    modifier = Modifier.padding(top = 8.dp),
                                    text = "Description",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                )

                                Text(
                                    text = data.description,
                                    style = MaterialTheme.typography.bodyMedium .copy(
                                        fontSize = 14.sp
                                    )
                                )

                                Text(
                                    modifier = Modifier.padding(top = 8.dp),
                                    text = "Presenter",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                )
                                Text(
                                    text = data.presenterName,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontSize = 15.sp
                                    )
                                )
                            }
                        }
                    } ?: run{
                        Text(text = derivedTitle)
                    }
                }
            }
            is Resource.Error -> {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Log.e("::OnLoadError", uiState.course.message ?: "Error")

                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = uiState.course.message ?: "Error",
                        textAlign = TextAlign.Center,
                    )

                    Button(
                        onClick = {
                            viewModel.getCourse(title)
                        },
                        content = {
                            Text(text = "Refresh")
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = Color.White,
                        )
                    )
                }
            }
            else -> {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = "Refresh Loading"
                    )

                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: () -> Unit
) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = stringResource(
                        id = R.string.navigate_up
                    )
                )
            }
        },
        title = {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    style = MaterialTheme.typography.displaySmall.copy(
                        fontSize = 18.sp
                    ),

                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
private fun CourseListItemViewPreview() {
    DetailScreen(
        title = "G12 Chemistry",
        onBackClick = {}
    )
}