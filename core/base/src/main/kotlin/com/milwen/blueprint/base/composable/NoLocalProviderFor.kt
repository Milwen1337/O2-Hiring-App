package com.milwen.blueprint.base.composable

fun noLocalProviderFor(name: String): Nothing {
    error("CompositionLocal $name not provided")
}