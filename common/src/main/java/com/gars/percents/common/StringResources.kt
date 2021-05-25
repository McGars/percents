package com.gars.percents.common

import android.content.Context


interface StringResources {

    fun getString(text: Int, vararg arguments: Any): String

    class Impl(private val context: Context) : StringResources {

        override fun getString(text: Int, vararg arguments: Any): String {
            return if (arguments.isNotEmpty()) context.getString(text, *arguments)
            else context.getString(text)
        }

    }
}