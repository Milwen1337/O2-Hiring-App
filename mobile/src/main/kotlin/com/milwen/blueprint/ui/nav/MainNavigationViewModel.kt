package com.milwen.blueprint.ui.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.milwen.blueprint.base.navigation.NavDestination
import com.milwen.blueprint.base.navigation.NavDestinationPayload
import com.milwen.blueprint.ui.architecture.BaseViewModel
import com.milwen.blueprint.ui.screen.activation.ActivationScreen
import com.milwen.blueprint.ui.screen.menu.MenuScreen
import com.milwen.blueprint.ui.screen.scratch.ScratchScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

object MainDestination {

    /*
    data object PayloadTest : NavDestinationPayload<PayloadTestPayload> {
        override val path: String = "payload_test"
        override val content: @Composable (navHostController: NavHostController) -> Unit = {
            PayloadTestScreen(hiltViewModel())
        }
        override val payloadClazz: Class<PayloadTestPayload> = PayloadTestPayload::class.java
    }*/

    data object Menu : NavDestination {
        override val path: String = "menu"
        override val content: @Composable (navHostController: NavHostController) -> Unit = {
            MenuScreen(hiltViewModel())
        }
    }

    data object Scratch : NavDestination {
        override val path: String = "scratch"
        override val content: @Composable (navHostController: NavHostController) -> Unit = {
            ScratchScreen(hiltViewModel())
        }
    }

    data object Activation : NavDestination {
        override val path: String = "activation"
        override val content: @Composable (navHostController: NavHostController) -> Unit = {
            ActivationScreen(hiltViewModel())
        }
    }
}

@HiltViewModel
class MainNavigationViewModel @Inject constructor(

) : BaseViewModel() {

    val destinations = listOf(
        MainDestination.Menu,
        MainDestination.Scratch,
        MainDestination.Activation,
    )

    val startDestination = destinations.first()

}