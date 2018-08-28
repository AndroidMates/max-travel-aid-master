/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.recyclerview;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public abstract class OnItemSelectedListener implements RecyclerView.OnItemTouchListener {
	
	private final GestureDetector gestureDetector;
	
	public OnItemSelectedListener(Context context) {
		gestureDetector = new GestureDetector(context,
				new GestureDetector.SimpleOnGestureListener() {
					@Override
					public boolean onSingleTapUp(MotionEvent e) {
						return true;
					}
				});
	}
	
	public abstract void onItemSelected(RecyclerView.ViewHolder holder, int position);
	
	@Override
	public final boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
		if (gestureDetector.onTouchEvent(e)) {
			View touchedView = rv.findChildViewUnder(e.getX(), e.getY());
			onItemSelected(rv.findContainingViewHolder(touchedView),
					rv.getChildAdapterPosition(touchedView));
		}
		return false;
	}
	
	
	
	@Override
	public final void onTouchEvent(RecyclerView rv, MotionEvent e) {
		throw new UnsupportedOperationException("Not implemented");
	}
	
	@Override
	public final void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
		throw new UnsupportedOperationException("Not implemented");
	}
}
