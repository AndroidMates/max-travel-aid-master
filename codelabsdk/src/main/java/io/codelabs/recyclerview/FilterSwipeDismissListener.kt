/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.recyclerview

/**
 * Interface for events related to swipe dismissing filters
 */
interface FilterSwipeDismissListener {
    fun onItemDismiss(position: Int)
}
