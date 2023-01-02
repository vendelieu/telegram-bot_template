val ktor_version: String by project
val logback_version: String by project
val tgbot_version: String by project

plugins {
    application
    kotlin("jvm") version "1.8.0"
}

group = "com.example.ktorwebhook"
version = "0.0.1"
application {
    mainClass.set("com.example.ktorwebhook.KtorWebhookApplication")

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
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
}