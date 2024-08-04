plugins {
    alias(libs.plugins.blueprint.android.library.compose)
    alias(libs.plugins.blueprint.android.hilt)
}

android {
    namespace = "com.milwen.blueprint.core.base"
}

dependencies {
    implementation(libs.androidx.compose.navigation)
    api(libs.androidx.compose.hilt.navigation)
    api(libs.kotlinx.coroutines.playservices)
    implementation(libs.gson)
}
