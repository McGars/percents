package com.gars.percents.details.presentation

import com.gars.percents.details.data.AccrualItem


data class DetailsViewModel(
    val state: State,
    val month: String,
    val actuals: List<AccrualItem>
) {
    enum class State {
        IDLE,
        LOADING,
        DATA,
        ERROR,
    }
}