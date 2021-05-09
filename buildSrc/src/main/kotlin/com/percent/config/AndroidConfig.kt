package com.percent.config

import org.gradle.api.JavaVersion
import java.util.Collections.emptySet

object AndroidConfig {

    const val applicationId = "com.gars.percents"

    const val compileSdk = 30
    const val minSdk = 23
    const val targetSdk = compileSdk
    val targetJVM = JavaVersion.VERSION_1_8

    const val buildToolsVersion = "30.0.3"

    const val instrumentationTestRunner = "androidx.test.runner.AndroidJUnitRunner"

    val noGeneratedDensities = emptySet<String>().toTypedArray()
}
