package com.milwen.blueprint.model

sealed interface ScratchCardState {
    val value: String
    data object Unscratched: ScratchCardState {
        override val value: String = "unscratched"
    }

    data object Scratched: ScratchCardState {
        override val value: String = "scratched"
    }

    data object Activated: ScratchCardState {
        override val value: String = "activated"
    }
}

fun String?.toScratchCardState() =
    when(this) {
        ScratchCardState.Unscratched.value -> ScratchCardState.Unscratched
        ScratchCardState.Scratched.value -> ScratchCardState.Scratched
        ScratchCardState.Activated.value -> ScratchCardState.Activated
        else -> ScratchCardState.Unscratched
    }