package com.milwen.blueprint.extensions

enum class ProjectBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
}