import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val javaTarget = JavaVersion.VERSION_17

plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.ksp)
}

group = "com.example.springbot"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = javaTarget

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    implementation(libs.tg.bot)
    ksp(libs.tg.ksp)

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

kotlin {
    jvmToolchain(javaTarget.majorVersion.toInt())
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
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = javaTarget.majorVersion
            javaParameters = true
        }
    }
}
