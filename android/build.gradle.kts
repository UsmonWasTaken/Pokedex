plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

val majorVersion = 1
val minorVersion = 0
val patchVersion = 0

android {
    namespace = "app.pokedex.android"
    compileSdk = 34

    defaultConfig {
        applicationId = "app.pokedex.android"
        minSdk = 26
        targetSdk = 34
        versionCode = majorVersion * 1000 + minorVersion * 100 + patchVersion
        versionName = "$majorVersion.$minorVersion.$patchVersion"
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            pickFirsts.add("META-INF/INDEX.LIST")
            excludes.add("META-INF/AL2.0")
            excludes.add("META-INF/LGPL2.1")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
        }
        debug {
            isMinifyEnabled = false
        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.splashscreen)
    implementation(libs.koin.android)
}