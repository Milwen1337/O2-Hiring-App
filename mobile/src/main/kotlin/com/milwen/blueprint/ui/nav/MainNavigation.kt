package com.milwen.blueprint.ui.nav

import androidx.compose.runtime.Composable
import com.milwen.blueprint.base.navigation.Navigation

@Composable
fun MainNavigation(viewModel: MainNavigationViewModel) {
    Navigation(
        destinations = viewModel.destinations,
        startDestination = viewModel.startDestination,
        )
}