package com.devstudio.ai.data.projects

import com.devstudio.ai.data.models.Project

class ProjectRepository {
    private val mockProjects = listOf(
        Project(
            id = "1",
            name = "mobile-commerce",
            type = "React Native",
            status = "Live",
            lastUpdated = System.currentTimeMillis(),
            repoUrl = "https://github.com/user/mobile-commerce",
            previewUrl = "https://mobile-commerce.vercel.app"
        ),
        Project(
            id = "2",
            name = "api-console",
            type = "Node.js",
            status = "Building",
            lastUpdated = System.currentTimeMillis() - 3600000,
            repoUrl = "https://github.com/user/api-console"
        ),
        Project(
            id = "3",
            name = "agent-dashboard",
            type = "Next.js",
            status = "Draft",
            lastUpdated = System.currentTimeMillis() - 7200000,
            repoUrl = "https://github.com/user/agent-dashboard"
        ),
        Project(
            id = "4",
            name = "studio-admin",
            type = "Vue.js",
            status = "Live",
            lastUpdated = System.currentTimeMillis() - 1800000,
            previewUrl = "https://studio-admin.vercel.app"
        ),
        Project(
            id = "5",
            name = "design-system",
            type = "Storybook",
            status = "Building",
            lastUpdated = System.currentTimeMillis() - 900000,
            repoUrl = "https://github.com/user/design-system"
        )
    )

    suspend fun getProjects(): List<Project> = mockProjects

    suspend fun getProjectById(id: String): Project? = mockProjects.find { it.id == id }
}
