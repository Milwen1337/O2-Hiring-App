package com.milwen.blueprint.plugins

import com.android.build.gradle.LibraryExtension
import com.milwen.blueprint.extensions.Versions
import com.milwen.blueprint.extensions.configureAndroid
import com.milwen.blueprint.extensions.configureFlavors
import com.milwen.blueprint.extensions.configureKotlin
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }

                defaultConfig.targetSdk = Versions.TARGET_SDK

                buildFeatures {
                    buildConfig = true
                }

                configureAndroid()
                configureKotlin()
                configureFlavors(this)
            }
        }
    }
}
