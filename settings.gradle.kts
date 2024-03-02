import java.util.Properties
import java.io.FileInputStream

// Initialize Properties object
val localProperties = Properties()

// Define the local properties file
val localPropertiesFile = rootDir.resolve("local.properties")

if (localPropertiesFile.exists()) {
    FileInputStream(localPropertiesFile).use { localProperties.load(it) }
}

rootProject.name = "stub"

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        google()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/apexnova-vc/proto")
            credentials {
                username = localProperties.getProperty("gpr.user") ?: System.getenv("GITHUB_USERNAME") ?: ""
                password = localProperties.getProperty("gpr.key") ?: System.getenv("GITHUB_TOKEN") ?: ""
            }
        }
    }
}



plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}
