package com.gars.percents.tools

import android.widget.EditText

/**
 * Created by gars on 29.12.2016.
 */

fun EditText?.txt() : String {
    return this?.text.toString().trim()
}
fun EditText?.toInt(defVal: Int = 0) : Int {
    return txt().run {
       if(isEmpty()) defVal else toInt()
    }
}
fun EditText?.toFloat(defVal: Float = 0f) : Float {
    return txt().run {
        if(isEmpty()) defVal else toFloat()
    }
}