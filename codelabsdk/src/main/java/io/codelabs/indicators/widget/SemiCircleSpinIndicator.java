/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.indicators.widget;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;


import java.util.ArrayList;

import io.codelabs.indicators.Indicator;

public class SemiCircleSpinIndicator extends Indicator {

    private float degress;

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.rotate(degress,centerX(),centerY());
        RectF rectF=new RectF(0,0,getWidth(),getHeight());
        canvas.drawArc(rectF,-60,120,false,paint);
    }

    @Override
    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators=new ArrayList<>();
        ValueAnimator rotateAnim=ValueAnimator.ofFloat(0,180,360);
        addUpdateListener(rotateAnim,new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degress= (float) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        rotateAnim.setDuration(600);
        rotateAnim.setRepeatCount(-1);
        animators.add(rotateAnim);
        return animators;
    }

}
