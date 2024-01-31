plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.protobuf) apply true
    alias(libs.plugins.kotlin.jvm) apply true
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.dokka) apply false
    `java-library`
    `maven-publish`
}

group = "com.apexnova"
version = findProperty("version")

dependencies {
    protobuf(libs.apexnova.proto)

    api(libs.kotlinx.coroutines.core)
    api(libs.grpc.stub)
    api(libs.grpc.protobuf)
    api(libs.protobuf.java.util)
    api(libs.protobuf.kotlin)
    api(libs.grpc.kotlin.stub)
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions {
        freeCompilerArgs = listOf("-opt-in=kotlin.RequiresOptIn")
    }
}

protobuf {
    protoc {
        artifact = libs.protoc.asProvider().get().toString()
    }
    plugins {
        create("grpc") {
            artifact = libs.protoc.gen.grpc.java.get().toString()
        }
        create("grpckt") {
            artifact = libs.protoc.gen.grpc.kotlin.get().toString() + ":jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                create("grpc")
                create("grpckt")
            }
            it.builtins {
                create("kotlin")
            }
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/apexnova-vc/stub")
            credentials {
                username = "dongming-shen" // findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USERNAME")
                password = findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}

java {
    // withJavadocJar() add this will cause warnings fix later
    withSourcesJar()
}