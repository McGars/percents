import com.percent.config.AndroidConfig
import com.percent.config.KotlinConfig
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id(BuildPlugins.Ids.kotlinAndroid)
}

repositories {
    google()
    maven(url = "https://jitpack.io")
}

android {
    compileSdkVersion(AndroidConfig.compileSdk)
    buildToolsVersion(AndroidConfig.buildToolsVersion)

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {

        minSdkVersion(AndroidConfig.minSdk)
        targetSdkVersion(AndroidConfig.targetSdk)

        applicationId = AndroidConfig.applicationId
        testInstrumentationRunner = AndroidConfig.instrumentationTestRunner
        versionCode = Versioning.version.code
        versionName = Versioning.version.name

        vectorDrawables.apply {
            useSupportLibrary = true
            generatedDensities(*(AndroidConfig.noGeneratedDensities))
        }

        resConfig("ru")

        testBuildType = "release"
    }
    buildTypes {

        getByName("debug") {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
            isTestCoverageEnabled = true
        }

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true

            val proguardConfig = com.percent.config.ProguardConfig("$rootDir/proguard")
            proguardFiles(*(proguardConfig.customRules))
            proguardFiles(getDefaultProguardFile(proguardConfig.androidRules))

        }
    }

    compileOptions {
        sourceCompatibility = AndroidConfig.targetJVM
        targetCompatibility = AndroidConfig.targetJVM
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = KotlinConfig.targetJVM
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(project(mapOf("path" to ":common")))
    implementation(project(mapOf("path" to ":feature:navigation")))
    implementation(project(mapOf("path" to ":feature:home")))
    implementation(project(mapOf("path" to ":feature:details")))

    implementation (Libraries.coreAndroidx)
    implementation (Libraries.fragmentAndroidx)
    implementation (Libraries.materialDesign)
    implementation (Libraries.viewBinding)
    implementation (Libraries.kotlinStdlib)
    implementation (Libraries.koin)
    implementation (Libraries.koinExt)
    implementation (Libraries.mviCore)
    implementation (Libraries.mviBinder)
    implementation (Libraries.mviAndroid)
    implementation (Libraries.timber)

    testImplementation(Libraries.jUnit)
}