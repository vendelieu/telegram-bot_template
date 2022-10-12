val ktor_version: String by project
val koin_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.7.20"
}

group = "eu.vendeli.samples.heroku"
version = "0.0.1"
java.targetCompatibility = JavaVersion.VERSION_17

application {
    mainClass.set("eu.vendelieu.samples.heroku.HerokuAppKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-XX:+UseZGC", "-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("eu.vendeli:telegram-bot:2.2.2")
    implementation("io.insert-koin:koin-core:$koin_version")

    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
}

tasks.create("stage").dependsOn("installDist")