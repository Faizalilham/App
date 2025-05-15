plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt.android)
    kotlin("kapt")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.faizal.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.faizal.newsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    hilt {
        enableAggregatingTask = true
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

    //Splash Api
    implementation(libs.androidx.core.splashscreen)

    //Compose Navigation
    implementation(libs.androidx.navigation.compose)

    //Dagger Hilt
    implementation(libs.hilt.android)
//    kapt("com.google.dagger:hilt-compiler:2.45")
    implementation(libs.androidx.hilt.navigation.compose)


    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //Coil
    implementation(libs.coil.compose)

    //Datastore
    implementation(libs.androidx.datastore.preferences)

    //Compose Foundation
    implementation(libs.androidx.foundation)

    //Accompanist
    implementation(libs.accompanist.systemuicontroller)

    //Paging 3
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)

    kapt(libs.hilt.compiler)


    //Room
    implementation(libs.androidx.room.runtime)
//    kapt("androidx.room:room-compiler:2.5.2")
    implementation(libs.androidx.room.ktx)
    kapt(libs.room.compiler)

    // Unit Testing
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.paging.common)
    testImplementation(libs.truth)
    testImplementation(libs.turbine)
    testImplementation(libs.core.testing)

    // Hilt Testing
    androidTestImplementation(libs.hilt.testing)
    kaptAndroidTest(libs.hilt.compiler)

    // UI Testing
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.androidx.junit)

}