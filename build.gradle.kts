plugins {
    application
    kotlin("jvm") version "1.8.0"
}

group = "com.example.poll"
version = "0.0.1"
application {
    mainClass.set("com.example.poll.PollApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("eu.vendeli:telegram-bot:2.5.4")
}