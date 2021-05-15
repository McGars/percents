pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

includeBuild("buildSrc-dependency")

include(
    ":proc",
    ":feature:navigation"
)