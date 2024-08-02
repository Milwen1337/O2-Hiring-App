package com.milwen.blueprint.base.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

private fun NavGraphBuilder.destination(
    destination: NavDestination,
    navHostController: NavHostController,
) {
    composable(
        route = destination.route(),
        arguments = destination.arguments(),
        enterTransition = destination.enterTransition(),
        exitTransition = destination.exitTransition(),
        popEnterTransition = destination.popEnterTransition(),
        popExitTransition = destination.popExitTransition(),
    ) {
        destination.content(navHostController)
    }
}
@Composable
fun NavHost(
    navHostController: NavHostController,
    destinations: List<Nav>,
    startDestination: Nav,
    modifier: Modifier,
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
        { fadeIn(animationSpec = tween(500)) },
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
        { fadeOut(animationSpec = tween(500)) },
    popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
        enterTransition,
    popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
       exitTransition,
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination.route(),
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        modifier = modifier
    ) {
        destinations.forEach { destination ->
            when (destination) {
                is NavNagivation -> {
                    navigation(
                        route = destination.route(),
                        startDestination = destination.destinations.first().route()
                    ) {
                        destination.destinations.forEach { childDestination ->
                            destination(
                                destination = childDestination,
                                navHostController =  navHostController,
                            )
                        }
                    }
                }
                is NavDestination -> {
                    destination(
                        destination = destination,
                        navHostController = navHostController,
                    )
                }
            }
        }
    }
}