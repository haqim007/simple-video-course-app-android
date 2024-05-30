package dev.haqim.simplevideocourseapp.domain.usecase

import dagger.hilt.android.scopes.ViewModelScoped
import dev.haqim.simplevideocourseapp.data.mechanism.Resource
import dev.haqim.simplevideocourseapp.domain.model.Course
import dev.haqim.simplevideocourseapp.domain.repository.ICourseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetCourseUseCase @Inject constructor(
    private val repository: ICourseRepository
) {
    operator fun invoke(title: String): Flow<Resource<Course?>>{
        return repository.getCourse(title)
    }
}