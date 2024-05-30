package dev.haqim.simplevideocourseapp.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import dev.haqim.simplevideocourseapp.R
import dev.haqim.simplevideocourseapp.domain.model.Course
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseListView(
    modifier: Modifier = Modifier,
    courses: Flow<PagingData<Course>>,
    onClick: (title: String) -> Unit
) {
    val pullToRefreshState = rememberPullToRefreshState()
    val pagingItems: LazyPagingItems<Course> =
        courses.collectAsLazyPagingItems()
    if(pullToRefreshState.isRefreshing){
        pagingItems.refresh()
    }
    LaunchedEffect(pagingItems.loadState) {
        when (pagingItems.loadState.refresh) {
            is  LoadState.Loading -> Unit
            is LoadState.Error,is LoadState.NotLoading -> {
                pullToRefreshState.endRefresh()
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(pullToRefreshState.nestedScrollConnection)
    ) {
        if (pagingItems.loadState.refresh is LoadState.Loading) {
            if (pagingItems.itemCount < 1) {
                Column(
                    modifier = Modifier
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
        } else if (pagingItems.loadState.refresh is LoadState.Error) {
            CourseListErrorView(pagingItems.loadState, pagingItems)
        } else if (
            pagingItems.loadState.refresh is LoadState.NotLoading &&
            pagingItems.itemCount == 0
        ) { // empty?
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text("There is no course at the moment. Please go back later")
            }

        }
        LazyColumn(
            contentPadding = PaddingValues(all = dimensionResource(id = R.dimen.card_side_margin))
        ) {
            items(
                count = pagingItems.itemCount,
                key = pagingItems.itemKey { it.title }
            ) { index ->
                val course = pagingItems[index] ?: return@items
                CourseListItemView(course = course) {
                    onClick(it.title)
                }
            }
        }
        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullToRefreshState
        )
    }
}