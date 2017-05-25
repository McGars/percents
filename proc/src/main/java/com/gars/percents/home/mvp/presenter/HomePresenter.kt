package com.gars.percents.home.mvp.presenter

import android.widget.EditText

/**
 * Created by gars on 29.12.2016.
 */
interface HomePresenter {
    fun buildData(inputs: Array<EditText>?)
}