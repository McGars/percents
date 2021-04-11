
buildscript {

    repositories {
        google()
        jcenter()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://plugins.gradle.org/m2/")
    }

    dependencies {
        classpath(BuildPlugins.Dependencies.androidSupport)
        classpath(BuildPlugins.Dependencies.kotlinSupport)
    }
}

plugins {
    id("class-loader-plugin")
}

allprojects {

    repositories {
        google()
        maven(url = "https://kotlin.bintray.com/kotlinx")
        maven(url = "https://jitpack.io")
        jcenter()
    }

}

tasks.register("clean").configure {
    delete("build")
}