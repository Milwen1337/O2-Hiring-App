package com.milwen.blueprint.base.navigation

import android.net.Uri
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.navigation.NavType
import android.util.Base64

const val ARG_PAYLOAD = "payload"

interface Payload : Parcelable

class PayloadNavType<T : Payload>(private val clazz: Class<T>) : NavType<T>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): T {
        return value.decode(clazz)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }
}

fun Payload.encodeToString(): String {
    val parcel = Parcel.obtain().apply {
        writeParcelable(this@encodeToString, 0)
    }

    val result: String = Uri.encode(Base64.encodeToString(parcel.marshall(), Base64.DEFAULT))

    parcel.recycle()

    return result
}

fun <T: Payload> String.decode(clazz: Class<T>): T {
    val bytes = Base64.decode(Uri.decode(this), Base64.DEFAULT)

    val parcel = Parcel.obtain().apply {
        unmarshall(bytes, 0, bytes.size)
        setDataPosition(0)
    }

    val result: T? = parcel.readParcelable(clazz.classLoader)

    parcel.recycle()

    return requireNotNull(result) {
        "Payload encountered deserialization error for class ${clazz.simpleName}."
    }
}