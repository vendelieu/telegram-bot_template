import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val jvmTargetVersion = JavaVersion.VERSION_17

plugins {
    application
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
}

group = "eu.vendeli.samples"
version = "0.0.1"
application {
    mainClass.set("eu.vendeli.samples.ErrorHandlingApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.logback.classic)
    implementation(libs.coroutines.core)
    implementation(libs.tg.bot)
    ksp(libs.tg.ksp)
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.valueOf(jvmTargetVersion.majorVersion)
    }
    jvmToolchain(jvmTargetVersion.majorVersion.toInt())
}