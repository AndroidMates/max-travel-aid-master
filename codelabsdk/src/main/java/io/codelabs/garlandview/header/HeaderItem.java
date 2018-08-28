/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.garlandview.header;

import android.view.View;

import io.codelabs.garlandview.TailItem;
import io.codelabs.garlandview.inner.InnerRecyclerView;


/**
 * Implementation of {@link TailItem}
 */
public abstract class HeaderItem extends TailItem<InnerRecyclerView> {

    public HeaderItem(View itemView) {
        super(itemView);
    }

    /**
     * @return  Must return header main layout
     */
    abstract public View getHeader();

    /**
     * @return  Must return header alpha-layout.
     * Alpha-layout is the layout which will hide header's views,
     * when item will be scrolled to left or right.
     */
    abstract public View getHeaderAlphaView();

}
