package com.devstudio.ai.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.devstudio.ai.data.models.Project
import com.devstudio.ai.data.projects.ProjectRepository
import com.devstudio.ai.domain.usecases.LoadProjectsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ProjectState {
    data object Loading : ProjectState()
    data class Success(val projects: List<Project>) : ProjectState()
    data class Error(val message: String) : ProjectState()
}

class DashboardViewModel(
    private val projectRepository: ProjectRepository
) : ViewModel() {

    private val loadProjectsUseCase = LoadProjectsUseCase(projectRepository)

    private val _projectState = MutableStateFlow<ProjectState>(ProjectState.Loading)
    val projectState: StateFlow<ProjectState> = _projectState.asStateFlow()

    private val _activeProjects = MutableStateFlow(12)
    val activeProjects: StateFlow<Int> = _activeProjects.asStateFlow()

    private val _deployments = MutableStateFlow("8 Live")
    val deployments: StateFlow<String> = _deployments.asStateFlow()

    private val _aiTokens = MutableStateFlow("48.2K")
    val aiTokens: StateFlow<String> = _aiTokens.asStateFlow()

    init {
        loadProjects()
    }

    private fun loadProjects() {
        viewModelScope.launch {
            try {
                val projects = loadProjectsUseCase()
                _projectState.value = ProjectState.Success(projects)
            } catch (e: Exception) {
                _projectState.value = ProjectState.Error(e.message ?: "Error loading projects")
            }
        }
    }
}

class DashboardViewModelFactory(
    private val projectRepository: ProjectRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DashboardViewModel(projectRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
