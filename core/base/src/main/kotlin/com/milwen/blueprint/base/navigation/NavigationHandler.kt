package com.milwen.blueprint.base.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.milwen.blueprint.base.composable.noLocalProviderFor


sealed class Navigation {
    data object Back: Navigation()

    sealed class Route: Navigation() {
        abstract val destination: NavDestination
        abstract val navOptions: NavOptions?
        abstract val popUpToStartDestination: Boolean

        abstract fun route(): String
    }

    data class Destination(
        override val destination: NavDestination,
        override val navOptions: NavOptions? = null,
        override val popUpToStartDestination: Boolean = false,
    ) : Route() {
        override fun route(): String = destination.path
    }

    data class DestinationInitializer<T: Payload>(
        override val destination: NavDestinationPayload<T>,
        val initializer: T,
        override val navOptions: NavOptions? = null,
        override val popUpToStartDestination: Boolean = false,
    ): Route() {
        override fun route(): String = NavigationUtil.buildRoute(
            destination.path,
            "$ARG_PAYLOAD=${initializer.encodeToString()}",
        )
    }
}

class NavigationHandler (
    internal val navHostController: NavHostController,
) {
    fun navigate(navigation: Navigation) {
        when (navigation) {
            is Navigation.Back ->
                navHostController.navigateUp()
            is Navigation.Route ->
                navHostController.navigate(
                    route = navigation.route(),
                    navOptions = if (navigation.popUpToStartDestination) {
                        navOptions {
                            popUpTo(navHostController.graph.findStartDestination().id) {
                                inclusive = true
                            }
                        }
                    } else {
                        navigation.navOptions
                    },
                )
        }
    }
}

@Composable
fun rememberNavigationHandler(
    navHostController: NavHostController = rememberNavController(),
): NavigationHandler {
    return remember(navHostController) {
        NavigationHandler(
            navHostController = navHostController,
        )
    }
}

val LocalNavigationHandler: ProvidableCompositionLocal<NavigationHandler> = staticCompositionLocalOf {
    noLocalProviderFor("LocalNavigationHandler")
}

