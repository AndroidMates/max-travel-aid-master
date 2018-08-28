/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.garlandview.header;


import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class HeaderDecorator extends RecyclerView.ItemDecoration {

    private final int mHeaderHeight;
    private final int mOffset;

    public HeaderDecorator(int headerHeight, int offset) {
        this.mHeaderHeight = headerHeight;
        this.mOffset = offset;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        final int position = parent.getChildAdapterPosition(view);
        final boolean isFirst = position == 0;
        final boolean isLast = position == parent.getAdapter().getItemCount() - 1;
        final int topOffset = isFirst ? mHeaderHeight + mOffset : mOffset;
        final int bottomOffset = isLast ? mOffset : 0;
        outRect.set(0, topOffset, 0, bottomOffset);
    }
}
