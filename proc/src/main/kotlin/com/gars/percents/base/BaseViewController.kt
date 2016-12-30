package com.gars.percents.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller

/**
 * Created by gars on 29.12.2016.
 */
abstract class BaseViewController(args: Bundle? = null) : Controller(args) {

    protected abstract fun inflateView(inflater: LayoutInflater, container: ViewGroup): View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        Log.d("onCreateView", javaClass.canonicalName)
        return inflateView(inflater, container).apply { onViewBound(this) }
    }

    /**
     * Call when view is created
     */
    protected open fun onViewBound(view: View) {}

    override fun onAttach(view: View) {
        setTitle()
        super.onAttach(view)
    }

    protected open fun setTitle() {
        var parentController = parentController
        while (parentController != null) {
            if (parentController is BaseViewController && parentController.getTitle() != null) {
                return
            }
            parentController = parentController.parentController
        }

        // set title
        getTitle()?.run { activity?.title = this }
                ?: getTitleInt().run { if (this != 0) activity?.setTitle(this) }
    }

    protected open fun getTitle(): String? = null

    protected open fun getTitleInt() = 0
}