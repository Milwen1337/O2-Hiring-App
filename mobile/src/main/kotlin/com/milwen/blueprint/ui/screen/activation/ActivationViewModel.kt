package com.milwen.blueprint.ui.screen.activation

import android.util.Log
import com.milwen.blueprint.model.ScratchCardModel
import com.milwen.blueprint.repository.ScratchCardRepository
import com.milwen.blueprint.ui.architecture.BaseViewModel
import com.milwen.blueprint.ui.model.ActivationState
import com.milwen.blueprint.ui.model.ActivationUiModel
import com.milwen.blueprint.ui.model.ScratchCardUiState
import com.milwen.blueprint.ui.model.toScratchCardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivationViewModel @Inject constructor(
    private val scratchCardRepository: ScratchCardRepository,
) : BaseViewModel() {

    private val _state: MutableStateFlow<ActivationUiModel> = MutableStateFlow(ActivationUiModel())
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        launch {
            scratchCardRepository.getScratchCard().let { model ->
                model?.let {
                    _state.update {
                        it.copy(cardId = model.id, scratchCardUiState = model.state.toScratchCardState())
                    }
                }
            }
        }
    }

    fun activateCard(code: String) {
        _state.update { it.copy(activationState = ActivationState.InProgress) }
        Log.i("ViewModel", "ActivationViewModel: activateCard: inProgress")
        launch {
            val response = scratchCardRepository.activateScratchCard(code)
            Log.i("ViewModel", "ActivationViewModel: activateCard: response")
            if (response.isActivated){
                Log.i("ViewModel", "ActivationViewModel: activateCard: response -> isActivated")
                _state.update { it.copy(scratchCardUiState = ScratchCardUiState.Activated()) }

                scratchCardRepository.setScratchCard(ScratchCardModel(state.value.cardId, state.value.scratchCardUiState.toScratchCardState()))
            } else {
                Log.i("ViewModel", "ActivationViewModel: activateCard: response -> isNotActivated")
                _state.update { it.copy(showError = true) }
            }
            _state.update { it.copy(activationState = ActivationState.Finished) }
        }
    }

    fun dismissError(){
        _state.update { it.copy(showError = false) }
    }

}