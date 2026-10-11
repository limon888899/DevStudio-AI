package com.devstudio.ai

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "devstudio_ai_auth")

class AuthViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

class AuthViewModel(context: Context) : ViewModel() {
    private val dataStore = context.applicationContext.dataStore
    private val tokenKey = stringPreferencesKey("jwt_token")

    private val _authState = MutableStateFlow<AuthState>(AuthState.Loading)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    init {
        viewModelScope.launch {
            dataStore.data.map { preferences ->
                preferences[tokenKey]
            }.collect { token ->
                _authState.value = if (token.isNullOrBlank()) AuthState.Unauthenticated else AuthState.Authenticated
            }
        }
    }

    suspend fun login(email: String, password: String): Boolean {
        return if (email.contains("@") && password.length >= 6) {
            dataStore.edit { prefs ->
                prefs[tokenKey] = "demo_jwt_token_${email.hashCode()}"
            }
            _authState.value = AuthState.Authenticated
            true
        } else {
            false
        }
    }

    suspend fun saveToken(token: String) {
        if (token.isNotBlank()) {
            dataStore.edit { prefs ->
                prefs[tokenKey] = token
            }
            _authState.value = AuthState.Authenticated
        }
    }

    suspend fun logout() {
        dataStore.edit { prefs ->
            prefs.remove(tokenKey)
        }
        _authState.value = AuthState.Unauthenticated
    }

    suspend fun isLoggedIn(): Boolean {
        return dataStore.data.map { it[tokenKey] }.first() != null
    }
}

sealed interface AuthState {
    data object Loading : AuthState
    data object Authenticated : AuthState
    data object Unauthenticated : AuthState
}
