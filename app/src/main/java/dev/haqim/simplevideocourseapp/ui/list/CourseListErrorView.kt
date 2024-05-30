package dev.haqim.simplevideocourseapp.ui.list

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import dev.haqim.simplevideocourseapp.domain.model.Course

@Composable
fun CourseListErrorView(
    loadState: CombinedLoadStates,
    pagingData: LazyPagingItems<Course>,
) {
    val isPaginatingError = (loadState.append is LoadState.Error) ||
            pagingData.itemCount > 1
    val error = if (loadState.append is LoadState.Error)
        (loadState.append as LoadState.Error).error
    else
        (loadState.refresh as LoadState.Error).error

    val modifierError = if (isPaginatingError) {
        Modifier.padding(8.dp)
    } else {
        Modifier.fillMaxSize()
    }
    if (pagingData.itemCount == 0) {
        Column(
            modifier = modifierError,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            if (!isPaginatingError) {
                Icon(
                    modifier = Modifier
                        .size(64.dp),
                    imageVector = Icons.Rounded.Warning, contentDescription = null
                )
            }

            Log.e("::OnLoadError", error.stackTraceToString())

            Text(
                modifier = Modifier
                    .padding(8.dp),
                text = error.message ?: error.toString(),
                textAlign = TextAlign.Center,
            )

            Button(
                onClick = {
                    pagingData.refresh()
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
}