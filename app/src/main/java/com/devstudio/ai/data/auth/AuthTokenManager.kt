package com.devstudio.ai.data.auth

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "devstudio_ai_secure_auth")

class AuthTokenManager(private val context: Context) {
    private val tokenKey = stringPreferencesKey("jwt_token")
    private val userEmailKey = stringPreferencesKey("user_email")
    private val userNameKey = stringPreferencesKey("user_name")
    private val githubTokenKey = stringPreferencesKey("github_token")
    private val vercelKeyKey = stringPreferencesKey("vercel_api_key")
    private val openaiKeyKey = stringPreferencesKey("openai_key")

    suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[tokenKey] = token
        }
    }

    fun getToken(): Flow<String?> = context.dataStore.data.map { it[tokenKey] }

    suspend fun saveUserData(email: String, name: String) {
        context.dataStore.edit { prefs ->
            prefs[userEmailKey] = email
            prefs[userNameKey] = name
        }
    }

    fun getUserEmail(): Flow<String?> = context.dataStore.data.map { it[userEmailKey] }
    fun getUserName(): Flow<String?> = context.dataStore.data.map { it[userNameKey] }

    suspend fun saveSecret(key: String, value: String) {
        context.dataStore.edit { prefs ->
            when (key) {
                "github_token" -> prefs[githubTokenKey] = value
                "vercel_key" -> prefs[vercelKeyKey] = value
                "openai_key" -> prefs[openaiKeyKey] = value
            }
        }
    }

    fun getSecret(key: String): Flow<String?> = context.dataStore.data.map { prefs ->
        when (key) {
            "github_token" -> prefs[githubTokenKey]
            "vercel_key" -> prefs[vercelKeyKey]
            "openai_key" -> prefs[openaiKeyKey]
            else -> null
        }
    }

    suspend fun clearAll() {
        context.dataStore.edit { it.clear() }
    }
}
