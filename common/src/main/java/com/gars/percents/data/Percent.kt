package com.gars.percents.data

import java.io.Serializable

/**
 * Created by gars on 29.12.2016.
 */
data class Percent (
        var percent: Float = 10f,
        var deposit: Float = 0f,
        var monthAdd: Int = 0,
        var monthAddBreak: Int = 0,
        var years: Int = 1,
        var portion: Int = 1,
        var takeOff: Float = 0f,
        var takeOffEndMonth: Int = 0,
        var takeOffCount: Float = 0f
) : Serializable