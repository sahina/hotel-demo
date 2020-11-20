import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
    base
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.spring") version "1.4.10"
    kotlin("plugin.jpa") version "1.4.10"
    kotlin("kapt") version "1.4.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.10"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.4.10"
    id("org.springframework.boot") version "2.3.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("com.palantir.docker") version "0.25.0" apply false
    id("com.palantir.docker-run") version "0.25.0" apply false
}

group = "com.hotel"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
    jcenter()
    mavenLocal()
}

configure<DependencyManagementExtension> {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    //
    // spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }

    //
    // axon
    implementation("org.axonframework:axon-spring-boot-starter:4.4.5")
    testImplementation("org.axonframework:axon-test:4.4.5")

    //
    // others
    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("org.hibernate.validator:hibernate-validator:6.0.18.Final")
    implementation("org.postgresql:postgresql")
    implementation("org.hibernate:hibernate-core:5.4.22.Final")
    testImplementation("org.hibernate:hibernate-testing:5.4.22.Final")
}




