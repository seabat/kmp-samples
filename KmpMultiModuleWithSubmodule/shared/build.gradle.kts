import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("co.touchlab.skie") version "0.10.1"
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries {
            framework {
                baseName = "Shared" // Umbrella モジュールの名前
                isStatic = true

                // Accessible by the iOS app
                export(project(":shared:viewmodel"))
                export(project(":shared:domain"))
                export(project(":kmp-tutorial-shared"))
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            // Accessible by the native app
            api(project(":shared:viewmodel"))
            api(project(":shared:domain"))

            // Accessible only in the KMP part
            implementation(project(":shared:data"))

            // KmpTutorial shared module
            api(project(":kmp-tutorial-shared"))

            // share モジュールのプログラムから参照するライブラリ
            implementation(libs.koin.core)
        }
    }
}

android {
    defaultConfig {
        namespace = "dev.seabat.kmp.withsubmodule.shared"
    }
    compileSdk =
        libs.versions.android.compileSdk
            .get()
            .toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk =
            libs.versions.android.minSdk
                .get()
                .toInt()
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            consumerProguardFiles("consumer-rules.pro")
        }
    }
}
