package com.milwen.blueprint.ui.model

import androidx.compose.runtime.Immutable

@Immutable
data class ActivationUiModel(
    var cardId: String? = null,
    var showWarning: Boolean = false,
    var scratchCardUiState: ScratchCardUiState = ScratchCardUiState.Unscratched(),
    var activationState: ActivationState = ActivationState.Default,
)

sealed interface ActivationState {

    data object Default: ActivationState

    data object InProgress: ActivationState

    data object Finished: ActivationState

}