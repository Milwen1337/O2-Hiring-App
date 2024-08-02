pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Blueprint"

include(
    ":mobile",
    ":core:base",
    ":core:ui",
    ":data",
    ":domain"
)
