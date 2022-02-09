plugins {
    application
    kotlin("jvm") version "1.6.10"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
    id("org.springframework.boot") version "2.5.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.spring") version "1.6.10"
}

version = "0.0.1-SNAPSHOT"
group = "{{project_group_id}}"

repositories {
    mavenCentral()
}

dependencies {
    {% if inputs.starter_web %}
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    {% else %}
    implementation("org.springframework.boot:spring-boot-starter")
    {% endif %}
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(kotlin("test"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(11))
    }
}

application {
    mainClass.set("{{project_group_id}}.ApplicationKt")
}
