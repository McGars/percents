package com.gars.percents.details

import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gars.percents.R
import com.gars.percents.base.BaseViewController
import com.gars.percents.home.State
import com.gars.percents.tools.Constants
import kotlinx.android.synthetic.main.item_count.view.*
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by gars on 29.12.2016.
 */
class DetailsController(bundle: Bundle) : BaseViewController(bundle) {

    private val state : State by lazy { args.getSerializable(Constants.STATE) as State }
    private val sdf = SimpleDateFormat("yyyy.MM")
    private var tableLayout: ViewGroup? = null
    private var tvMonthPercent: TextView? = null

    override fun getTitleInt() = R.string.details

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup)
            = inflater.inflate(R.layout.view_count_page, container, false)


    override fun onViewBound(view: View) {
        tableLayout = view.findViewById(R.id.tableLayout) as? ViewGroup
        tvMonthPercent = view.findViewById(R.id.tvMonthPercent) as? TextView
        buildViews()
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        Log.d("onDestroyView", "DetailsController")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "DetailsController")
    }

    private fun buildViews() {
        val calendar = Calendar.getInstance()

        val greenColor = ContextCompat.getColor(activity, android.R.color.holo_green_dark)

        // вычисляем ежемесяцный процент
        val monthPercent = state.percent / 12f
        tvMonthPercent?.text = activity?.getString(R.string.persendInMonth, monthPercent)
        var count_all_invite = 0
        var last_incoming = 0f
        val inflater = LayoutInflater.from(applicationContext)
        for (i in 1 .. state.yearState * 12) {
            // строка UI для заполнения данных
            val row = inflater.inflate(R.layout.item_count, tableLayout, false)
            // поля
            with(row) {
                // порядковый номер
                row.tvNumberMounth.text = (i).toString()

                // проценты в каждом месяце
                val incoming = state.deposit * monthPercent / 100

                // разница между прошлым и текущим месяцем
                if (last_incoming != 0f)
                    tvIncomingProcents.text = formatnumber((incoming - last_incoming).toInt())

                last_incoming = incoming

                // увеличение депозита на проценты
                state.deposit += incoming

                // определяем необходимо ли снимать с депозита
                if (state.takeOff < i + 2 &&
                        (state.takeOffEndMonth == 0 || state.takeOffEndMonth >= i + 2) &&
                        state.takeOffCount != 0f) {
                    // снятие прибыли в месяц
                    state.deposit -= state.takeOffCount

                    // подкрашиваем снятие в UI
                    val _t_k = (state.takeOffCount / state.portion).toInt()
                    tvTakeoff.setTextColor(when(_t_k > 0){
                        true -> Color.RED
                        false-> Color.GRAY
                    })
                    // выводим в UI
                    tvTakeoff.text = formatnumber((state.takeOffCount / state.portion).toInt())
                }
                if (state.monthAddBreak == 0 || state.monthAddBreak >= i + 2) {
                    // пополнение депозита в месяц
                    state.deposit += state.monthAdd
                    count_all_invite += state.monthAdd
                } else {
                    count_all_invite = 0
                }

                // подкрашиваем прибыль
                tvIncoming.setTextColor(if (incoming > 0) greenColor else Color.DKGRAY)

                // выводим прибыль
                tvIncoming.text = formatnumber(incoming.toInt())
                // выводим общее количество вложений
                tvInvite.text = formatnumber(count_all_invite)
                // выводим итог на депозите
                tvCount.text = formatnumber(state.deposit.toInt())
                // дата
                calendar.add(Calendar.MONTH, 1)
                tvYearMonth.text = sdf.format(calendar.time)
                // добавляем строку
                tableLayout?.addView(row)
                // добавляем линию
                addTableLine()
            }
        }

    }

    // формат цыфр
    private fun formatnumber(i: Int) =  NumberFormat.getNumberInstance(Locale.US).format(i.toLong())

    private fun addTableLine() {
        tableLayout?.addView(View(activity).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1)
            setBackgroundColor(Color.GRAY)
        })
    }
}