package com.gars.percents.home.mvi

import com.gars.percents.R
import com.gars.percents.home.presentation.UiHomeEvent

class UiHomeEventTransformer: (UiHomeEvent) -> HomeFeature.Wish? {
        override fun invoke(event: UiHomeEvent): HomeFeature.Wish? {
            return when(event) {
                is UiHomeEvent.FieldUpdate -> convert(event.fieldId, event.value.takeIf { it.isNotEmpty() } ?: "0")
                else -> null
            }
        }
        
        private fun convert(fieldId: Int, value: String): HomeFeature.Wish? {
            return when(fieldId) {
                R.id.etProcent -> HomeFeature.Wish.PercentsUpdate(value.toFloat())
                R.id.etDeposit -> HomeFeature.Wish.DepositUpdate(value.toFloat())
                R.id.etMounthAdd -> HomeFeature.Wish.MonthAddUpdate(value.toInt())
                R.id.etMounthAddBreak -> HomeFeature.Wish.MonthAddBreakUpdate(value.toInt())
                R.id.etYearState -> HomeFeature.Wish.YearsUpdate(value.toInt())
                R.id.etPortion -> HomeFeature.Wish.PortionUpdate(value.toInt())
                R.id.etTakeOff -> HomeFeature.Wish.TakeOffUpdate(value.toFloat())
                R.id.etTakeOffEndMonth -> HomeFeature.Wish.TakeOffEndMonthUpdate(value.toInt())
                R.id.etTakeOffCount -> HomeFeature.Wish.TakeOffCountUpdate(value.toFloat())
                else -> null
            }
        }
    }