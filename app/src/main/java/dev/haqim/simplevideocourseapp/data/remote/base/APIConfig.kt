package dev.haqim.simplevideocourseapp.data.remote.base

import dev.haqim.simplevideocourseapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class APIConfig @Inject constructor(
    private val okHttpClient: OkHttpClient.Builder
) {

    private fun createRetrofit(
        baseUrl: String = BuildConfig.BASE_URL,
    ): Retrofit {

        okHttpClient
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)



        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient.build())
            .build()
    }

    fun <ServiceClass> createService(
        serviceClass: Class<ServiceClass>
    ): ServiceClass {
        val retrofit = createRetrofit()
        return retrofit.create(serviceClass)
    }


}