package com.devstudio.ai.domain.usecases

import com.devstudio.ai.data.auth.AuthRepository
import com.devstudio.ai.data.models.User

class LoginUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        return authRepository.login(email, password)
    }
}

class RegisterUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String, name: String, password: String): Result<User> {
        return authRepository.register(email, name, password)
    }
}

class LogoutUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke() {
        authRepository.logout()
    }
}
