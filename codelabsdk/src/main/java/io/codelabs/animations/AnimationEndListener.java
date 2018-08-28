/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.animations;

import android.view.animation.Animation;

/**
 * Just sugar for code clean
 */
public abstract class AnimationEndListener implements Animation.AnimationListener {
	
	@Override
	public void onAnimationStart(Animation animation) {
		// do nothing
	}
	
	@Override
	public void onAnimationRepeat(Animation animation) {
		// do nothing
	}
	
}
