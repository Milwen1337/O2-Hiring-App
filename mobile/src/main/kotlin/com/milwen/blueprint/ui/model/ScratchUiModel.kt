package com.milwen.blueprint.ui.model

import androidx.compose.runtime.Immutable

@Immutable
data class ScratchUiModel(
    val cardId: String? = null,
    val scratchCardState: ScratchCardState = ScratchCardState.Unscratched
)

sealed interface ScratchCardState {

    data object Unscratched: ScratchCardState

    data object Scratched: ScratchCardState

    data object Activated: ScratchCardState
}