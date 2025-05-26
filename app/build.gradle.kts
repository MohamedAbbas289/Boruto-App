plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.example.borutoapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.borutoapp"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
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

// ── Retrofit ─────────────────────────────────────────────
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)       // optional
    implementation(libs.retrofit.converter.moshi)      // optional

    // ── Paging ───────────────────────────────────────────────
    implementation(libs.paging.runtime.ktx)
    implementation(libs.paging.compose)                // Compose support
    testImplementation(libs.paging.testing)

    // ── kotlinx‑serialization ────────────────────────────────
    implementation(libs.kotlinx.serialization.json)

    // ── DataStore (Preferences) ──────────────────────────────
    implementation(libs.androidx.datastore.preferences)

    // navigation:
    implementation(libs.navigation.compose)

    // Coil
    implementation(libs.coil)
    implementation(libs.coil.network.okhttp)            // optional

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    implementation(libs.room.paging)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.dagger.hilt.android.compiler)

    // Accompanist
//    implementation(libs.accompanist.pager)
//    implementation(libs.accompanist.pager.indicators)
//    implementation(libs.accompanist.swiperefresh)
//    implementation(libs.accompanist.systemuicontroller)

    // Palette API
    implementation(libs.palette.ktx)

    implementation(libs.androidx.core.splashscreen)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}