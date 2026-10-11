package com.devstudio.ai.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.devstudio.ai.data.models.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AssistantViewModel : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadInitialMessages()
    }

    private fun loadInitialMessages() {
        val initial = listOf(
            ChatMessage(
                id = "1",
                sender = "Assistant",
                text = "Hello! I'm your DevStudio AI Assistant. How can I help you build today?",
                timestamp = System.currentTimeMillis(),
                role = "assistant"
            )
        )
        _messages.value = initial
    }

    fun sendMessage(prompt: String) {
        viewModelScope.launch {
            _isLoading.value = true

            val userMessage = ChatMessage(
                id = System.currentTimeMillis().toString(),
                sender = "You",
                text = prompt,
                timestamp = System.currentTimeMillis(),
                role = "user"
            )

            _messages.value = _messages.value + userMessage

            // Simulate AI response
            val response = when {
                prompt.lowercase().contains("dashboard") -> "I'll generate an e-commerce dashboard with KPI tiles, sales charts, and product tables."
                prompt.lowercase().contains("fix") -> "Let me analyze the error and suggest fixes."
                prompt.lowercase().contains("deploy") -> "Deploying to Vercel... This will take a few moments."
                else -> "Processing your request..."
            }

            val assistantMessage = ChatMessage(
                id = (System.currentTimeMillis() + 1).toString(),
                sender = "Assistant",
                text = response,
                timestamp = System.currentTimeMillis() + 1000,
                role = "assistant"
            )

            _messages.value = _messages.value + assistantMessage
            _isLoading.value = false
        }
    }
}

class AssistantViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssistantViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AssistantViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
