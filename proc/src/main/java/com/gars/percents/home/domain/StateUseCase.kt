package com.gars.percents.home.domain

import com.gars.percents.R
import com.gars.percents.home.data.Percent
import com.gars.percents.tools.toFloat
import com.gars.percents.tools.toInt

class StateUseCase {

    val state = Percent()

    fun applyChangesToState(id: Int, value: String) {
            when(id) {
                R.id.etProcent -> state.percent = value.toFloat(state.percent)
                R.id.etDeposit -> state.deposit = value.toFloat(state.deposit)
                R.id.etMounthAdd -> state.monthAdd = value.toInt(state.monthAdd)
                R.id.etMounthAddBreak -> state.monthAddBreak = value.toInt(state.monthAddBreak)
                R.id.etYearState -> state.years = value.toInt(state.years)
                R.id.etPortion -> state.portion = value.toInt(state.portion)
                R.id.etTakeOff -> state.takeOff = value.toFloat(state.takeOff)
                R.id.etTakeOffEndMonth -> state.takeOffEndMonth = value.toInt(state.takeOffEndMonth)
                R.id.etTakeOffCount -> state.takeOffCount = value.toFloat(state.takeOffCount)
        }
    }
}