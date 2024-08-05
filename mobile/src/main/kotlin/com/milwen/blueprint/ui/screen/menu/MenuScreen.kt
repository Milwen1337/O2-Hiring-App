package com.milwen.blueprint.ui.screen.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.milwen.blueprint.base.navigation.LocalNavigationHandler
import com.milwen.blueprint.base.navigation.Navigation
import com.milwen.blueprint.ui.compose.MainScreen
import com.milwen.blueprint.ui.compose.PrimaryButton
import com.milwen.blueprint.ui.compose.ThemedPreview
import com.milwen.blueprint.ui.model.MenuUiModel
import com.milwen.blueprint.ui.model.ScratchCardUiState
import com.milwen.blueprint.ui.nav.MainDestination

@Immutable
private interface MenuScreenListener {
    fun onScratchClick()
    fun onActivationClick()
}

@Composable
fun MenuScreen(
    viewModel: MenuViewModel,
) {
    val data = viewModel.state.collectAsStateWithLifecycle()
    val navigationHandler = LocalNavigationHandler.current

    val listener = remember(navigationHandler) {
        object : MenuScreenListener {
            override fun onScratchClick() {
                navigationHandler.navigate(Navigation.Destination(
                    destination = MainDestination.Scratch,
                ))
            }

            override fun onActivationClick() {
                navigationHandler.navigate(Navigation.Destination(
                    destination = MainDestination.Activation,
                ))
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }

    MainScreen(
        title = "Menu"
    ) {
        MenuScreenContent(
            state = data.value,
            listener = listener,
        )
    }
}

@Composable
private fun MenuScreenContent(
    state: MenuUiModel,
    listener: MenuScreenListener,
) {
    Column (
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        CardState(state.scratchCardUiState.value)

        MenuButton(
            text = "Scratch Screen",
            onClick = { listener.onScratchClick() })

        MenuButton(
            text = "Activation Screen",
            onClick = { listener.onActivationClick() })
    }
}

@Composable
private fun CardState(
    text: String
) {
    Text(text = text)
}

@Composable
private fun MenuButton(
    text: String,
    onClick: () -> Unit,
) {

    PrimaryButton(
        text = text,
        onClick = { onClick.invoke() },
    )
}

@Preview(name = "PreviewMenuScreenContent", group = "App Screen")
@Composable
private fun PreviewMenuScreen(){
    ThemedPreview {
        MenuScreenContent(
            state = MenuUiModel(scratchCardUiState = ScratchCardUiState.Activated()),
            listener = object : MenuScreenListener {
                override fun onScratchClick() {}
                override fun onActivationClick() {}
            }
        )
    }
}