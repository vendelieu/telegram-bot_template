val jvmTargetVersion = JavaVersion.VERSION_11

plugins {
    application
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
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
    ksp(libs.tg.ksp)
}

tasks {
    compileJava {
        targetCompatibility = jvmTargetVersion.majorVersion
    }
    compileKotlin {
        kotlinOptions {
            jvmTarget = jvmTargetVersion.majorVersion
            javaParameters = true
        }
    }
}
