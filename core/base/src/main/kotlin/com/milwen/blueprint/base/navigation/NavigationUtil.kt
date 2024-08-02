package com.milwen.blueprint.base.navigation

object NavigationUtil {

    fun buildRoute(path: String, query: String) =
        when (query.isBlank()) {
            true -> path
            false -> "$path?$query"
        }
}