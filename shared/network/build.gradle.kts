plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    jvm()
    androidTarget()

    sourceSets {
        all {
            languageSettings {
                // optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }

        commonMain.dependencies {
            implementation(projects.shared.common)
            implementation(projects.shared.domain)

            implementation(libs.koin.core)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.contentNegotiation)
            implementation(libs.ktor.client.resources)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.json)
        }

        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(libs.ktor.client.android)
        }

        jvmMain.dependencies {
            implementation(libs.ktor.client.java)
        }
    }

    jvmToolchain(11)
}

android {
    namespace = "app.pokedex.shared.network"
    compileSdk = 34
    defaultConfig.minSdk = 26
}
