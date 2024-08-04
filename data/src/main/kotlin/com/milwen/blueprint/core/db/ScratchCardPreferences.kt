package com.milwen.blueprint.core.db

import android.content.Context
import com.milwen.blueprint.base.datastore.DatastorePreferences
import com.milwen.blueprint.model.ScratchCardModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

private const val DATASTORE_NAME = "scratch_card"
private const val SCRATCH_CARD_KEY = "scratch_card_key"

@Singleton
class ScratchCardPreferences @Inject constructor(
    @ApplicationContext context: Context,
): DatastorePreferences(context, DATASTORE_NAME) {

    fun getScratchCard(): ScratchCardModel? {
        val jsonString = sharedPreferences.getString(SCRATCH_CARD_KEY, null)
        return jsonString?.let { gson.fromJson(it, ScratchCardModel::class.java) }
    }

    fun setScratchCard(scratchCardModel: ScratchCardModel) {
        val jsonString = gson.toJson(scratchCardModel)
        sharedPreferences.edit().putString(SCRATCH_CARD_KEY, jsonString).apply()
    }

    fun clearScratchCard() {
        sharedPreferences.edit().remove(SCRATCH_CARD_KEY).apply()
    }

}