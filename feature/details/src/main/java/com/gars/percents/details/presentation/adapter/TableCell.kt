package com.gars.percents.details.presentation.adapter

import android.graphics.Color
import androidx.annotation.ColorInt


class TableCell(
    val text: String,
    @ColorInt
    val textColor: Int = Color.BLACK,
    val subtext: String? = null
)