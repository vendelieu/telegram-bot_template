plugins {
    application
    kotlin("jvm") version "1.7.20"
}

group = "eu.vendeli.samples"
version = "0.0.1"
application {
    mainClass.set("eu.vendeli.samples.TgBotApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("eu.vendeli:telegram-bot:2.2.1")
}