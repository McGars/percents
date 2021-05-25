package com.gars.percents.details.presentation.adapter

import com.gars.percents.common.StringResources
import com.gars.percents.details.R
import com.gars.percents.details.data.AccrualItem


class TableDataSource(
    private val items: List<AccrualItem>,
    private val stringResources: StringResources
) {

    private companion object {
        const val COLUMN_MONTH = 0
        const val COLUMN_DATE = 1
        const val COLUMN_TAKEOFF = 2
        const val COLUMN_PROFIT = 3
        const val COLUMN_TOTAL = 4

    }

    val size get() = items.size

    fun getCellData(column: Int, row: Int): TableCell {
        val item = items[row]
        return when (column) {
            COLUMN_MONTH -> TableCell(item.monthNumber)
            COLUMN_DATE -> TableCell(item.date)
            COLUMN_TAKEOFF -> TableCell(item.takeOff, item.takeOffColor, item.investment)
            COLUMN_PROFIT -> TableCell(item.profit, item.incomingColor, item.incomingPercents)
            COLUMN_TOTAL -> TableCell(item.total)
            else -> throw RuntimeException("no column exists $column")
        }
    }

    fun getHeaderData(column: Int): TableCell {
        return when (column) {
            COLUMN_MONTH -> TableCell(
                text = column.toString()
            )
            COLUMN_DATE -> TableCell(
                text = stringResources.getString(R.string.result_page_date)
            )
            COLUMN_TAKEOFF -> TableCell(
                text = stringResources.getString(R.string.result_page_takeoff),
                subtext = stringResources.getString(R.string.result_page_add_in_month)
            )
            COLUMN_PROFIT -> TableCell(
                text = stringResources.getString(R.string.result_page_incomming),
            )
            COLUMN_TOTAL -> TableCell(
                text = stringResources.getString(R.string.result_page_total)
            )
            else -> throw RuntimeException("no column exists $column")
        }
    }

}