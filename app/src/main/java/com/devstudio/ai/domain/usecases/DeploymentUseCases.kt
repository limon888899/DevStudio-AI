package com.devstudio.ai.domain.usecases

import com.devstudio.ai.data.deployments.DeploymentRepository
import com.devstudio.ai.data.models.Deployment

class LoadDeploymentsUseCase(private val deploymentRepository: DeploymentRepository) {
    suspend operator fun invoke(): List<Deployment> {
        return deploymentRepository.getDeployments()
    }
}

class DeployProjectUseCase(private val deploymentRepository: DeploymentRepository) {
    suspend operator fun invoke(projectName: String): Deployment {
        return deploymentRepository.deployProject(projectName)
    }
}
