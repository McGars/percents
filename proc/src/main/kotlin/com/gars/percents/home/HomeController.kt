package com.gars.percents.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.gars.percents.R
import com.gars.percents.base.BaseViewController
import com.gars.percents.details.DetailsController
import com.gars.percents.home.mvp.HomeModelImpl
import com.gars.percents.home.mvp.HomePresenterImpl
import com.gars.percents.home.mvp.presenter.HomePresenter
import com.gars.percents.home.mvp.view.HomeView
import com.gars.percents.tools.Constants
import kotlinx.android.synthetic.main.view_home.view.*
import kotlin.properties.Delegates

/**
 * Created by gars on 29.12.2016.
 */
class HomeController : BaseViewController(), View.OnClickListener, HomeView {

    private var presenter: HomePresenter by Delegates.notNull()
    private var inputs: Array<EditText>? = null

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup)
        = inflater.inflate(R.layout.view_home, container, false)


    override fun onViewBound(view: View) {
        view.btStart.setOnClickListener(this)
        inputs = with(view) {
           arrayOf(etProcent, etDeposit, etMounthAdd, etMounthAddBreak, etYearState,
                    etPortion, etTakeOff, etTakeOffEndMonth, etTakeOffCount)
        }
        presenter = HomePresenterImpl(this, HomeModelImpl())
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btStart) {
            presenter.buildData(inputs)
        }
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        Log.d("onDestroyView", "HomeController")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "HomeController")
    }

    /**
     * Show data after HomePresenter.buildData
     */
    override fun showData(state: State) {
        router.pushController(RouterTransaction.with(
                DetailsController(Bundle().apply { putSerializable(Constants.STATE, state) }))
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    override fun getTitleInt() = R.string.app_name

}