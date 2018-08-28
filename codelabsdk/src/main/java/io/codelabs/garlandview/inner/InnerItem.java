/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.garlandview.inner;


import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

/**
 * ViewHolder class for {@link InnerRecyclerView}.
 */
public abstract class InnerItem extends RecyclerView.ViewHolder {

    private int prevHeight;

    public InnerItem(View itemView) {
        super(itemView);
    }

    void onItemViewHeightChanged(int newHeight) {
        if ((prevHeight != 0) && (newHeight != prevHeight)) {
            final View view = getInnerLayout();
            ViewCompat.setY(view, ViewCompat.getY(view) - (prevHeight - newHeight));
        }
        prevHeight = newHeight;
    }

    void resetInnerLayoutOffset() {
        ViewCompat.setY(getInnerLayout(), 0);
    }

    /**
     * @return  Must return main item layout
     */
    protected abstract View getInnerLayout();

}
