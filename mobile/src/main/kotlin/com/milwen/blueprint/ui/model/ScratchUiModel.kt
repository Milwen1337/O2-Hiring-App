package com.milwen.blueprint.ui.model

import androidx.compose.runtime.Immutable
import com.milwen.blueprint.model.ScratchCardState

@Immutable
data class ScratchUiModel(
    var cardId: String? = null,
    var scratchCardUiState: ScratchCardUiState = ScratchCardUiState.Unscratched(),
    var progressState: ProgressState = ProgressState.Finished
)

sealed interface ScratchCardUiState {
    val value: String
    data class Unscratched(override val value: String = "unscratched"): ScratchCardUiState


    data class Scratched(override val value: String = "scratched"): ScratchCardUiState

    data class Activated(override val value: String = "activated"): ScratchCardUiState
}

sealed interface ProgressState {

    data object InProgress: ProgressState

    data object Finished: ProgressState

}

fun ScratchCardState.toScratchCardState() =
    when(this) {
        ScratchCardState.Unscratched -> ScratchCardUiState.Unscratched()
        ScratchCardState.Scratched -> ScratchCardUiState.Scratched()
        ScratchCardState.Activated -> ScratchCardUiState.Activated()
    }

fun ScratchCardUiState.toScratchCardState() =
    when(this) {
        is ScratchCardUiState.Unscratched -> ScratchCardState.Unscratched
        is ScratchCardUiState.Scratched -> ScratchCardState.Scratched
        is ScratchCardUiState.Activated -> ScratchCardState.Activated
    }