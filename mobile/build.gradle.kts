import com.milwen.blueprint.extensions.Versions
@Suppress("DSL_SCOPE_VIOLATION") // Remove when fixed https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.blueprint.android.application)
    alias(libs.plugins.blueprint.android.hilt)
}

android {
    namespace = "com.milwen.blueprint"

    defaultConfig {
        applicationId = "com.milwen.blueprint"
        versionCode = Versions.VERSION_CODE
        versionName = Versions.VERSION_NAME
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
            excludes.add("/META-INF/versions/9/previous-compilation-data.bin")
        }
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":core:base"))
    implementation(project(":core:ui"))


    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.lifecycle.runtime)
}
