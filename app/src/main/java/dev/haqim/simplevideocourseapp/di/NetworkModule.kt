package dev.haqim.simplevideocourseapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dev.haqim.simplevideocourseapp.data.remote.base.APIConfig
import dev.haqim.simplevideocourseapp.data.remote.service.CourseService
import okhttp3.OkHttpClient

@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

    @ViewModelScoped
    @Provides
    fun provideCoursesService(
        apiConfig: APIConfig
    ): CourseService = apiConfig.createService(CourseService::class.java)

    @ViewModelScoped
    @Provides
    fun provideOkHttpClient(): OkHttpClient.Builder =
        OkHttpClient.Builder()
}