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
                username = providers.gradleProperty("gpr.user").forUseAtConfigurationTime().orNull
                password = providers.gradleProperty("gpr.key").forUseAtConfigurationTime().orNull
            }
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}
