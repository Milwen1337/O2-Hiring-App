package com.milwen.blueprint.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.milwen.blueprint.extensions.AppFlavor
import com.milwen.blueprint.extensions.FlavorDimension
import com.milwen.blueprint.extensions.Versions
import com.milwen.blueprint.extensions.configureAndroid
import com.milwen.blueprint.extensions.configureAndroidCompose
import com.milwen.blueprint.extensions.configureFlavors
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("dagger.hilt.android.plugin")
                apply("org.jetbrains.kotlin.android")
                apply("kotlin-parcelize")
            }

            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    targetSdk = Versions.TARGET_SDK
                    missingDimensionStrategy(
                        FlavorDimension.contentType.name,
                        AppFlavor.dev.name
                    )
                }

                buildFeatures {
                    buildConfig = true
                }

                configureAndroid()
                configureAndroidCompose(this)
                configureFlavors(this)
            }
        }
    }
}
