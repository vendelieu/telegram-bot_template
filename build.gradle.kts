plugins {
    application
    kotlin("jvm") version "1.8.0"
}

group = "com.example.conversation"
version = "0.0.1"
application {
    mainClass.set("com.example.conversation.ConversationApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("eu.vendeli:telegram-bot:2.6.0")

    implementation(group = "org.redisson", name = "redisson", version = "3.17.0") {
        exclude("com.fasterxml.jackson.core", "jackson-databind")
    }
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
}
