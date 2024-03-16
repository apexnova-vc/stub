plugins {
    alias(libs.plugins.protobuf) apply true
    alias(libs.plugins.kotlin.jvm) apply true
    `java-library`
    `maven-publish`
}

group = "com.apexnova"
version = findProperty("version")

dependencies {
    protobuf(libs.apexnova.proto)

    implementation("org.springframework.boot:spring-boot-starter-data-cassandra:3.2.3")
    implementation(libs.grpc.stub)
    implementation(libs.grpc.protobuf)
    implementation(libs.protobuf.kotlin)
    implementation(libs.grpc.kotlin.stub)
    implementation(libs.kotlinx.coroutines.core)
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

// only publish from github action.
publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/apexnova-vc/stub")
            credentials {
                username = System.getenv("GITHUB_USERNAME")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}

sourceSets {
    main {
        kotlin.srcDir("src/main/kotlin")
        resources.srcDir("src/main/resources")
    }
    test {
        kotlin.srcDir("src/test/kotlin")
        resources.srcDir("src/test/resources")
    }
}

java {
    withJavadocJar()
    // Uncomment the following line if you need the sources jar as well
    withSourcesJar()
}


