package com.gars.percents.navigation.data.model


sealed class Page {
    object Idle: Page()
    object Home: Page()
    object Details: Page()
}
