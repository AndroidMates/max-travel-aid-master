/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import androidx.core.content.res.ResourcesCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import io.codelabs.R;
import io.codelabs.util.ViewUtils;


/**
 * A view which punches out some text from an opaque color block, allowing you to see through it.
 */
public class CutoutTextView extends View {

    public static final float PHI = 1.6182f;
    private final TextPaint textPaint;
    private Bitmap cutout;
    private int foregroundColor = Color.MAGENTA;
    private String text;
    private float textY;
    private float textX;
    private float maxTextSize;

    public CutoutTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);

        final TypedArray a =
                getContext().obtainStyledAttributes(attrs, R.styleable.CutoutTextView, 0, 0);
        if (a.hasValue(R.styleable.CutoutTextView_android_fontFamily)) {
            try {
                Typeface font = ResourcesCompat.getFont(getContext(),
                        a.getResourceId(R.styleable.CutoutTextView_android_fontFamily, 0));
                if (font != null) {
                    textPaint.setTypeface(font);
                }
            } catch (Resources.NotFoundException nfe) { }
        }
        if (a.hasValue(R.styleable.CutoutTextView_foregroundColor)) {
            foregroundColor = a.getColor(R.styleable.CutoutTextView_foregroundColor,
                    foregroundColor);
        }
        if (a.hasValue(R.styleable.CutoutTextView_android_text)) {
            text = a.getString(R.styleable.CutoutTextView_android_text);
        }
        maxTextSize = context.getResources().getDimensionPixelSize(R.dimen.display_4_text_size);
        a.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calculateTextPosition();
        createBitmap();
    }

    private void calculateTextPosition() {
        float targetWidth = getWidth() / PHI;
        float textSize = ViewUtils.getSingleLineTextSize(
                text, textPaint, targetWidth, 0f, maxTextSize, 0.5f, getResources().getDisplayMetrics());
        textPaint.setTextSize(textSize);

        // measuring text is fun :] see: https://chris.banes.me/2014/03/27/measuring-text/
        textX = (getWidth() - textPaint.measureText(text)) / 2;
        Rect textBounds = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), textBounds);
        float textHeight = textBounds.height();
        textY = (getHeight() + textHeight) / 2;
    }

    private void createBitmap() {
        if (cutout != null && !cutout.isRecycled()) {
            cutout.recycle();
        }
        cutout = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        cutout.setHasAlpha(true);
        Canvas cutoutCanvas = new Canvas(cutout);
        cutoutCanvas.drawColor(foregroundColor);

        // this is the magic – Clear mode punches out the bitmap
        textPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        cutoutCanvas.drawText(text, textX, textY, textPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(cutout, 0, 0, null);
    }

    @Override
    public boolean hasOverlappingRendering() {
        return true;
    }
}
