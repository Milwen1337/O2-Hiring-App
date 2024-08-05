package com.milwen.blueprint.ui.screen.scratch

import com.milwen.blueprint.model.ScratchCardModel
import com.milwen.blueprint.repository.ScratchCardRepository
import com.milwen.blueprint.ui.architecture.BaseViewModel
import com.milwen.blueprint.ui.model.ProgressState
import com.milwen.blueprint.ui.model.ScratchCardUiState
import com.milwen.blueprint.ui.model.ScratchUiModel
import com.milwen.blueprint.ui.model.toScratchCardState
import com.milwen.blueprint.usecase.GenerateScratchCardCodeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScratchViewModel @Inject constructor(
    private val scratchCardRepository: ScratchCardRepository,
    private val generateScratchCardCodeUseCase: GenerateScratchCardCodeUseCase,
) : BaseViewModel() {

    private val _state: MutableStateFlow<ScratchUiModel> = MutableStateFlow(ScratchUiModel())
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        launch {
            scratchCardRepository.getScratchCard().let { model ->
                model?.let {
                    _state.update {
                        ScratchUiModel(
                            cardId = model.id,
                            scratchCardUiState = model.state.toScratchCardState(),
                        )
                    }
                }
            }
        }
    }

    fun scratchCard(){
        _state.update { it.copy(progressState = ProgressState.InProgress) }
        launch {
            generateScratchCardCodeUseCase.generate().collect { scratchId ->
                _state.update { it.copy(cardId = scratchId, scratchCardUiState = ScratchCardUiState.Scratched(), progressState = ProgressState.Finished) }
                scratchCardRepository.setScratchCard(ScratchCardModel(state.value.cardId, state.value.scratchCardUiState.toScratchCardState()))
            }
        }
    }

}