package com.milwen.blueprint.ui.model

import androidx.compose.runtime.Immutable

@Immutable
data class MenuUiModel(
    var scratchCardUiState: ScratchCardUiState = ScratchCardUiState.Unscratched(),
)