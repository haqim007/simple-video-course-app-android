package dev.haqim.simplevideocourseapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.haqim.simplevideocourseapp.domain.model.Course
import dev.haqim.simplevideocourseapp.domain.usecase.GetCoursesUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getCoursesUseCase: GetCoursesUseCase
): ViewModel() {
    val courses: Flow<PagingData<Course>>

    init {
        courses = getCoursesUseCase().cachedIn(viewModelScope)
    }
}