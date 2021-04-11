pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
    }
}

includeBuild("include-build-dependency")

include(
    ":proc"
)