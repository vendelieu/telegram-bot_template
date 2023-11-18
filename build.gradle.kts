val jvmTargetVersion = JavaVersion.VERSION_11

plugins {
    application
    alias(libs.plugins.kotlin.jvm)
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
    implementation(libs.tg.bot)
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
