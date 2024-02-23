dependencies {
    compileOnly("dev.folia:folia-api:1.20.2-R0.1-SNAPSHOT")
    implementation(project(":nms-api"))
    implementation(project(":nms:V1_20_R1","reobf"))
    implementation(project(":nms:V1_20_R2","reobf"))
    implementation(project(":nms:V1_20_R3","reobf"))
}