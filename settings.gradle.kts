pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

includeBuild("buildSrc-dependency")

include(
    ":app",
    ":common",
    ":feature:navigation",
    ":feature:home",
    ":feature:details"
)