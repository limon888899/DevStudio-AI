package com.devstudio.ai.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.devstudio.ai.data.deployments.DeploymentRepository
import com.devstudio.ai.data.models.Deployment
import com.devstudio.ai.domain.usecases.LoadDeploymentsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class DeploymentState {
    data object Loading : DeploymentState()
    data class Success(val deployments: List<Deployment>) : DeploymentState()
    data class Error(val message: String) : DeploymentState()
}

class DeployViewModel(
    private val deploymentRepository: DeploymentRepository
) : ViewModel() {

    private val loadDeploymentsUseCase = LoadDeploymentsUseCase(deploymentRepository)

    private val _deploymentState = MutableStateFlow<DeploymentState>(DeploymentState.Loading)
    val deploymentState: StateFlow<DeploymentState> = _deploymentState.asStateFlow()

    private val _buildProgress = MutableStateFlow(0f)
    val buildProgress: StateFlow<Float> = _buildProgress.asStateFlow()

    init {
        loadDeployments()
    }

    private fun loadDeployments() {
        viewModelScope.launch {
            try {
                val deployments = loadDeploymentsUseCase()
                _deploymentState.value = DeploymentState.Success(deployments)
            } catch (e: Exception) {
                _deploymentState.value = DeploymentState.Error(e.message ?: "Error loading deployments")
            }
        }
    }

    fun startApkBuild() {
        viewModelScope.launch {
            _deploymentState.value = DeploymentState.Loading
            for (i in 0..100 step 10) {
                _buildProgress.value = i / 100f
                kotlinx.coroutines.delay(500)
            }
            loadDeployments()
        }
    }
}

class DeployViewModelFactory(
    private val deploymentRepository: DeploymentRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeployViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DeployViewModel(deploymentRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
