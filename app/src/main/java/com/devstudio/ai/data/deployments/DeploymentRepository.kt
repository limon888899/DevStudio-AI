package com.devstudio.ai.data.deployments

import com.devstudio.ai.data.models.Deployment

class DeploymentRepository {
    private val mockDeployments = listOf(
        Deployment(
            id = "1",
            name = "studio-app.vercel.app",
            platform = "Vercel",
            status = "Live",
            url = "https://studio-app.vercel.app",
            buildLogs = listOf(
                "[14:32] Building project...",
                "[14:33] Compiling source code...",
                "[14:34] Running build tasks...",
                "[14:35] Optimizing bundle...",
                "[14:36] Deployment complete!"
            )
        ),
        Deployment(
            id = "2",
            name = "app.devstudio.ai",
            platform = "Custom Domain",
            status = "Live",
            url = "https://app.devstudio.ai",
            buildLogs = listOf()
        )
    )

    suspend fun getDeployments(): List<Deployment> = mockDeployments

    suspend fun getDeploymentById(id: String): Deployment? = mockDeployments.find { it.id == id }

    suspend fun deployProject(projectName: String): Deployment {
        return Deployment(
            id = System.currentTimeMillis().toString(),
            name = "$projectName.vercel.app",
            platform = "Vercel",
            status = "Building",
            url = "https://$projectName.vercel.app",
            buildLogs = listOf("[NOW] Deployment started...")
        )
    }
}
