package com.milwen.blueprint.core.api

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

data class ActivationResponse(
    val android: String
)

interface ApiService {
    @GET("version")
    suspend fun getVersion(
        @Query("code") code: String
    ): ActivationResponse
}

@Singleton
class ScratchCardActivationAPI @Inject constructor(
    private val apiService: ApiService,
){
    suspend fun activateScratchCard(code: String): Flow<ActivationResponse> = flow {
        try {
            val response = apiService.getVersion(code)
            emit(response)
        } catch (e: Exception) {
            throw e
        }
    }.flowOn(Dispatchers.IO)

}