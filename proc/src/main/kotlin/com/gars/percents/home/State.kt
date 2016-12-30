package com.gars.percents.home

import java.io.Serializable

/**
 * Created by gars on 29.12.2016.
 */
data class State (
        var procents: Int = 10,
        var deposit: Float = 0f,
        var mounthAdd: Int = 0,
        var mounthAddBreak: Int = 0,
        var yearState: Int = 1,
        var portion: Int = 1,
        var takeOff: Float = 0f,
        var takeOffEndMonth: Int = 0,
        var takeOffCount: Float = 0f) : Serializable