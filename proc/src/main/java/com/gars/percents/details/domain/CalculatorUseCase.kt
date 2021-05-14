package com.gars.percents.details.domain

import android.graphics.Color
import com.gars.percents.details.data.AccrualItem
import com.gars.percents.home.data.Percent
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


interface CalculatorUseCase {

    fun calculate(percent: Percent): List<AccrualItem>

    class Impl : CalculatorUseCase {

        private val sdf = SimpleDateFormat("yyyy.MM")

        override fun calculate(percent: Percent): List<AccrualItem> {

            val calendar = Calendar.getInstance()
            val items: MutableList<AccrualItem> = mutableListOf()

            // вычисляем ежемесяцный процент
            val monthPercent = percent.percent / 12f
            var countAllInvite = 0
            var lastIncoming = 0f
            var deposit = percent.deposit

            for (month in 1..percent.years * 12) {
                // поля
//            with(row) {
                // порядковый номер
                val monthNumber = month.toString()

                // проценты в каждом месяце
                val incoming = incomingByPercents(deposit, monthPercent)

                // разница между прошлым и текущим месяцем
                val incomingPercents = diffBetweenPreviousMonth(lastIncoming, incoming)

                lastIncoming = incoming

                // увеличение депозита на проценты
                deposit += incoming

                // определяем необходимо ли снимать с депозита
                val (takeOff, takeOffColor) = calculateTakeOff(percent, month)

                // снятие прибыли в месяц
                if (takeOff.isNotEmpty()) {
                    deposit -= percent.takeOffCount
                }

                val (incomingByUser, newDeposit) = incomingByUser(
                    percent,
                    month,
                    deposit,
                    countAllInvite
                )

                deposit = newDeposit
                countAllInvite = incomingByUser

                // подкрашиваем прибыль
                val incomingColor = if (incoming > 0) Color.GREEN else Color.DKGRAY

                // выводим прибыль
                val profit = formatNumber(incoming.toInt())
                // выводим общее количество вложений
                val investment = formatNumber(countAllInvite)
                // выводим итог на депозите
                val total = formatNumber(deposit.toInt())
                // дата
                calendar.add(Calendar.MONTH, 1)

                val date = sdf.format(calendar.time)

                items += AccrualItem(
                    monthNumber = monthNumber,
                    incomingPercents = incomingPercents,
                    takeOff = takeOff.takeIf { it.isNotEmpty() } ?: "-",
                    takeOffColor = takeOffColor,
                    incomingColor = incomingColor,
                    profit = profit,
                    investment = investment,
                    total = total,
                    date = date
                )
            }

            return items
        }

        private fun incomingByUser(
            percent: Percent,
            month: Int,
            currentDeposit: Float,
            countAllInvite: Int
        ): Pair<Int, Float> {
            var deposit = currentDeposit
            val incomingByUser =
                if (percent.monthAddBreak == 0 || percent.monthAddBreak >= month + 2) {
                    // пополнение депозита в месяц
                    deposit += percent.monthAdd
                    countAllInvite + percent.monthAdd
                } else {
                    0
                }
            return Pair(incomingByUser, deposit)
        }

        private fun incomingByPercents(
            deposit: Float,
            monthPercent: Float
        ): Float = deposit * monthPercent / 100

        private fun diffBetweenPreviousMonth(
            last_incoming: Float,
            incoming: Float
        ): String = when {
            last_incoming != 0f -> formatNumber((incoming - last_incoming).toInt())
            else -> ""
        }

        private fun formatNumber(i: Int) =
            NumberFormat.getNumberInstance(Locale.US).format(i.toLong())

        private fun calculateTakeOff(percent: Percent, month: Int): Pair<String, Int> {
            return if (percent.takeOff < month + 2 &&
                (percent.takeOffEndMonth == 0 || percent.takeOffEndMonth >= month + 2) &&
                percent.takeOffCount != 0f
            ) {
                val takeOffCount = (percent.takeOffCount / percent.portion).toInt()

                val takeOffColor = when (takeOffCount > 0) {
                    true -> Color.RED
                    false -> Color.GRAY
                }
                val takeOffText = formatNumber(takeOffCount)

                takeOffText to takeOffColor
            } else {
                "" to Color.GRAY
            }
        }
    }

}