import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("co.touchlab.skie") version "0.8.4"
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
                baseName = "Shared" // Umbrella Framework の名前
                isStatic = true

                // Umbrella Framework に含めるモジュールを指定
                export(project(":shared:domain"))
                export(project(":shared:data"))
            }
        }
    }

    sourceSets {
        commonMain.dependencies {
            // Accessible by the native app
            api(project(":shared:domain"))
            // Accessible only in the KMM part
            api(project(":shared:data"))

            implementation(libs.koin.core)
        }
    }
}

android {
    defaultConfig {
        namespace = "dev.seabat.kmp.multimodule.shared"
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
