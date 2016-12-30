package com.gars.percents.tools

import android.widget.EditText

/**
 * Created by gars on 29.12.2016.
 */


inline fun EditText?.txt() : String {
    return this?.text.toString()
}
inline fun EditText?.toInt(defVal: Int = 0) : Int {
    if(txt().isEmpty())
        return defVal
    return txt().toInt()
}
inline fun EditText?.toFloat(defVal: Float = 0f) : Float {
    if(txt().isEmpty())
        return defVal
    return txt().toFloat()
}