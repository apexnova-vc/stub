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
                username = "dongming-shen"
                password = "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"
            }
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}
