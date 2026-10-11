package com.devstudio.ai.data.auth

import com.devstudio.ai.data.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class AuthRepository(private val tokenManager: AuthTokenManager) {

    suspend fun login(email: String, password: String): Result<User> = withContext(Dispatchers.IO) {
        try {
            if (email.contains("@") && password.length >= 6) {
                val token = "jwt_token_${email.hashCode()}_${System.currentTimeMillis()}"
                val user = User(
                    id = email.hashCode().toString(),
                    email = email,
                    name = email.split("@")[0],
                    token = token,
                    workspaceName = "DevStudio Team"
                )
                tokenManager.saveToken(token)
                tokenManager.saveUserData(email, user.name)
                Result.success(user)
            } else {
                Result.failure(Exception("Invalid credentials"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(email: String, name: String, password: String): Result<User> = withContext(Dispatchers.IO) {
        try {
            if (email.contains("@") && password.length >= 6 && name.isNotBlank()) {
                val token = "jwt_token_${email.hashCode()}_${System.currentTimeMillis()}"
                val user = User(
                    id = email.hashCode().toString(),
                    email = email,
                    name = name,
                    token = token,
                    workspaceName = "$name's Workspace"
                )
                tokenManager.saveToken(token)
                tokenManager.saveUserData(email, name)
                Result.success(user)
            } else {
                Result.failure(Exception("Invalid input"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun isLoggedIn(): Flow<Boolean> = tokenManager.getToken().apply {
        // This will emit whether token exists
    } as Flow<Boolean>

    suspend fun logout() {
        tokenManager.clearAll()
    }

    suspend fun getToken(): String? = tokenManager.getToken().first()

    suspend fun getUserEmail(): String? = tokenManager.getUserEmail().first()

    suspend fun getUserName(): String? = tokenManager.getUserName().first()
}
