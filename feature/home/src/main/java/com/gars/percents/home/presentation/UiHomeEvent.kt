package com.gars.percents.home.presentation


sealed class UiHomeEvent {
    class FieldUpdate(val fieldId: Int, val value: String): UiHomeEvent()
    object Calculate: UiHomeEvent()
}