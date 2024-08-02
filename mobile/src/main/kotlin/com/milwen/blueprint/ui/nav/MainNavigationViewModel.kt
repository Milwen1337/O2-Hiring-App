package com.milwen.blueprint.ui.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.milwen.blueprint.base.navigation.NavDestination
import com.milwen.blueprint.base.navigation.NavDestinationPayload
import com.milwen.blueprint.ui.architecture.BaseViewModel
import com.milwen.blueprint.ui.screen.intro.IntroScreen
import com.milwen.blueprint.ui.screen.payloadtestscreen.PayloadTestPayload
import com.milwen.blueprint.ui.screen.payloadtestscreen.PayloadTestScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

object MainDestination {

    data object Intro : NavDestination {
        override val path: String = "intro"
        override val content: @Composable (navHostController: NavHostController) -> Unit = {
            IntroScreen(hiltViewModel())
        }
    }

    data object PayloadTest : NavDestinationPayload<PayloadTestPayload> {
        override val path: String = "payload_test"
        override val content: @Composable (navHostController: NavHostController) -> Unit = {
            PayloadTestScreen(hiltViewModel())
        }
        override val payloadClazz: Class<PayloadTestPayload> = PayloadTestPayload::class.java
    }
}

@HiltViewModel
class MainNavigationViewModel @Inject constructor(

) : BaseViewModel() {

    val destinations = listOf(
        MainDestination.Intro,
        MainDestination.PayloadTest,
    )

    val startDestination = destinations.first()

}