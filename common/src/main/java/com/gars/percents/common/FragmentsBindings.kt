package com.gars.percents.common

import com.badoo.binder.Binder
import com.badoo.binder.lifecycle.Lifecycle

abstract class FragmentsBindings<T : Any>(
    lifecycleOwner: Lifecycle
) {
    protected val binder = Binder(
        lifecycle = lifecycleOwner
    )

    abstract fun setup(view: T)
}