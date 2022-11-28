val profile = if(project.hasProperty("profile")) project.property("profile").toString() else "local"

sourceSets {
    main {
        resources {
            srcDirs("src/main/resources/profile/$profile")
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    kapt("org.springframework.boot:spring-boot-configuration-processor")
    compileOnly("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    runtimeOnly("org.mariadb:r2dbc-mariadb:1.1.2")

    implementation("org.springdoc:springdoc-openapi-kotlin:1.6.13")
    implementation("org.springdoc:springdoc-openapi-webflux-ui:1.6.13")

    implementation("org.springframework.cloud:spring-cloud-starter-vault-config")

    implementation("io.netty:netty-resolver-dns-native-macos:4.1.85.Final:osx-aarch_64")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2021.0.3")
    }
}

tasks.bootJar {
    archiveFileName.set("chaeking-api.jar")
}