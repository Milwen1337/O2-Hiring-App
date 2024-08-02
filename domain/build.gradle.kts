plugins {
    alias(libs.plugins.blueprint.android.library)
    alias(libs.plugins.blueprint.android.hilt)
}

android {
    namespace = "com.milwen.blueprint.domain"
}

dependencies {
    implementation(project(":core:base"))
    implementation(project(":domain"))
}
