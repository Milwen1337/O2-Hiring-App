package com.milwen.blueprint.plugins

import com.android.build.gradle.LibraryExtension
import com.milwen.blueprint.extensions.Versions
import com.milwen.blueprint.extensions.configureAndroidCompose
import com.milwen.blueprint.extensions.configureFlavors
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryComposePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                compileSdk = Versions.COMPILE_SDK

                buildFeatures {
                    buildConfig = true
                }

                configureAndroidCompose(this)
                configureFlavors(this)
            }
        }
    }
}
