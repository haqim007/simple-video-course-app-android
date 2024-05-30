package dev.haqim.simplevideocourseapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.haqim.simplevideocourseapp.data.mechanism.Resource
import dev.haqim.simplevideocourseapp.domain.model.Course
import dev.haqim.simplevideocourseapp.domain.usecase.GetCourseUseCase
import dev.haqim.simplevideocourseapp.domain.usecase.GetCoursesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCourseUseCase: GetCourseUseCase
): ViewModel() {
    private val _state = MutableStateFlow(DetailUiState())
    val state get() = _state.asStateFlow()

    fun getCourse(title: String){
        viewModelScope.launch {
            getCourseUseCase(title).collect{res ->
                _state.update {
                    it.copy(
                        course = res
                    )
                }
            }

        }
    }
}

data class DetailUiState(
    val course: Resource<Course?> = Resource.Idle()
)