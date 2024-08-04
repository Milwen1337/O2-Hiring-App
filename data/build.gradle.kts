plugins {
    alias(libs.plugins.blueprint.android.library)
    alias(libs.plugins.blueprint.android.hilt)
}

android {
    namespace = "com.milwen.blueprint.data"
}

dependencies {
    implementation(project(":core:base"))
    implementation(project(":domain"))
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
}
