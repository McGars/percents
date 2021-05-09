// Versions for project parameters and forEachDependency

object Libraries {

    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlinVersion}"
    const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"

    const val coreAndroidx = "androidx.core:core-ktx:${Versions.coreAndroidx}"
    const val fragmentAndroidx = "androidx.fragment:fragment-ktx:${Versions.coreAndroidx}"
    const val viewBinding = "com.github.kirich1409:viewbindingpropertydelegate:${Versions.viewBinding}"

    const val koin = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinExt = "io.insert-koin:koin-android-ext:${Versions.koin}"

    const val mviCore = "com.github.badoo.mvicore:mvicore:${Versions.mviCore}"
    const val mviBinder = "com.github.badoo.mvicore:binder:${Versions.mviCore}"
    const val mviAndroid = "com.github.badoo.mvicore:mvicore-android:${Versions.mviCore}"

    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val jUnit = "junit:junit:${Versions.junit}"

    private object Versions {
        const val koin = "3.0.1-beta-2"
        const val coreAndroidx = "1.3.1"
        const val materialDesign = "1.3.0"
        const val viewBinding = "1.4.6"
        const val junit = "4.13"
        const val kotlinVersion = "1.4.32"
        const val mviCore = "1.2.6"
        const val rxJava = "2.2.21"
        const val rxAndroid = "2.1.1"
        const val timber = "4.7.1"
    }
}
