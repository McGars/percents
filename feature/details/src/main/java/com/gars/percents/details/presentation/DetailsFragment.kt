package com.gars.percents.details.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gars.percents.common.component
import com.gars.percents.common.databinding.ToolbarBinding
import com.gars.percents.details.R
import com.gars.percents.details.databinding.FragmentCalculateBinding
import com.gars.percents.details.di.DetailsScopeComponent
import com.gars.percents.details.presentation.view.DetailsView
import com.gars.percents.details.presentation.model.DetailsViewModel
import com.gars.percents.details.presentation.adapter.CalculateTableAdapter
import com.gars.percents.details.presentation.adapter.TableDataSource
import com.gars.percents.details.presentation.event.UiDetailsEvent
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject

class DetailsFragment : Fragment(R.layout.fragment_calculate), DetailsView,
    Consumer<DetailsViewModel> {

    companion object {
        fun newInstance(): Fragment = DetailsFragment()
    }

    override val source = PublishSubject.create<UiDetailsEvent>().apply {
        startWith(UiDetailsEvent.Ready)
    }

    private val component: DetailsScopeComponent by component()

    private val binding: FragmentCalculateBinding by viewBinding()

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
                toolbarBinding.toolbar.title = model.month
                showItems(model.actuals)
            }
        }
    }

    private fun showItems(items: List<com.gars.percents.details.data.AccrualItem>) {
        val adapter = CalculateTableAdapter(TableDataSource(items, component.stringResources))
        binding.tableLayout.setAdapter(adapter)
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