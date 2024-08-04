package com.milwen.blueprint.ui.screen.scratch

import com.milwen.blueprint.model.ScratchCardModel
import com.milwen.blueprint.repository.ScratchCardRepository
import com.milwen.blueprint.ui.architecture.BaseViewModel
import com.milwen.blueprint.ui.model.ScratchCardUiState
import com.milwen.blueprint.ui.model.ScratchUiModel
import com.milwen.blueprint.ui.model.toScratchCardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ScratchViewModel @Inject constructor(
    private val scratchCardRepository: ScratchCardRepository,
) : BaseViewModel() {

    private val _state: MutableStateFlow<ScratchUiModel> = MutableStateFlow(ScratchUiModel())
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        launch {
            scratchCardRepository.getScratchCard().collect { model->
                model?.let {
                    state.value.apply {
                        cardId = model.id
                        scratchCardUiState = model.state.toScratchCardState()
                    }
                }
            }
        }
    }

    fun scratchCard(){
        state.value.scratchCardUiState = ScratchCardUiState.Scratched
        scratchCardRepository.setScratchCard(ScratchCardModel(state.value.cardId, state.value.scratchCardUiState.toScratchCardState()))
    }

}