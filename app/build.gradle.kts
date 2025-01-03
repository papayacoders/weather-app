import com.android.manifmerger.Actions.load
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("kapt")  // Change this line
    id("com.google.dagger.hilt.android")
}
val localProperties = File(rootProject.rootDir, "local.properties").reader().use {
    Properties().apply { load(it) }
}

val apiKey = localProperties.getProperty("WEATHER_API_KEY", "")
android {
    namespace = "com.example.weathernow"
    compileSdk = 35

    defaultConfig {
//        val localProperties = File(rootProject.rootDir, "local.properties")
//        val apiKey = localProperties.takeIf { it.exists() }?.inputStream()?.use { input ->
//            Properties().apply { load(input) }.getProperty("API_KEY")
//        }
        // Add API key to BuildConfig
        buildConfigField("String", "API_KEY", "\"$apiKey\"")
        applicationId = "com.example.weathernow"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        kapt {
            correctErrorTypes = true
        }
    }

    buildTypes {
        release {
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Dagger Hilt
//    implementation (libs.hilt.android)
//    kapt (libs.hilt.android.compiler)
//    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android.v253)

    kapt(libs.hilt.android.compiler.v2511)
    implementation(libs.androidx.hilt.navigation.compose.v110)
    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    // Coroutines
    implementation (libs.kotlinx.coroutines.android)

    // ViewModel
    implementation (libs.androidx.lifecycle.viewmodel.compose)
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

    implementation(libs.androidx.material.icons.extended)

    implementation(libs.androidsvg.aar)
}