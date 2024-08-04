package com.milwen.blueprint.core.repository

import com.milwen.blueprint.core.db.ScratchCardPreferences
import com.milwen.blueprint.model.ScratchCardModel
import com.milwen.blueprint.repository.ScratchCardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ScratchCardRepositoryImpl @Inject constructor(
    private val preferences: ScratchCardPreferences,
): ScratchCardRepository {
    override fun getScratchCard(): Flow<ScratchCardModel?> = callbackFlow {
        preferences.getScratchCard()
    }

    override fun setScratchCard(scratchCard: ScratchCardModel) {
        preferences.setScratchCard(scratchCard)
    }

}

