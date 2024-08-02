plugins {
    alias(libs.plugins.blueprint.android.library.compose)
    alias(libs.plugins.blueprint.android.hilt)
}

android {
    namespace = "com.milwen.blueprint.core.ui"
}

dependencies {
    implementation(project(":core:base"))
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.material)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
}
