plugins {
    application
    kotlin("jvm") version "1.8.0"
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
    implementation("eu.vendeli:telegram-bot:2.5.2")
}
