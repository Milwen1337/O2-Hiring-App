package com.milwen.blueprint.ui.architecture

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.milwen.blueprint.base.composable.noLocalProviderFor
import com.milwen.blueprint.ui.theme.MainTheme

abstract class BaseActivity : ComponentActivity() {

    abstract val viewModel: BaseViewModel
    abstract val screen: @Composable () -> Unit

    @Composable
    open fun isDarkTheme(): Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupScreen()
    }

    private fun setupScreen() {
        setContent {
            CompositionLocalProvider(
                value = LocalActivity provides this
            ) {
                MainTheme(
                    isDark = isDarkTheme()
                ) {
                    screen()
                }
            }
        }
    }
}

val LocalActivity: ProvidableCompositionLocal<BaseActivity> = staticCompositionLocalOf {
    noLocalProviderFor("LocalActivity")
}