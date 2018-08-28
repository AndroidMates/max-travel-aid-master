/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.util

import android.view.View

/**
 * An extension which performs an [action] if the view has been measured, otherwise waits for it to
 * be measured, and then performs the [action].
 */
inline fun View.measured(crossinline action: () -> Unit) {
    if (isLaidOut) {
        action()
    } else {
        addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(view: View, left: Int, top: Int, right: Int,
                                        bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int,
                                        oldBottom: Int) {
                removeOnLayoutChangeListener(this)
                action()
            }
        })
    }
}
