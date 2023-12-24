val jvmTargetVersion = JavaVersion.VERSION_17

plugins {
    application
    alias(libs.plugins.kotlin.jvm)
    id("com.google.devtools.ksp") version "1.9.21-1.0.16"
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
    implementation(libs.tg.bot)
    ksp(libs.tg.ksp)
}

tasks {
    compileJava {
        targetCompatibility = jvmTargetVersion.majorVersion
        sourceCompatibility = jvmTargetVersion.majorVersion
    }
    compileKotlin {
        kotlinOptions {
            jvmTarget = jvmTargetVersion.majorVersion
            javaParameters = true
        }
    }
}
