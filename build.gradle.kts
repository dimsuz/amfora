import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }
}

plugins {
    kotlin("jvm") version "1.5.21"
    id("org.jetbrains.compose") version "1.0.0-alpha3"
    id("com.apollographql.apollo") version "2.5.9"
}

group = "ru.dimsuz"
version = "1.0"

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
    google()
}

apollo {
    packageName.set("ru.dimsuz.amfora")
    generateKotlinModels.set(true)
}

dependencies {
    implementation(compose.desktop.currentOs)

    implementation("com.apollographql.apollo:apollo-runtime:2.5.9")
    implementation("com.apollographql.apollo:apollo-coroutines-support:2.5.9")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

compose.desktop {
    application {
        mainClass = "ru.dimsuz.amfora.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Deb)
            packageName = "amfora"
        }
    }
}
