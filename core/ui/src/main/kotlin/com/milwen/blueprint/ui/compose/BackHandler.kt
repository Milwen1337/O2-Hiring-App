package com.milwen.blueprint.ui.compose

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

interface BackHandler {
    fun navigateBack()
}

@Composable
fun rememberBackHandler() : BackHandler {
    val backPressedDispatcher = LocalOnBackPressedDispatcherOwner.current
    return remember(backPressedDispatcher) {
        object : BackHandler {
            override fun navigateBack() {
                backPressedDispatcher?.onBackPressedDispatcher?.onBackPressed()
            }
        }
    }
}