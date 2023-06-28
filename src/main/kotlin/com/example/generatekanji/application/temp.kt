ependencies {
    implementation("org.apache.poi:poi-ooxml:5.2.3")
    implementation("org.apache.httpcomponents:httpclient:4.5.14")
    implementation("javax.xml.stream:stax-api:1.0")
    implementation("com.fasterxml:aalto-xml:1.3.2")
    implementation("org.apache.httpcomponents:httpcore:4.4.4")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(kotlin("test"))
    implementation("com.grapecity.documents:gcexcel:6.1.2")
    implementation("org.springframework.data:spring-data-jdbc:3.0.6")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.14")
    implementation("org.springdoc:springdoc-openapi-webflux-ui:1.6.14")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
