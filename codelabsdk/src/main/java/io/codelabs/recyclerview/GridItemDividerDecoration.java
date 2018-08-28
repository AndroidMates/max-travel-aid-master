/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.recyclerview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

/**
 * A {@link RecyclerView.ItemDecoration} which draws dividers (along the right & bottom)
 * for {@link RecyclerView.ViewHolder}s which implement {@link Divided}.
 */
public class GridItemDividerDecoration extends RecyclerView.ItemDecoration {
	
	private final int dividerSize;
	private final Paint paint;
	
	public GridItemDividerDecoration(@Dimension int dividerSize,
	                                 @ColorInt int dividerColor) {
		this.dividerSize = dividerSize;
		paint = new Paint();
		paint.setColor(dividerColor);
		paint.setStyle(Paint.Style.FILL);
	}
	
	public GridItemDividerDecoration(@NonNull Context context,
	                                 @DimenRes int dividerSizeResId,
	                                 @ColorRes int dividerColorResId) {
		this(context.getResources().getDimensionPixelSize(dividerSizeResId),
				ContextCompat.getColor(context, dividerColorResId));
	}
	
	@Override
	public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
		if (parent.isAnimating()) return;
		
		final int childCount = parent.getChildCount();
		final RecyclerView.LayoutManager lm = parent.getLayoutManager();
		for (int i = 0; i < childCount; i++) {
			final View child = parent.getChildAt(i);
			RecyclerView.ViewHolder viewHolder = parent.getChildViewHolder(child);
			
			if (viewHolder instanceof Divided) {
				final int right = lm.getDecoratedRight(child);
				final int bottom = lm.getDecoratedBottom(child);
				// draw the bottom divider
				canvas.drawRect(lm.getDecoratedLeft(child),
						bottom - dividerSize,
						right,
						bottom,
						paint);
				// draw the right edge divider
				canvas.drawRect(right - dividerSize,
						lm.getDecoratedTop(child),
						right,
						bottom - dividerSize,
						paint);
			}
		}
	}
	
}
