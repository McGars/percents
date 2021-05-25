package com.gars.percents.details.presentation.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.cleveroad.adaptivetablelayout.LinkedAdaptiveTableAdapter
import com.cleveroad.adaptivetablelayout.ViewHolder
import com.cleveroad.adaptivetablelayout.ViewHolderImpl
import com.gars.percents.details.R
import com.gars.percents.details.databinding.ItemCellBinding
import com.gars.percents.details.databinding.ItemCornerCellBinding
import com.gars.percents.details.databinding.ItemHeaderColumnCellBinding
import com.gars.percents.details.databinding.ItemHeaderRowCellBinding
import com.gars.percents.common.dp
import com.gars.percents.common.inflate


class CalculateTableAdapter(
    private val source: TableDataSource
) : LinkedAdaptiveTableAdapter() {

    private companion object {
        const val TOTAL_COLUMNS = 5
    }

    override fun getRowCount(): Int = source.size

    override fun getColumnCount(): Int = TOTAL_COLUMNS

    override fun onCreateItemViewHolder(parent: ViewGroup): ViewHolderImpl =
        CellViewHolder(parent.inflate(R.layout.item_cell))

    override fun onCreateColumnHeaderViewHolder(parent: ViewGroup): ViewHolderImpl =
        ColumnHeaderViewHolder(parent.inflate(R.layout.item_header_column_cell))

    override fun onCreateRowHeaderViewHolder(parent: ViewGroup): ViewHolderImpl =
        RowHeaderViewHolder(parent.inflate(R.layout.item_header_row_cell))

    override fun onCreateLeftTopHeaderViewHolder(parent: ViewGroup): ViewHolderImpl =
        CornerViewHolder(parent.inflate(R.layout.item_corner_cell))

    override fun onBindViewHolder(viewHolder: ViewHolder, row: Int, column: Int) {
        val vh = viewHolder as CellViewHolder
        val item = source.getCellData(column, row)
        vh.viewBinding.tvTitle.text = item.text
        vh.viewBinding.tvTitle.setTextColor(item.textColor)
        vh.viewBinding.tvSubtitle.isVisible = item.subtext.isNullOrBlank().not()
        vh.viewBinding.tvSubtitle.text = item.subtext
    }

    override fun onBindHeaderColumnViewHolder(viewHolder: ViewHolder, column: Int) {
        val vh = viewHolder as ColumnHeaderViewHolder
        val headerData = source.getHeaderData(column)
        vh.viewBinding.tvTitle.text = concatenateWithNewLineSeparator(headerData.text, headerData.subtext)
    }

    override fun onBindHeaderRowViewHolder(viewHolder: ViewHolder, row: Int) {
        val vh = viewHolder as RowHeaderViewHolder
        vh.viewBinding.tvTitle.text = row.toString()
    }

    override fun onBindLeftTopHeaderViewHolder(viewHolder: ViewHolder) {

    }

    override fun getColumnWidth(column: Int): Int = 90.dp

    override fun getRowHeight(row: Int): Int = 50.dp

    override fun getHeaderColumnHeight(): Int = 50.dp

    override fun getHeaderRowWidth(): Int = 30.dp

    private class CellViewHolder(itemView: View) : ViewHolderImpl(itemView) {
        val viewBinding = ItemCellBinding.bind(itemView)
    }

    private class ColumnHeaderViewHolder(itemView: View) : ViewHolderImpl(itemView) {
        val viewBinding = ItemHeaderColumnCellBinding.bind(itemView)
    }

    private class RowHeaderViewHolder(itemView: View) : ViewHolderImpl(itemView) {
        val viewBinding = ItemHeaderRowCellBinding.bind(itemView)
    }

    private class CornerViewHolder(itemView: View) : ViewHolderImpl(itemView) {
        val viewBinding = ItemCornerCellBinding.bind(itemView)
    }

    private fun concatenateWithNewLineSeparator(vararg texts: String?): String {
        return texts
            .mapNotNull { it }
            .joinToString(separator = " /\n")
    }

}