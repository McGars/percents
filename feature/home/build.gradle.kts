import com.percent.config.AndroidConfig
import com.percent.config.KotlinConfig

plugins {
    id(BuildPlugins.Ids.androidLibrary)
    id(BuildPlugins.Ids.kotlinAndroid)
}

android {
    compileSdkVersion(AndroidConfig.compileSdk)
    buildToolsVersion(AndroidConfig.buildToolsVersion)

    defaultConfig {

        minSdkVersion(AndroidConfig.minSdk)
        targetSdkVersion(AndroidConfig.targetSdk)

        testInstrumentationRunner = AndroidConfig.instrumentationTestRunner

        vectorDrawables.apply {
            useSupportLibrary = true
            generatedDensities(*(AndroidConfig.noGeneratedDensities))
        }
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {

        getByName("debug")

        getByName("release") {
            val proguardConfig = com.percent.config.ProguardConfig("$rootDir/proguard")
            proguardFiles(*(proguardConfig.customRules))
            proguardFiles(getDefaultProguardFile(proguardConfig.androidRules))

        }
    }

    compileOptions {
        sourceCompatibility = AndroidConfig.targetJVM
        targetCompatibility = AndroidConfig.targetJVM
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = KotlinConfig.targetJVM
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(project(mapOf("path" to ":common")))

    implementation (Libraries.coreAndroidx)
    implementation (Libraries.materialDesign)
    implementation (Libraries.viewBinding)
    implementation (Libraries.kotlinStdlib)
    implementation (Libraries.koin)
    implementation (Libraries.koinExt)
    implementation (Libraries.mviCore)
    implementation (Libraries.mviBinder)
    implementation (Libraries.mviAndroid)
    implementation (Libraries.rxJava)

    testImplementation(Libraries.jUnit)
}