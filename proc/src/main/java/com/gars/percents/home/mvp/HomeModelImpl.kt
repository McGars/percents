package com.gars.percents.home.mvp

import android.widget.EditText
import com.gars.percents.R
import com.gars.percents.home.State
import com.gars.percents.home.mvp.model.HomeModel
import com.gars.percents.tools.toFloat
import com.gars.percents.tools.toInt

/**
 * Created by gars on 29.12.2016.
 */
class HomeModelImpl : HomeModel {
    override fun buildData(inputs: Array<EditText>?) : State {
        val state = State()
        inputs?.forEach {
            when(it.id) {
                R.id.etProcent -> state.percent = it.toFloat(state.percent)
                R.id.etDeposit -> state.deposit = it.toFloat(state.deposit)
                R.id.etMounthAdd -> state.monthAdd = it.toInt(state.monthAdd)
                R.id.etMounthAddBreak -> state.monthAddBreak = it.toInt(state.monthAddBreak)
                R.id.etYearState -> state.yearState = it.toInt(state.yearState)
                R.id.etPortion -> state.portion = it.toInt(state.portion)
                R.id.etTakeOff -> state.takeOff = it.toFloat(state.takeOff)
                R.id.etTakeOffEndMonth -> state.takeOffEndMonth = it.toInt(state.takeOffEndMonth)
                R.id.etTakeOffCount -> state.takeOffCount = it.toFloat(state.takeOffCount)
            }
        }

        return state
    }
}