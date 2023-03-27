val jvmTargetVersion = JavaVersion.VERSION_11

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    application
    alias(libs.plugins.kotlin.jvm)
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

    implementation(libs.redisson) {
        exclude("com.fasterxml.jackson.core", "jackson-databind")
    }
    implementation(libs.jackson.databind)
    implementation(libs.ktx.coroutines.jdk8)
}

tasks {
    compileJava {
        targetCompatibility = jvmTargetVersion.majorVersion
    }
    compileKotlin {
        kotlinOptions.jvmTarget = jvmTargetVersion.majorVersion
    }
}
