rootProject.name = "KMPMultiModulewithSubmodule"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

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

include(":composeApp")

include(":shared")

include(":shared:viewmodel")
project(":shared:viewmodel").projectDir = File("$rootDir/shared-viewmodel")

include(":shared:domain")
project(":shared:domain").projectDir = File("$rootDir/shared-domain")

include(":shared:data")
project(":shared:data").projectDir = File("$rootDir/shared-data")

// KmpTutorial shared module
include(":kmp-tutorial-shared")
project(":kmp-tutorial-shared").projectDir = File("$rootDir/kmp-tutorial/KmpTutorial/shared")