package com.milwen.blueprint.ui.screen.menu

import com.milwen.blueprint.repository.ScratchCardRepository
import com.milwen.blueprint.ui.architecture.BaseViewModel
import com.milwen.blueprint.ui.model.MenuUiModel
import com.milwen.blueprint.ui.model.toScratchCardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val scratchCardRepository: ScratchCardRepository,
) : BaseViewModel() {

    private val _state: MutableStateFlow<MenuUiModel> = MutableStateFlow(MenuUiModel())
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        launch {
            scratchCardRepository.getScratchCard().let { model ->
                model?.let {
                    _state.update {
                        MenuUiModel(
                            scratchCardUiState = model.state.toScratchCardState(),
                        )
                    }
                }
            }
        }
    }

}