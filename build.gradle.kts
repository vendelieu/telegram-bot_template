import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val jvmTargetVersion = JavaVersion.VERSION_17

plugins {
    application
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
}

group = "com.example.echo"
version = "0.0.1"
application {
    mainClass.set("com.example.echo.EchoApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.tg.bot)
    ksp(libs.tg.ksp)
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget(jvmTargetVersion.majorVersion)
    }
    jvmToolchain(jvmTargetVersion.majorVersion.toInt())
}
