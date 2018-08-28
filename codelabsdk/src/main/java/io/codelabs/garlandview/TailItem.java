/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.garlandview;


import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

/**
 * ViewHolder class for {@link TailRecyclerView}.
 * @param <T> inner item ViewGroup subclass.
 */
public abstract class TailItem<T extends ViewGroup> extends RecyclerView.ViewHolder {

    public TailItem(View itemView) {
        super(itemView);
    }

    /**
     * @return  Must return inner item scroll state - is it scrolling now or not.
     */
    abstract public boolean isScrolling();

    /**
     * @return  Must return inner item ViewGroup, that contains inner items.
     */
    abstract public T getViewGroup();

}
