/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.garlandview.inner;


import android.content.Context;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;

/**
 * Extended {@link RecyclerView}
 * that works <b>only</b> with {@link InnerLayoutManager} and {@link InnerAdapter}.
 */
public class InnerRecyclerView extends RecyclerView {

    public InnerRecyclerView(Context context) {
        super(context);
    }

    public InnerRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public InnerRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * @param adapter must be instance of TailAdapter class
     */
    @Override
    public void setAdapter(Adapter adapter) {
        if (!(adapter instanceof InnerAdapter)) {
            throw new IllegalArgumentException("Adapter must be instance of InnerAdapter class");
        }
        super.setAdapter(adapter);
    }

    /**
     *
     * @param lm must be instance of InnerLayoutManager class
     */
    @Override
    public void setLayoutManager(LayoutManager lm) {
        if (!(lm instanceof InnerLayoutManager)) {
            throw new IllegalArgumentException("LayoutManager must be instance of InnerLayoutManager class");
        }
        super.setLayoutManager(lm);
    }

}
