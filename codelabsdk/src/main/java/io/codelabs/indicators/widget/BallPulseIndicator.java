/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.indicators.widget;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;

import io.codelabs.indicators.Indicator;

public class BallPulseIndicator extends Indicator {
	
	public static final float SCALE = 1.0f;
	
	//scale x ,y
	private final float[] scaleFloats = {SCALE, SCALE, SCALE};
	
	
	@Override
	public void draw(Canvas canvas, Paint paint) {
		float circleSpacing = 4;
		float radius = (Math.min(getWidth(), getHeight()) - circleSpacing * 2) / 6;
		float x = getWidth() / 2 - (radius * 2 + circleSpacing);
		float y = getHeight() / 2;
		for (int i = 0; i < 3; i++) {
			canvas.save();
			float translateX = x + (radius * 2) * i + circleSpacing * i;
			canvas.translate(translateX, y);
			canvas.scale(scaleFloats[i], scaleFloats[i]);
			canvas.drawCircle(0, 0, radius, paint);
			canvas.restore();
		}
	}
	
	@Override
	public ArrayList<ValueAnimator> onCreateAnimators() {
		ArrayList<ValueAnimator> animators = new ArrayList<>(0);
		int[] delays = {120, 240, 360};
		for (int i = 0; i < 3; i++) {
			int index = i;
			
			ValueAnimator scaleAnim = ValueAnimator.ofFloat(1, 0.3f, 1);
			
			scaleAnim.setDuration(750);
			scaleAnim.setRepeatCount(-1);
			scaleAnim.setStartDelay(delays[i]);
			
			addUpdateListener(scaleAnim, new ValueAnimator.AnimatorUpdateListener() {
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					scaleFloats[index] = (float) animation.getAnimatedValue();
					postInvalidate();
				}
			});
			animators.add(scaleAnim);
		}
		return animators;
	}
	
	
}
