package com.milwen.blueprint.repository

import com.milwen.blueprint.model.ScratchCardModel
import kotlinx.coroutines.flow.Flow

interface ScratchCardRepository {

    fun getScratchCard(): Flow<ScratchCardModel?>

    fun setScratchCard(scratchCard: ScratchCardModel)
}