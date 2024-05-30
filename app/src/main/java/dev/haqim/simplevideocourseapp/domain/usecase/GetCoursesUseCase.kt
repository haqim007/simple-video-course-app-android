package dev.haqim.simplevideocourseapp.domain.usecase

import androidx.paging.PagingData
import dagger.hilt.android.scopes.ViewModelScoped
import dev.haqim.simplevideocourseapp.domain.model.Course
import dev.haqim.simplevideocourseapp.domain.repository.ICourseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetCoursesUseCase @Inject constructor(private val repository: ICourseRepository) {
    operator fun invoke(): Flow<PagingData<Course>>{
        return repository.getCourses()
    }
}