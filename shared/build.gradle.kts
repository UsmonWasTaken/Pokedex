import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.compose)
    alias(libs.plugins.sqldelight)
}

kotlin {
    jvmToolchain(11)

    jvm("desktop") {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
        java {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }

    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
        java {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material3)
                api(compose.materialIconsExtended)
                api(compose.components.resources)

                api(libs.ktor.client.core)
                api(libs.ktor.client.contentNegotiation)
                api(libs.ktor.client.logging)
                api(libs.ktor.serialization.json)

                api(libs.sqldelight.coroutines.extensions)
                api(libs.sqldelight.primitive.adapters)

                api(libs.koin.core)
                api(libs.koin.test)

                api(libs.kotlinx.coroutines.core)
                api(libs.kotlinx.serialization.json)

                api(libs.mvikotlin)
                api(libs.mvikotlin.main)
                api(libs.mvikotlin.extensions.coroutines)

                api(libs.decompose)
                api(libs.decompose.extensions.compose)
                api(libs.essenty.lifecycle)
                api(libs.qdsfdhvh.imageLoader)
            }

            compilerOptions {
                freeCompilerArgs.add("-Xexpect-actual-classes")
                freeCompilerArgs.add("-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi")
                freeCompilerArgs.add("-opt-in=androidx.compose.material3.ExperimentalMaterial3Api")
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.ktor.client.android)
                implementation(libs.sqldelight.driver.android)
                implementation(libs.koin.android)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(libs.ktor.client.java)
                implementation(libs.sqldelight.driver.sqlite)
            }
        }
    }
}

compose {
    resources {
        publicResClass = true
        packageOfResClass = "app.pokedex.shared"
    }
}

android {
    namespace = "app.pokedex.shared"
    compileSdk = 34
    defaultConfig.minSdk = 26
}

sqldelight {
    databases {
        create("PokedexDatabase") {
            packageName.set("app.pokedex.shared.database")
        }
    }
}