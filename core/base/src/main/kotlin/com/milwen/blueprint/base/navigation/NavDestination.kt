package com.milwen.blueprint.base.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.navArgument


interface Nav {
    val path: String

    fun route(): String = NavigationUtil.buildRoute(path, getArgumentsPlaceholders())

    fun arguments(): List<NamedNavArgument> = emptyList()

    fun getArgumentsPlaceholders(): String = ""
}

interface NavDestination : Nav {
    val content: @Composable (navHostController: NavHostController) -> Unit

    fun enterTransition(): (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null

    fun exitTransition(): (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null

    fun popEnterTransition(): (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)? = null

    fun popExitTransition(): (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)? = null
}

interface NavDestinationArgs : NavDestination {
    val arguments: List<NamedNavArgument>

    override fun arguments(): List<NamedNavArgument> = arguments

    override fun getArgumentsPlaceholders(): String =
        arguments.joinToString("&") { argument -> "${argument.name}={${argument.name}" }
}

interface NavDestinationPayload<T : Payload> : NavDestination {
    val payloadClazz: Class<T>

    override fun arguments(): List<NamedNavArgument> {
        return listOf(
            navArgument(ARG_PAYLOAD) {
                type = PayloadNavType(payloadClazz)
            }
        )
    }

    override fun getArgumentsPlaceholders(): String {
        return "$ARG_PAYLOAD={$ARG_PAYLOAD}"
    }
}

interface NavNagivation : Nav {
    val destinations: List<NavDestination>
}