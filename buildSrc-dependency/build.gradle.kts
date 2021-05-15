
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
        register("class-loader-plugin") {
            id = "class-loader-plugin"
            implementationClass = "com.percent.plugin.ClassLoaderPlugin"
        }
    }
}