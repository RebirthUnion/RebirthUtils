plugins {
    kotlin("jvm") version "1.9.21"
    id("java-library")
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "moe.taswell"
version = "1.0-dev"

allprojects {
    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "com.github.johnrengelman.shadow")

    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://oss.sonatype.org/content/groups/public/")
    }

    tasks {
        compileJava {
            options.encoding = "UTF-8"
        }
        processResources {
            filesMatching("**/plugin.yml") {
                expand(rootProject.project.properties)
            }

            // Always re-run this task
            outputs.upToDateWhen { false }
        }

    }
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
