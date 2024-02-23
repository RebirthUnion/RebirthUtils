plugins {
    kotlin("jvm")
    id("java")
}

group = "moe.taswell"
version = "1.0-dev"

repositories {
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
    mavenCentral()
}

dependencies {
    compileOnly("io.netty:netty-all:4.1.105.Final")
    compileOnly("dev.folia:folia-api:1.20.2-R0.1-SNAPSHOT")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
