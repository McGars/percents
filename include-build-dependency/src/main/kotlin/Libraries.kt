// Versions for project parameters and forEachDependency

object Libraries {

    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersion}"
    const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    const val conductor = "com.bluelinelabs:conductor-support:${Versions.conductor}"

    const val coreAndroidx = "androidx.core:core-ktx:${Versions.coreAndroidx}"

    const val koin = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinExt = "io.insert-koin:koin-android-ext:${Versions.koin}"

    const val jUnit = "junit:junit:${Versions.junit}"

    private object Versions {
        const val koin = "3.0.1-beta-2"
        const val coreAndroidx = "1.3.1"
        const val materialDesign = "1.3.0"
        const val conductor = "2.1.5"
        const val junit = "4.13"
        const val kotlinVersion = "1.4.32"
    }
}
