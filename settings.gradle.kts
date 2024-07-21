pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        kotlin("multiplatform") version "2.0.0"
        id("org.jetbrains.kotlin.plugin.serialization") version "1.7.0"
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}

rootProject.name = "MeetAgainShared"
include(":shared")
