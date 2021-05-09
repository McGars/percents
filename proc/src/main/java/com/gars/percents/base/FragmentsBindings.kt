package com.gars.percents.base

import androidx.lifecycle.LifecycleOwner
import com.badoo.binder.Binder
import com.badoo.mvicore.android.lifecycle.CreateDestroyBinderLifecycle


abstract class FragmentsBindings<T : Any>(
    lifecycleOwner: LifecycleOwner
) {
    protected val binder = Binder(
        lifecycle = CreateDestroyBinderLifecycle(
            androidLifecycle = lifecycleOwner.lifecycle
        )
    )

    abstract fun setup(view: T)
}