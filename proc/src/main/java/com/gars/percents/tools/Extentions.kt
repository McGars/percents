package com.gars.percents.tools

fun String?.toInt(defVal: Int): Int =
    if (isNullOrEmpty()) defVal else this.toInt()

fun String?.toFloat(defVal: Float): Float =
    if (isNullOrEmpty()) defVal else this.toFloat()