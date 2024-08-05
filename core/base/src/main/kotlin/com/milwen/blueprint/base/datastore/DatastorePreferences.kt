package com.milwen.blueprint.base.datastore

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder

open class DatastorePreferences(
    private val context: Context,
    private val name: String,
    protected val gson: Gson = GsonBuilder().create()
){
    protected val sharedPreferences: SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
}