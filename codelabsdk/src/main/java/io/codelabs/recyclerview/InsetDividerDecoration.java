/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.recyclerview;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

/**
 * A decoration which draws a horizontal divider between {@link RecyclerView.ViewHolder}s which
 * implement {@link Divided}; with a left inset.
 */
public class InsetDividerDecoration extends RecyclerView.ItemDecoration {

    private final Paint paint;
    private final int inset;
    private final int height;

    public InsetDividerDecoration(@Dimension int dividerHeight,
                                  @Dimension int leftInset,
                                  @ColorInt int dividerColor) {
        inset = leftInset;
        height = dividerHeight;
        paint = new Paint();
        paint.setColor(dividerColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(dividerHeight);
    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        if (childCount < 2) return;

        RecyclerView.LayoutManager lm = parent.getLayoutManager();
        float[] lines = new float[childCount * 4];
        boolean hasDividers = false;

        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.ViewHolder viewHolder = parent.getChildViewHolder(child);

            if (viewHolder instanceof Divided) {
                // skip if this *or next* view is activated
                if (child.isActivated()
                     || (i + 1 < childCount && parent.getChildAt(i + 1).isActivated())) {
                    continue;
                }
                lines[i * 4] = inset + lm.getDecoratedLeft(child);
                lines[(i * 4) + 2] = lm.getDecoratedRight(child);
                int y = lm.getDecoratedBottom(child) + (int) child.getTranslationY() - height;
                lines[(i * 4) + 1] = y;
                lines[(i * 4) + 3] = y;
                hasDividers = true;
            }
        }
        if (hasDividers) {
            canvas.drawLines(lines, paint);
        }
    }
}
