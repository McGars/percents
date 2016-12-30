package com.gars.percents.home.mvp.model

import android.widget.EditText
import com.gars.percents.home.State

/**
 * Created by gars on 29.12.2016.
 */
interface HomeModel {
    fun buildData(inputs: Array<EditText>?) : State
}