package com.milwen.blueprint.ui.screen.scratch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.milwen.blueprint.ui.compose.MainScreen
import com.milwen.blueprint.ui.compose.PrimaryButton
import com.milwen.blueprint.ui.compose.ThemedPreview
import com.milwen.blueprint.ui.model.ScratchCardUiState
import com.milwen.blueprint.ui.model.ScratchUiModel
import com.milwen.blueprint.ui.theme.MainTheme

@Immutable
private interface ScratchCardListener {
    fun onCardScratch()
}

@Composable
fun ScratchScreen(
    viewModel: ScratchViewModel,
) {
    val data = viewModel.state.collectAsStateWithLifecycle()
    val listener = object : ScratchCardListener {
        override fun onCardScratch() {
            viewModel.scratchCard()
        }
    }

    MainScreen(
        title = "Scratch Card"
    ) {
        ScratchScreenContent(
            state = data.value,
            listener = listener,
        )
    }
}

@Composable
private fun ScratchScreenContent(
    state: ScratchUiModel,
    listener: ScratchCardListener,
) {
    Column (
      modifier = Modifier
          .navigationBarsPadding()
          .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ScratchCard(state = state)

        if (state.scratchCardUiState == ScratchCardUiState.Unscratched){
            ScratchButton(
                text = "Scratch",
                onClick = { listener.onCardScratch() },
            )
        }

    }
}

@Composable
private fun ScratchCard(
    state: ScratchUiModel,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = when(state.scratchCardUiState){
                ScratchCardUiState.Activated -> {
                    MainTheme.colors.brand.green
                }
                ScratchCardUiState.Scratched -> {
                    MainTheme.colors.brand.blue
                }
                ScratchCardUiState.Unscratched -> {
                    MainTheme.colors.surface.tertiary
                }
            },
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .heightIn(min = 56.dp),
    ){
        when(state.scratchCardUiState){
            ScratchCardUiState.Activated -> {
                ScratchText(text = state.cardId?:"")
            }
            ScratchCardUiState.Scratched -> {
                ScratchText(text = state.cardId?:"")
            }
            ScratchCardUiState.Unscratched -> {}
        }
    }
}

@Composable
private fun ScratchText(
    text: String,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun ScratchButton(
    text: String,
    onClick: () -> Unit,
) {

    PrimaryButton(
        text = text,
        onClick = { onClick.invoke() },
    )
}

@Preview(name = "ScratchScreenContent", group = "App Screen")
@Composable
private fun PreviewScratchScreen(){
    ThemedPreview {
        ScratchScreenContent(
            state = ScratchUiModel(cardId = "22238", scratchCardUiState = ScratchCardUiState.Activated),
            listener = object : ScratchCardListener {
                override fun onCardScratch() {}
            }
        )
    }
}
