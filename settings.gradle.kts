rootProject.name = "stub"

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
                username = providers.environmentVariable("GITHUB_USERNAME").forUseAtConfigurationTime().orNull
                password = providers.environmentVariable("GITHUB_TOKEN").forUseAtConfigurationTime().orNull
            }
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}
