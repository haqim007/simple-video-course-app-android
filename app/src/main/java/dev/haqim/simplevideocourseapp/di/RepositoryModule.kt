package dev.haqim.simplevideocourseapp.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dev.haqim.simplevideocourseapp.data.repository.CourseRepository
import dev.haqim.simplevideocourseapp.domain.repository.ICourseRepository

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    @ViewModelScoped
    fun provideRepositoryModule(repository: CourseRepository): ICourseRepository
}