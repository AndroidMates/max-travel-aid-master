/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.util;

import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.core.view.ViewCompat;
import android.view.View;

/**
 * Utility helper for moving a {@link View} around using
 * {@link View#offsetLeftAndRight(int)} and
 * {@link View#offsetTopAndBottom(int)}.
 * <p>
 *  Allows the setting of absolute offsets (similar to translationX/Y), in addition to relative
 * offsets. Reapplies offsets after a layout pass (as long as you call {@link #onViewLayout()}).
 * <p>
 * Adapted from the mDesign support library.
 */
public class ViewOffsetHelper {

    /**
     * Animatable property
     */
    public static final FloatPropertyCompat<ViewOffsetHelper> OFFSET_Y
            = new FloatPropertyCompat<ViewOffsetHelper>("offsetY") {
        @Override
        public float getValue(ViewOffsetHelper offsetHelper) {
            return offsetHelper.getTopAndBottomOffset();
        }

        @Override
        public void setValue(ViewOffsetHelper offsetHelper, float offset) {
            offsetHelper.setTopAndBottomOffset((int) offset);
        }
    };

    private final View mView;

    private int mLayoutTop;
    private int mLayoutLeft;
    private int mOffsetTop;
    private int mOffsetLeft;

    public ViewOffsetHelper(View view) {
        mView = view;
    }

    public void onViewLayout() {
        // Grab the intended top & left
        mLayoutTop = mView.getTop();
        mLayoutLeft = mView.getLeft();

        // And offset it as needed
        updateOffsets();
    }

    /**
     * Set the top and bottom offset for this {@link ViewOffsetHelper}'s view by
     * an absolute amount.
     *
     * @param absoluteOffset the offset in px.
     * @return true if the offset has changed
     */
    public boolean setTopAndBottomOffset(int absoluteOffset) {
        if (mOffsetTop != absoluteOffset) {
            mOffsetTop = absoluteOffset;
            updateOffsets();
            return true;
        }
        return false;
    }

    /**
     * Set the top and bottom offset for this {@link ViewOffsetHelper}'s view by
     * an relative amount.
     */
    public void offsetTopAndBottom(int relativeOffset) {
        mOffsetTop += relativeOffset;
        updateOffsets();
    }

    /**
     * Set the left and right offset for this {@link ViewOffsetHelper}'s view by
     * an absolute amount.
     *
     * @param absoluteOffset the offset in px.
     * @return true if the offset has changed
     */
    public boolean setLeftAndRightOffset(int absoluteOffset) {
        if (mOffsetLeft != absoluteOffset) {
            mOffsetLeft = absoluteOffset;
            updateOffsets();
            return true;
        }
        return false;
    }

    /**
     * Set the left and right offset for this {@link ViewOffsetHelper}'s view by
     * an relative amount.
     */
    public void offsetLeftAndRight(int relativeOffset) {
        mOffsetLeft += relativeOffset;
        updateOffsets();
    }

    public int getTopAndBottomOffset() {
        return mOffsetTop;
    }

    public int getLeftAndRightOffset() {
        return mOffsetLeft;
    }

    /**
     * Notify this helper that a change to the view's offsets has occurred outside of this class.
     */
    public void resyncOffsets() {
        mOffsetTop = mView.getTop() - mLayoutTop;
        mOffsetLeft = mView.getLeft() - mLayoutLeft;
    }

    private void updateOffsets() {
        ViewCompat.offsetTopAndBottom(mView, mOffsetTop - (mView.getTop() - mLayoutTop));
        ViewCompat.offsetLeftAndRight(mView, mOffsetLeft - (mView.getLeft() - mLayoutLeft));
    }

}
