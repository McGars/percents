package com.gars.percents.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes


fun ViewGroup.inflate(
    @LayoutRes layoutId: Int,
    parent: ViewGroup = this,
    attachToRoot: Boolean = false
): View {
    val inflater = LayoutInflater.from(context)
    return inflater.inflate(layoutId, parent, attachToRoot)
}