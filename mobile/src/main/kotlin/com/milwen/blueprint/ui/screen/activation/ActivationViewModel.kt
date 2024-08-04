package com.milwen.blueprint.ui.screen.activation

import com.milwen.blueprint.repository.ScratchCardRepository
import com.milwen.blueprint.ui.architecture.BaseViewModel
import com.milwen.blueprint.ui.model.ActivationUiModel
import com.milwen.blueprint.ui.model.toScratchCardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    fun activateCard(code: String) {
        launch {
            val response = scratchCardRepository.activateScratchCard(code)
            if (response.isActivated){

            }
        }
    }

}