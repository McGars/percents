package com.gars.percents.home

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.gars.percents.R
import com.gars.percents.common.component
import com.gars.percents.databinding.FragmentHomeBinding
import com.gars.percents.databinding.ToolbarBinding
import com.gars.percents.home.di.HomeScopeComponent
import com.gars.percents.home.presentation.HomeView
import com.gars.percents.home.presentation.UiHomeEvent
import io.reactivex.subjects.PublishSubject

class HomeFragment : Fragment(R.layout.fragment_home), HomeView {

    companion object {
        fun newInstance(): Fragment = HomeFragment()
    }

    override val source = PublishSubject.create<UiHomeEvent>()

    private val component: HomeScopeComponent by component()

    private val bindings: FragmentHomeBinding by viewBinding(FragmentHomeBinding::bind)

    private val toolbarBinding: ToolbarBinding by viewBinding(R.id.toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.binder.setup(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initHandleInputs()
    }

    private fun initHandleInputs() {
        with(bindings) {
            arrayOf(
                etProcent, etDeposit, etMounthAdd, etMounthAddBreak, etYearState,
                etPortion, etTakeOff, etTakeOffEndMonth, etTakeOffCount
            ).forEach { et ->
                et.doAfterTextChanged { editable ->
                    source.onNext(UiHomeEvent.FieldUpdate(et.id, editable.toString()))
                }
            }
        }
    }

    private fun initToolbar() {
        toolbarBinding.toolbar.apply {
            setTitle(R.string.app_name)
            inflateMenu(R.menu.main_menu)
            setOnMenuItemClickListener(::onMenuItemClick)
        }
    }

    private fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionSend -> source.onNext(UiHomeEvent.Calculate)
        }
        return true
    }
}