package com.gars.percents.common

import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.subjects.PublishSubject


interface BaseObservableSource<T> : ObservableSource<T> {

    val source: PublishSubject<T>

    override fun subscribe(observer: Observer<in T>) {
        source.subscribe(observer)
    }
}