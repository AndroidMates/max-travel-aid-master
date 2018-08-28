/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.recyclerview

import android.content.Context
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

/**
 * Base class for loading data; extending types are responsible for providing implementations of
 * [.onDataLoaded] to do something with the data and [.cancelLoading] to
 * cancel any activity.
 */
abstract class BaseDataManager<T>(context: Context) : DataLoadingSubject {

    private val loadingCount: AtomicInteger = AtomicInteger(0)
    private var loadingCallbacks: MutableList<DataLoadingSubject.DataLoadingCallbacks>? = null

    abstract fun onDataLoaded(data: T)

    abstract fun cancelLoading()

    override val isDataLoading: Boolean
        get() = loadingCount.get() > 0

    override fun registerCallback(callback: DataLoadingSubject.DataLoadingCallbacks) {
        if (loadingCallbacks == null) {
            loadingCallbacks = ArrayList<DataLoadingSubject.DataLoadingCallbacks>(1)
        }
        loadingCallbacks!!.add(callback)
    }

    override fun unregisterCallback(callback: DataLoadingSubject.DataLoadingCallbacks) {
        if (loadingCallbacks != null && loadingCallbacks!!.contains(callback)) {
            loadingCallbacks!!.remove(callback)
        }
    }

    protected fun loadStarted() {
        if (0 == loadingCount.getAndIncrement()) {
            dispatchLoadingStartedCallbacks()
        }
    }

    protected fun loadFinished() {
        if (0 == loadingCount.decrementAndGet()) {
            dispatchLoadingFinishedCallbacks()
        }
    }

    protected fun resetLoadingCount() {
        loadingCount.set(0)
    }

    protected fun dispatchLoadingStartedCallbacks() {
        if (loadingCallbacks == null || loadingCallbacks!!.isEmpty()) return
        for (loadingCallback in loadingCallbacks!!) {
            loadingCallback.dataStartedLoading()
        }
    }

    protected fun dispatchLoadingFinishedCallbacks() {
        if (loadingCallbacks == null || loadingCallbacks!!.isEmpty()) return
        for (loadingCallback in loadingCallbacks!!) {
            loadingCallback.dataFinishedLoading()
        }
    }

}
