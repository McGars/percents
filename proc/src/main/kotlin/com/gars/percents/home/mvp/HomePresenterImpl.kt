package com.gars.percents.home.mvp

import android.widget.EditText
import com.gars.percents.home.mvp.model.HomeModel
import com.gars.percents.home.mvp.presenter.HomePresenter
import com.gars.percents.home.mvp.view.HomeView

/**
 * Created by gars on 29.12.2016.
 */
class HomePresenterImpl(val view: HomeView, var model: HomeModel) : HomePresenter {
    override fun buildData(inputs: Array<EditText>?) {
        val state = model.buildData(inputs)
        view.showData(state)
    }
}