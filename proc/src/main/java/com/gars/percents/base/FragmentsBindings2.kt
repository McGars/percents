package com.gars.percents.base

import com.badoo.binder.Binder
import com.badoo.binder.lifecycle.Lifecycle

abstract class FragmentsBindings2<T : Any>(
    lifecycleOwner: Lifecycle
) {
    protected val binder = Binder(
        lifecycle = lifecycleOwner
    )

    abstract fun setup(view: T)
}