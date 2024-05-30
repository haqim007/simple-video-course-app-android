plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.devtoolsKSP)
    alias(libs.plugins.jetbrainsKotlinParcelize)
    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "dev.haqim.simplevideocourseapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.haqim.simplevideocourseapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug{
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("BASE_URL", "https://quipper.github.io/native-technical-exam/")
        }
        release {
            buildConfigField("BASE_URL", "https://quipper.github.io/native-technical-exam/")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)


    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.paging.runtime.ktx)
    implementation(libs.glide)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    implementation(libs.paging.runtime.ktx)
    implementation(libs.room.paging)
    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.noop)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.paging.compose)

    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.exoplayer.dash)
    implementation(libs.androidx.media3.ui)
}

inline fun <reified ValueT> com.android.build.api.dsl.VariantDimension.buildConfigField(
    name: String,
    value: ValueT
) {
    val resolvedValue = when (value) {
        is String -> "\"$value\""
        else -> value
    }.toString()
    buildConfigField(ValueT::class.java.simpleName, name, resolvedValue)
}