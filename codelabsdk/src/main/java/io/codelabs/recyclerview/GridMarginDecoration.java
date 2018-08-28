/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.recyclerview;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class GridMarginDecoration extends RecyclerView.ItemDecoration {
	
	private int space;
	
	public GridMarginDecoration(int space) {
		this.space = space;
	}
	
	@Override
	public void getItemOffsets(Rect outRect, View view,
	                           RecyclerView parent, RecyclerView.State state) {
		outRect.left = space;
		outRect.top = space;
		outRect.right = space;
		outRect.bottom = space;
	}
}

