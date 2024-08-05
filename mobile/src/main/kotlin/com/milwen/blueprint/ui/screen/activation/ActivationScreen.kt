package com.milwen.blueprint.ui.screen.activation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.milwen.blueprint.base.navigation.LocalNavigationHandler
import com.milwen.blueprint.base.navigation.Navigation
import com.milwen.blueprint.ui.compose.AppBarNavigation
import com.milwen.blueprint.ui.compose.MainScreen
import com.milwen.blueprint.ui.compose.PrimaryButton
import com.milwen.blueprint.ui.compose.ThemedPreview
import com.milwen.blueprint.ui.compose.rememberBackHandler
import com.milwen.blueprint.ui.model.ActivationState
import com.milwen.blueprint.ui.model.ActivationUiModel
import com.milwen.blueprint.ui.model.ScratchCardUiState
import com.milwen.blueprint.ui.nav.MainDestination

@Immutable
private interface ActivationScreenListener {
    fun onActivationClick(cardId: String)

    fun onActivationFinished()
    fun onErrorDismiss()
}

@Composable
fun ActivationScreen(
    viewModel: ActivationViewModel,
) {
    val data = viewModel.state.collectAsStateWithLifecycle()
    val backHandler = rememberBackHandler()
    val navigationHandler = LocalNavigationHandler.current

    val listener = remember(navigationHandler) {
        object : ActivationScreenListener {
            override fun onActivationClick(cardId: String) {
                viewModel.activateCard(cardId)
            }

            override fun onActivationFinished() {
                navigationHandler.navigate(Navigation.Back)
            }

            override fun onErrorDismiss() {
                viewModel.dismissError()
            }
        }
    }

    MainScreen(
        title = "Activation Screen",
        navigation = AppBarNavigation.Button.Up(backHandler::navigateBack)
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

        when(state.activationState) {
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

        if (state.showError) {
            ErrorDialog(
                errorMessage = "Activation Failed!",
                onDismiss = { listener.onErrorDismiss() },
            )
        }

    }
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

@Composable
fun ErrorDialog(
    errorMessage: String?,
    onDismiss: () -> Unit
) {
    if (errorMessage != null) {
        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text("Error") },
            text = { Text(errorMessage) },
            confirmButton = {
                Button(onClick = { onDismiss() }) {
                    Text("OK")
                }
            }
        )
    }
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
                override fun onErrorDismiss() {}
            }
        )
    }
}