package com.gars.percents.details

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gars.percents.R
import com.gars.percents.common.component
import com.gars.percents.databinding.FragmentCountPageBinding
import com.gars.percents.databinding.ItemCountBinding
import com.gars.percents.databinding.ToolbarBinding
import com.gars.percents.details.data.AccrualItem
import com.gars.percents.details.di.DetailsScopeComponent
import com.gars.percents.details.presentation.DetailsView
import com.gars.percents.details.presentation.DetailsViewModel
import com.gars.percents.details.presentation.UiDetailsEvent
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject

class DetailsFragment : Fragment(R.layout.fragment_count_page), DetailsView,
    Consumer<DetailsViewModel> {

    companion object {
        fun newInstance(): Fragment = DetailsFragment()
    }

    override val source = PublishSubject.create<UiDetailsEvent>().apply {
        startWith(UiDetailsEvent.Ready)
    }

    private val component: DetailsScopeComponent by component()

    private val binding: FragmentCountPageBinding by viewBinding()

    private val toolbarBinding: ToolbarBinding by viewBinding(R.id.toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        component.binder.setup(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    override fun accept(model: DetailsViewModel) {
        when (model.state) {
            DetailsViewModel.State.IDLE -> source.onNext(UiDetailsEvent.Ready)
            DetailsViewModel.State.LOADING -> Unit
            DetailsViewModel.State.ERROR -> Unit
            DetailsViewModel.State.DATA -> {
                binding.tvMonthPercent.text = model.month
                showItems(model.actuals)
            }
        }
    }

    private fun showItems(items: List<AccrualItem>) {
        val inflater = LayoutInflater.from(activity)
        items.forEach { accrual ->

            // строка UI для заполнения данных
            val row = ItemCountBinding.inflate(inflater, binding.tableLayout, false)
            // поля
            with(row) {
                row.tvNumberMounth.text = accrual.monthNumber
                tvIncomingPercent.text = accrual.incomingPercents
                tvTakeoff.setTextColor(accrual.takeOffColor)
                tvTakeoff.text = accrual.takeOff
                tvIncoming.setTextColor(accrual.incomingColor)
                tvIncoming.text = accrual.profit
                tvInvite.text = accrual.investment
                tvCount.text = accrual.total
                tvYearMonth.text = accrual.date
                binding.tableLayout.addView(row.root)
                addTableLine()
            }
        }

    }

    private fun addTableLine() {
        binding.tableLayout.addView(View(context).apply {
            layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1)
            setBackgroundColor(Color.GRAY)
        })
    }

    private fun initToolbar() {
        toolbarBinding.toolbar.apply {
            setNavigationIcon(R.drawable.ic_baseline_arrow_back_24)
            setNavigationOnClickListener {
                requireActivity().onBackPressed()
            }
        }
    }

}