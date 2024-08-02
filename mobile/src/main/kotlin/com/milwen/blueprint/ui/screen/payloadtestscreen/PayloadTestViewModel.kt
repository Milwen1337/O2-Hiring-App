package com.milwen.blueprint.ui.screen.payloadtestscreen

import androidx.lifecycle.SavedStateHandle
import com.milwen.blueprint.base.navigation.ARG_PAYLOAD
import com.milwen.blueprint.base.navigation.Payload
import com.milwen.blueprint.ui.architecture.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class PayloadTestPayload(
    val name: String,
    val amount: Int,
) : Payload

@HiltViewModel
class PayloadTestViewModel @Inject constructor(
    savedState: SavedStateHandle,
) : BaseViewModel() {

    private val payload = requireNotNull(savedState.get<PayloadTestPayload>(ARG_PAYLOAD))

}