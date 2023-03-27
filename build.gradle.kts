val jvmTargetVersion = JavaVersion.VERSION_11

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    application
    alias(libs.plugins.kotlin.jvm)
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
    implementation(libs.tg.bot)
}

tasks {
    compileJava {
        targetCompatibility = jvmTargetVersion.majorVersion
    }
    compileKotlin {
        kotlinOptions.jvmTarget = jvmTargetVersion.majorVersion
    }
}
