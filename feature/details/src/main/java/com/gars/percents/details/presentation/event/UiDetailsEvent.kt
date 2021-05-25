package com.gars.percents.details.presentation.event


sealed class UiDetailsEvent {
    object Ready : UiDetailsEvent()
    object BackToHome : UiDetailsEvent()
}