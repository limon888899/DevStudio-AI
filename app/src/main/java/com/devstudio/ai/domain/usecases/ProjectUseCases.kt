package com.devstudio.ai.domain.usecases

import com.devstudio.ai.data.projects.ProjectRepository
import com.devstudio.ai.data.models.Project

class LoadProjectsUseCase(private val projectRepository: ProjectRepository) {
    suspend operator fun invoke(): List<Project> {
        return projectRepository.getProjects()
    }
}

class GetProjectByIdUseCase(private val projectRepository: ProjectRepository) {
    suspend operator fun invoke(id: String): Project? {
        return projectRepository.getProjectById(id)
    }
}
