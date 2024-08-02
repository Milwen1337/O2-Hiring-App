package com.milwen.blueprint.ui.nav

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.milwen.blueprint.ui.architecture.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override val viewModel: MainNavigationViewModel by viewModels()

    override val screen: @Composable () -> Unit = {
        MainNavigation(viewModel = viewModel)
    }
}