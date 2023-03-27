@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    application
    alias(libs.plugins.kotlin.jvm)
}

group = "com.example.blank"
version = "0.0.1"
application {
    mainClass.set("com.example.blank.TgBotApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.tg.bot)
}
