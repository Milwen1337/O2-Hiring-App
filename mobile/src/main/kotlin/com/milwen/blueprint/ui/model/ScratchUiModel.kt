package com.milwen.blueprint.ui.model

import androidx.compose.runtime.Immutable
import com.milwen.blueprint.model.ScratchCardState

@Immutable
data class ScratchUiModel(
    var cardId: String? = null,
    var scratchCardUiState: ScratchCardUiState = ScratchCardUiState.Unscratched
)

sealed interface ScratchCardUiState {

    data object Unscratched: ScratchCardUiState

    data object Scratched: ScratchCardUiState

    data object Activated: ScratchCardUiState
}

fun ScratchCardState.toScratchCardState() =
    when(this) {
        ScratchCardState.Unscratched -> ScratchCardUiState.Unscratched
        ScratchCardState.Scratched -> ScratchCardUiState.Scratched
        ScratchCardState.Activated -> ScratchCardUiState.Activated
    }

fun ScratchCardUiState.toScratchCardState() =
    when(this) {
        ScratchCardUiState.Unscratched -> ScratchCardState.Unscratched
        ScratchCardUiState.Scratched -> ScratchCardState.Scratched
        ScratchCardUiState.Activated -> ScratchCardState.Activated
    }