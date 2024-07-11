pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

//plugins {
//    id("com.gradle.develocity") version "3.17.5"
//}
//
//develocity {
//    buildScan {
//        termsOfUseUrl = "https://gradle.com/terms-of-service"
//        termsOfUseAgree = "yes"
//    }
//}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Pokedex"
include(":android")
include(":desktop")

include(":shared:domain")
include(":shared:common")
include(":shared:database")
include(":shared:network")
include(":shared:data")
include(":shared:ui")