val jvmTargetVersion = JavaVersion.VERSION_11
val ktorVer = libs.versions.ktor.get()

plugins {
    application
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
}

group = "com.example.ktorwebhook"
version = "0.0.1"
application {
    mainClass.set("com.example.ktorwebhook.KtorWebhookApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.tg.bot)
    implementation(libs.logback)
    ksp(libs.tg.ksp)

    implementation("io.ktor:ktor-server-core-jvm:$ktorVer")
    implementation("io.ktor:ktor-server-netty-jvm:$ktorVer")

    testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVer")
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
