package com.milwen.blueprint.usecase

import Config.GenerateScratchCardUUID.GENERATE_DELAY_IN_MS
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.UUID

class GenerateScratchCardCodeUseCase {
    suspend fun generate(): Flow<String> = callbackFlow {
        delay(GENERATE_DELAY_IN_MS)
        trySend(generateUUID())
    }

    private fun generateUUID(): String = UUID.randomUUID().toString()
}