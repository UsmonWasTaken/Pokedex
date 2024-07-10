plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.compose)
}

kotlin {
    jvm()
    androidTarget()

    sourceSets {
        all {
            languageSettings {
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
                optIn("androidx.compose.material3.ExperimentalMaterial3Api")
                optIn("androidx.compose.foundation.ExperimentalFoundationApi")
            }
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            api(compose.components.resources)

            implementation(libs.koin.core)
            implementation(projects.shared.domain)
            implementation(projects.shared.common)

            api(libs.mvikotlin)
            api(libs.mvikotlin.main)
            implementation(libs.mvikotlin.extensions.coroutines)

            api(libs.decompose)
            implementation(libs.decompose.extensions.compose)
            api(libs.essenty.lifecycle)
            implementation(libs.qdsfdhvh.imageLoader)

            implementation(libs.paging.common)
            implementation(libs.paging.compose.common)
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
        packageOfResClass = "app.pokedex.shared.ui"
    }
}

android {
    namespace = "app.pokedex.shared.ui"
    compileSdk = 34
    defaultConfig.minSdk = 26
}
