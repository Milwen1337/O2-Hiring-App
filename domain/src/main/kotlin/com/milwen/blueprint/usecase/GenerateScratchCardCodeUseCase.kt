package com.milwen.blueprint.usecase

import Config.GenerateScratchCardUUID.GENERATE_DELAY_IN_MS
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.UUID
import javax.inject.Inject

class GenerateScratchCardCodeUseCase @Inject constructor() {
    suspend fun generate(): Flow<String> = callbackFlow {
        delay(GENERATE_DELAY_IN_MS)
        trySend(generateUUID())
        awaitClose {  }
    }

    private fun generateUUID(): String = UUID.randomUUID().toString()
}