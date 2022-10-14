dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    runtimeOnly("org.mariadb:r2dbc-mariadb")
//    testImplementation("org.mariadb:r2dbc-mariadb:1.1.2")

    kapt("org.springframework.boot:spring-boot-configuration-processor")

//    implementation("at.favre.lib:bcrypt:0.9.0")
}

tasks.jar {
    archiveFileName.set("chaeking-auth.jar")
}