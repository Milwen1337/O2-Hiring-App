package com.milwen.blueprint.core.repository

import com.milwen.blueprint.core.api.ScratchCardActivationAPI
import com.milwen.blueprint.core.db.ScratchCardPreferences
import com.milwen.blueprint.model.ScratchCardActivationResponseModel
import com.milwen.blueprint.model.ScratchCardModel
import com.milwen.blueprint.repository.ScratchCardRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScratchCardRepositoryImpl @Inject constructor(
    private val preferences: ScratchCardPreferences,
    private val activationAPI: ScratchCardActivationAPI,
): ScratchCardRepository {

    private val _scratchCardStateFlow = MutableStateFlow(preferences.getScratchCard())
    val scratchCardStateFlow: StateFlow<ScratchCardModel?> = _scratchCardStateFlow

    override fun getScratchCard(): ScratchCardModel? = preferences.getScratchCard()

    override suspend fun getScratchCardFlow(): Flow<ScratchCardModel?> = scratchCardStateFlow

    override fun setScratchCard(scratchCard: ScratchCardModel) {
        preferences.setScratchCard(scratchCard)
        _scratchCardStateFlow.value = scratchCard
    }

    override suspend fun activateScratchCard(code: String): ScratchCardActivationResponseModel {
        return try {
            val response = activationAPI.activateScratchCard(code)
                .catch { _ ->
                    ScratchCardActivationResponseModel(isActivated = false)
                }
                .firstOrNull()

            response?.let {
                ScratchCardActivationResponseModel(isActivated = it.android > 277028)
            } ?: ScratchCardActivationResponseModel(isActivated = false)

        } catch (e: Exception) {
            ScratchCardActivationResponseModel(isActivated = false)
        }
    }

}

