package com.milwen.blueprint.repository

import com.milwen.blueprint.model.ScratchCardActivationResponseModel
import com.milwen.blueprint.model.ScratchCardModel
import kotlinx.coroutines.flow.Flow

interface ScratchCardRepository {

    fun getScratchCard(): ScratchCardModel?

    fun setScratchCard(scratchCard: ScratchCardModel)

    suspend fun activateScratchCard(code: String): ScratchCardActivationResponseModel
}