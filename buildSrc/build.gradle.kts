
repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

plugins {
    java
    `kotlin-dsl`
    `java-gradle-plugin`
}

buildscript {
    repositories {
        google()
    }
}

gradlePlugin {
    plugins {
        register("settings-plugin") {
            id = "settings-plugin"
            implementationClass = "com.percent.plugin.SittingsPlugin"
        }
    }
}
