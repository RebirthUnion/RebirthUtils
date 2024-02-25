plugins{
  kotlin("jvm") version "1.9.21"
}

dependencies{
   compileOnly("dev.folia:folia-api:1.20.4-R0.1-SNAPSHOT")
   implementation(project(":nms-api"))
   implementation(kotlin("stdlib-jdk8"))
}
