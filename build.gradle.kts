import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
    kotlin("plugin.jpa") version "1.8.21"
    id("org.openapi.generator") version "7.0.0"
    application
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}


dependencies {
// https://mvnrepository.com/artifact/mysql/mysql-connector-java
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.1.0")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")


    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.kafka:spring-kafka:3.0.8")
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
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("org.openapitools:openapi-generator-gradle-plugin:7.0.0")
    }
}


openApiGenerate {
    generatorName.set("kotlin")
    inputSpec.set("C:\\Users\\Даниил\\AppData\\Roaming\\JetBrains\\IntelliJIdea2022.3\\openApiExport\\generateKanji-openapi.yaml")
    outputDir.set("$buildDir/generated")
    apiPackage.set("com.example.generatekanji.application.controllers")
    modelPackage.set("com.example.generatekanji.application.controllers")
}

apply(plugin = "org.openapi.generator")

