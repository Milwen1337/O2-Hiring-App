plugins {
    `kotlin-dsl`
}

group = "com.milwen.blueprint.plugins"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    implementation(libs.kotlin.reflect)
}

gradlePlugin {
    /**
     * Register convention plugins so they are available in the build scripts of the application
     */
    plugins {
        register("androidApplication") {
            id = "blueprint.android.application"
            implementationClass = "com.milwen.blueprint.plugins.AndroidApplicationPlugin"
        }
        register("androidLibrary") {
            id = "blueprint.android.library"
            implementationClass = "com.milwen.blueprint.plugins.AndroidLibraryPlugin"
        }
        register("androidHilt") {
            id = "blueprint.android.hilt"
            implementationClass = "com.milwen.blueprint.plugins.AndroidHiltPlugin"
        }
        register("androidLibraryCompose") {
            id = "blueprint.android.library.compose"
            implementationClass = "com.milwen.blueprint.plugins.AndroidLibraryComposePlugin"
        }
    }
}
