package com.gars.percents.details.presentation


sealed class UiDetailsEvent {
    object Ready : UiDetailsEvent()
    object BackToHome : UiDetailsEvent()
}