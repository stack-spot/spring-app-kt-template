plugins {
    kotlin("jvm") version "1.6.10"
    application
}

version = "0.1"
group = "{{project_group_id}}"

repositories {
    mavenCentral()
}

dependencies {
    implementation("software.amazon.awscdk:aws-cdk-lib:2.16.0")
    implementation("org.cdk8s:cdk8s:1.5.39")
    implementation("org.cdk8s:cdk8s-plus-22:1.0.0-beta.142")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
}

application {
    mainClass.set("{{project_group_id}}.{{computed_inputs.camel_project_name}}CdkAppKt")
}

kotlin {
    jvmToolchain {
        (this as JavaToolchainSpec).languageVersion.set(JavaLanguageVersion.of(11))
    }
}
