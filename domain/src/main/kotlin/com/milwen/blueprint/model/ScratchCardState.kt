package com.milwen.blueprint.model

sealed interface ScratchCardState {

    data object Unscratched: ScratchCardState

    data object Scratched: ScratchCardState

    data object Activated: ScratchCardState
}