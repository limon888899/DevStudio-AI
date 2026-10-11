package com.devstudio.ai.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.devstudio.ai.data.auth.AuthTokenManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val tokenManager: AuthTokenManager
) : ViewModel() {

    private val _selectedModel = MutableStateFlow("GPT-4o")
    val selectedModel: StateFlow<String> = _selectedModel.asStateFlow()

    private val _gitHubToken = MutableStateFlow("")
    val gitHubToken: StateFlow<String> = _gitHubToken.asStateFlow()

    private val _vercelKey = MutableStateFlow("")
    val vercelKey: StateFlow<String> = _vercelKey.asStateFlow()

    private val _openaiKey = MutableStateFlow("")
    val openaiKey: StateFlow<String> = _openaiKey.asStateFlow()

    private val _darkMode = MutableStateFlow(true)
    val darkMode: StateFlow<Boolean> = _darkMode.asStateFlow()

    fun selectModel(model: String) {
        _selectedModel.value = model
    }

    fun saveGitHubToken(token: String) {
        viewModelScope.launch {
            tokenManager.saveSecret("github_token", token)
            _gitHubToken.value = token
        }
    }

    fun saveVercelKey(key: String) {
        viewModelScope.launch {
            tokenManager.saveSecret("vercel_key", key)
            _vercelKey.value = key
        }
    }

    fun saveOpenAIKey(key: String) {
        viewModelScope.launch {
            tokenManager.saveSecret("openai_key", key)
            _openaiKey.value = key
        }
    }

    fun toggleDarkMode() {
        _darkMode.value = !_darkMode.value
    }
}

class SettingsViewModelFactory(
    private val tokenManager: AuthTokenManager
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingsViewModel(tokenManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
