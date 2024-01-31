rootProject.name = "stub"

val githubUsername: String? = System.getenv("GITHUB_USERNAME")
val githubToken: String? = System.getenv("GITHUB_TOKEN")


pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
        google()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/apexnova-vc/proto")
            credentials {
                username = githubUsername
                password = githubToken
            }
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}
