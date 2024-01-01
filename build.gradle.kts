val jvmTargetVersion = JavaVersion.VERSION_11

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
    implementation(libs.coroutines.core)
    implementation(libs.tg.bot)
    ksp(libs.tg.ksp)
}

tasks {
    compileJava {
        targetCompatibility = jvmTargetVersion.majorVersion
    }
    compileKotlin {
        kotlinOptions {
            jvmTarget = jvmTargetVersion.majorVersion
            javaParameters = true
        }
    }
}