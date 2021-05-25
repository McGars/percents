package com.gars.percents.common

import android.os.Handler
import android.os.Looper
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.badoo.binder.lifecycle.ManualLifecycle
import org.koin.core.component.KoinScopeComponent
import org.koin.core.parameter.DefinitionParameters
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified T : KoinScopeComponent> Fragment.component(
    noinline initializer: () -> List<Any> = { emptyList() }
): ComponentBindPropertyLifeCycle<Fragment, T> {
    return ComponentBindPropertyLifeCycle<Fragment, T>(this) {
        val params = initializer.invoke()
        val component = T::class.java.newInstance()
        component.apply {
            scope.addParameters(DefinitionParameters(params))
        }
    }
}

class ComponentBindPropertyLifeCycle<in R : Fragment, out T : KoinScopeComponent>(
    fragment: R,
    private val componentBinder: () -> T
) : ReadOnlyProperty<R, T> {

    private var isViewAttached = false
    private var component: T? = null

    init {
        fragment.lifecycle.addObserver(FragmentLifecycleComponent(fragment))
    }

    @MainThread
    override fun getValue(thisRef: R, property: KProperty<*>): T {
        return component ?: componentBinder().also {
            component = it
        }
    }

    inner class FragmentLifecycleComponent(private val fragment: R) : LifecycleObserver{

        @MainThread
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        fun onStart() {
            if (isViewAttached.not()) {
                isViewAttached = true
                fragment.viewLifecycleOwner.lifecycle.addObserver(ViewLifecycleComponent())
            }
        }

        @MainThread
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            mainHandler.post {
                component?.closeScope()
                component = null
            }
        }
    }

    inner class ViewLifecycleComponent : LifecycleObserver{

        private val manualLifecycle by lazy { component?.scope?.get<ManualLifecycle>() }

        @MainThread
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate() {
            manualLifecycle?.begin()
        }

        @MainThread
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            mainHandler.post {
                manualLifecycle?.end()
                isViewAttached = false
            }
        }
    }

    private companion object {
        private val mainHandler = Handler(Looper.getMainLooper())
    }
}