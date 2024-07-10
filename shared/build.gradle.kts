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
    jvm()
    androidTarget()

    sourceSets {
        all {
            languageSettings {
                optIn("androidx.paging.ExperimentalPagingApi")
                optIn("kotlin.contracts.ExperimentalContracts")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                optIn("androidx.compose.material3.ExperimentalMaterial3Api")
                optIn("androidx.compose.foundation.ExperimentalFoundationApi")
            }
        }

        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            api(compose.materialIconsExtended)
            api(compose.components.resources)

            api(libs.ktor.client.core)
            api(libs.ktor.client.contentNegotiation)
            api(libs.ktor.client.resources)
            api(libs.ktor.client.logging)
            api(libs.ktor.serialization.json)

            api(libs.sqldelight.coroutines.extensions)
            api(libs.sqldelight.paging3.extensions)
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

            api(libs.paging.common)
            api(libs.paging.compose.common)
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            implementation(libs.sqldelight.driver.android)
            implementation(libs.koin.android)
        }

        jvmMain.dependencies {
            implementation(libs.ktor.client.java)
            implementation(libs.sqldelight.driver.sqlite)
        }
    }

    jvmToolchain(11)
}

composeCompiler {
    // Enable 'strong skipping'
    // https://medium.com/androiddevelopers/jetpack-compose-strong-skipping-mode-explained-cbdb2aa4b900
    enableStrongSkippingMode.set(true)

    // Needed for Layout Inspector to be able to see all of the nodes in the component tree:
    //https://issuetracker.google.com/issues/338842143
    includeSourceInformation.set(true)

    val configurationFile = rootProject.file("compose-stability.conf")
    stabilityConfigurationFile.set(configurationFile)
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