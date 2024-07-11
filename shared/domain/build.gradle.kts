plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvm()
    androidTarget()

    sourceSets {
        commonMain.dependencies {
            api(projects.shared.common)
            api(libs.paging.common)
            api(libs.kotlinx.serialization.json)
        }
    }

    jvmToolchain(11)
}

android {
    namespace = "app.pokedex.domain"
    compileSdk = 34
    defaultConfig.minSdk = 26
}