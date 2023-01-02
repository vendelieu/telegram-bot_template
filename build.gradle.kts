val ktor_version: String by project
val tgbot_version: String by project

plugins {
    application
    kotlin("jvm") version "1.8.0"
}

group = "com.example.heroku"
version = "0.0.1"
java.targetCompatibility = JavaVersion.VERSION_17

application {
    mainClass.set("com.example.heroku.HerokuAppKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("eu.vendeli:telegram-bot:$tgbot_version")

    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
}

tasks.create("stage").dependsOn("installDist")