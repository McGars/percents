package com.gars.percents.details.presentation.model


data class DetailsViewModel(
    val state: State,
    val month: String,
    val actuals: List<com.gars.percents.details.data.AccrualItem>
) {
    enum class State {
        IDLE,
        LOADING,
        DATA,
        ERROR,
    }
}