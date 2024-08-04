package com.milwen.blueprint.ui.screen.activation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
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
import com.milwen.blueprint.ui.model.ActivationState
import com.milwen.blueprint.ui.model.ActivationUiModel
import com.milwen.blueprint.ui.model.ScratchCardUiState
import com.milwen.blueprint.ui.nav.MainDestination

@Immutable
private interface ActivationScreenListener {
    fun onActivationClick(cardId: String)

    fun onActivationFinished()
}

@Composable
fun ActivationScreen(
    viewModel: ActivationViewModel,
) {
    val data = viewModel.state.collectAsStateWithLifecycle()
    val navigationHandler = LocalNavigationHandler.current

    val listener = object : ActivationScreenListener {
        override fun onActivationClick(cardId: String) {
            viewModel.activateCard(cardId)
        }

        override fun onActivationFinished() {
            navigationHandler.navigate(
                Navigation.Destination(
                destination = MainDestination.Menu,
            ))
        }
    }

    MainScreen(
        title = "Activation Screen"
    ) {
        ActivationScreenContent(
            state = data.value,
            listener = listener,
        )
    }
}

@Composable
private fun ActivationScreenContent(
    state: ActivationUiModel,
    listener: ActivationScreenListener,
) {
    Column (
        modifier = Modifier
            .navigationBarsPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        CardState(state.scratchCardUiState.value)

        when(state.activationState){
            ActivationState.Default -> {
                state.cardId?.let { cardId ->
                    ActivateButton(
                        text = "Activate Card",
                        onClick = { listener.onActivationClick(cardId) })
                }
            }
            ActivationState.InProgress -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(26.dp)
                )
            }
            ActivationState.Finished -> {
                listener.onActivationFinished()
            }
        }

    }
}

@Composable
private fun CardState(
    text: String
) {
    Text(text = text)
}

@Composable
private fun ActivateButton(
    text: String,
    onClick: () -> Unit,
) {

    PrimaryButton(
        text = text,
        onClick = { onClick.invoke() },
    )
}

@Preview(name = "PreviewActivationScreenContent", group = "App Screen")
@Composable
private fun PreviewActivationScreen(){
    ThemedPreview {
        ActivationScreenContent(
            state = ActivationUiModel(cardId = "222-332-2333", scratchCardUiState = ScratchCardUiState.Scratched()),
            listener = object : ActivationScreenListener {
                override fun onActivationClick(cardId: String) {}
                override fun onActivationFinished() {}
            }
        )
    }
}