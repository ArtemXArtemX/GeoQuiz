plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.geoquiz"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.geoquiz"
        minSdk = 27
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
    implementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.compose.ui.ui2)
    implementation(libs.androidx.compose.ui.ui.tooling.preview2)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.foundation.layout.android)
    debugImplementation(libs.androidx.compose.ui.ui.tooling2)

    implementation(libs.androidx.compose.ui.ui3)
    implementation(libs.androidx.compose.ui.ui.tooling.preview3)
    debugImplementation(libs.androidx.compose.ui.ui.tooling3)
    implementation(libs.androidx.compose.material3.material32)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
}