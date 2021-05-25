package com.gars.percents.details.data

import androidx.annotation.ColorInt


class AccrualItem(
    val monthNumber: String,
    val incomingPercents: String,
    val takeOff: String,
    @ColorInt
    val takeOffColor: Int,
    @ColorInt
    val incomingColor: Int,
    val profit: String,
    val investment: String,
    val total: String,
    val date: String,

    )