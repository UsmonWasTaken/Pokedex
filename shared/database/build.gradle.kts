plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.sqldelight)
}

kotlin {
    jvm()
    androidTarget()

    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared.common)
            implementation(projects.shared.domain)
            implementation(libs.koin.core)
            implementation(libs.sqldelight.coroutines.extensions)
            implementation(libs.sqldelight.paging3.extensions)
            implementation(libs.sqldelight.primitive.adapters)
        }

        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(libs.sqldelight.driver.android)
        }

        jvmMain.dependencies {
            implementation(libs.sqldelight.driver.sqlite)
        }
    }

    jvmToolchain(11)
}

android {
    namespace = "app.pokedex.shared.database"
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