package com.milwen.blueprint.base.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier

@Composable
fun Navigation(
    destinations: List<Nav>,
    startDestination: Nav,
    modifier: Modifier = Modifier,
    navigationHandler: NavigationHandler = rememberNavigationHandler(),
) {
    CompositionLocalProvider(
        value = LocalNavigationHandler provides navigationHandler,
    ) {
        NavHost(
            navHostController = navigationHandler.navHostController,
            destinations = destinations,
            startDestination = startDestination,
            modifier = modifier,
        )
    }
}