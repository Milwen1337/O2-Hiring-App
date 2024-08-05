package com.milwen.blueprint.ui.screen.menu

import android.util.Log
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

    fun loadData() {
        launch {
            scratchCardRepository.getScratchCard().let { model ->
                Log.i("ViewModel", "MenuViewModel: isNull = ${model == null}")
                model?.let {
                    Log.i("ViewModel", "MenuViewModel: MenuScratchCardState: ${model.state}")
                    _state.update {
                        it.copy(scratchCardUiState = model.state.toScratchCardState())
                    }
                }
            }
        }
    }

}