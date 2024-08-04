package com.milwen.blueprint.ui.screen.scratch

import androidx.lifecycle.SavedStateHandle
import com.milwen.blueprint.base.navigation.ARG_PAYLOAD
import com.milwen.blueprint.base.navigation.Payload
import com.milwen.blueprint.ui.architecture.BaseViewModel
import com.milwen.blueprint.ui.model.ScratchUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class ScratchCardPayload(
    val id: String,
    val state: Int,
) : Payload

@HiltViewModel
class ScratchViewModel @Inject constructor(
    savedState: SavedStateHandle,
) : BaseViewModel() {

    private val payload = requireNotNull(savedState.get<ScratchCardPayload>(ARG_PAYLOAD))

    private val _state: MutableStateFlow<ScratchUiModel> = MutableStateFlow(ScratchUiModel())
    val state = _state.asStateFlow()


}