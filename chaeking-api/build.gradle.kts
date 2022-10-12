apply(plugin = "kotlin-jpa")

dependencies {
//    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    implementation("org.springframework.boot:spring-boot-starter-web")
//    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    kapt("org.springframework.boot:spring-boot-configuration-processor")

    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    runtimeOnly("org.mariadb:r2dbc-mariadb:1.1.2")

    implementation("io.netty:netty-resolver-dns-native-macos:4.1.82.Final:osx-aarch_64")
}