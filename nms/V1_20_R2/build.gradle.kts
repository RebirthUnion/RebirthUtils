plugins {
    id("io.papermc.paperweight.userdev") version "1.5.11"
}

dependencies {
    paperweight.foliaDevBundle("1.20.2-R0.1-SNAPSHOT")
    compileOnly(project(":nms-api"))
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
    compileJava {
        options.encoding = "UTF-8"
    }
}

configurations.reobf {
    outgoing.artifact(layout.buildDirectory.file("libs/${project.name}-${project.version}.jar"))
}