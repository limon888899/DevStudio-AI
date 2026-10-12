package com.devstudio.ai.data.ai

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.devstudio.ai.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

object AiKeyStore {
    private val keyName = stringPreferencesKey("ai_api_key")

    suspend fun load(context: Context): String =
        context.applicationContext.dataStore.data.map { it[keyName] ?: "" }.first()

    suspend fun save(context: Context, value: String) {
        context.applicationContext.dataStore.edit { it[keyName] = value.trim() }
    }
}
