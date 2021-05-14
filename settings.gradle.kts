pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

includeBuild("include-build-dependency")

include(
    ":proc",
    ":feature:navigation"
)