package com.milwen.blueprint.core.db

import com.google.gson.*
import com.milwen.blueprint.model.ScratchCardState
import java.lang.reflect.Type

class ScratchCardStateTypeAdapter : JsonDeserializer<ScratchCardState>, JsonSerializer<ScratchCardState> {

    override fun serialize(src: ScratchCardState?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return JsonPrimitive(src?.value)
    }

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): ScratchCardState {
        return when (json) {
            is JsonPrimitive -> {
                val value = json.asString
                when (value) {
                    ScratchCardState.Unscratched.value -> ScratchCardState.Unscratched
                    ScratchCardState.Scratched.value -> ScratchCardState.Scratched
                    ScratchCardState.Activated.value -> ScratchCardState.Activated
                    else -> ScratchCardState.Unscratched
                }
            }
            else -> ScratchCardState.Unscratched
        }
    }
}

