package com.devstudio.ai.data.models

data class User(
    val id: String,
    val email: String,
    val name: String,
    val avatarUrl: String = "",
    val workspaceName: String = "",
    val token: String = ""
)

data class Project(
    val id: String,
    val name: String,
    val type: String,
    val status: String,
    val lastUpdated: Long,
    val repoUrl: String = "",
    val path: String = "",
    val previewUrl: String = ""
)

data class Deployment(
    val id: String,
    val name: String,
    val platform: String,
    val status: String,
    val url: String,
    val buildLogs: List<String> = emptyList()
)

data class SecretVaultItem(
    val key: String,
    val valueEncrypted: String,
    val category: String,
    val updatedAt: Long
)

data class ChatMessage(
    val id: String,
    val sender: String,
    val text: String,
    val timestamp: Long,
    val role: String
)

data class CodeFile(
    val name: String,
    val path: String,
    val content: String,
    val language: String
)
