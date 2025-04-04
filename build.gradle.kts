import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val javaTarget = JavaVersion.VERSION_17

plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.ksp)
    alias(libs.plugins.telegram.bot)
}

group = "com.example.springbot"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = javaTarget

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    implementation(libs.tg.spring.starter)

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

kotlin {
    jvmToolchain(javaTarget.majorVersion.toInt())
}

configurations.all {
    resolutionStrategy.eachDependency {
        val serdeVer = "1.8.1"
        when(requested.module.toString()) {
            // json serialiazaton
            "org.jetbrains.kotlinx:kotlinx-serialization-json" -> useVersion(serdeVer)
            "org.jetbrains.kotlinx:kotlinx-serialization-json-jvm" -> useVersion(serdeVer)
            "org.jetbrains.kotlinx:kotlinx-serialization-core" -> useVersion(serdeVer)
            "org.jetbrains.kotlinx:kotlinx-serialization-core-jvm" -> useVersion(serdeVer)
            "org.jetbrains.kotlinx:kotlinx-serialization-bom" -> useVersion(serdeVer)
        }
    }
}

tasks {
    compileJava {
        targetCompatibility = javaTarget.majorVersion
        sourceCompatibility = javaTarget.majorVersion
    }

    withType<Test> {
        useJUnitPlatform()
    }
    withType<KotlinCompile> {
        compilerOptions {
            freeCompilerArgs.set(listOf("-Xjsr305=strict"))
            jvmTarget.set(JvmTarget.fromTarget(javaTarget.majorVersion))
        }
    }
}
