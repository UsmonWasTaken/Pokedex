plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
}

kotlin {
    jvm()
    androidTarget()

    sourceSets {
        all {
            languageSettings {
                optIn("androidx.paging.ExperimentalPagingApi")
                optIn("kotlin.contracts.ExperimentalContracts")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }

        commonMain.dependencies {
            implementation(projects.shared.common)
            implementation(projects.shared.domain)
            implementation(projects.shared.database)
            implementation(projects.shared.network)

            implementation(libs.koin.core)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.contentNegotiation)
            implementation(libs.ktor.client.resources)
            implementation(libs.ktor.client.logging)

            implementation(libs.sqldelight.coroutines.extensions)
            implementation(libs.sqldelight.paging3.extensions)
            implementation(libs.sqldelight.primitive.adapters)
        }

        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(libs.ktor.client.android)
            implementation(libs.sqldelight.driver.android)
        }

        jvmMain.dependencies {
            implementation(libs.ktor.client.java)
            implementation(libs.sqldelight.driver.sqlite)
        }
    }

    jvmToolchain(11)
}

android {
    namespace = "app.pokedex.data"
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