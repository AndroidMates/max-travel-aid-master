/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import androidx.annotation.FontRes;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;

import io.codelabs.R;


/**
 * An extension to {@link AppCompatTextView} which aligns text to a 4dp baseline grid.
 * <p>
 * To achieve this we expose a {@code lineHeightHint} allowing you to specify the desired line
 * height (alternatively a {@code lineHeightMultiplierHint} to use a multiplier of the text size).
 * This line height will be adjusted to be a multiple of 4dp to ensure that baselines sit on
 * the grid.
 * <p>
 * We also adjust spacing above and below the text to ensure that the first line's baseline sits on
 * the grid (relative to the view's top) & that this view's height is a multiple of 4dp so that
 * subsequent views start on the grid.
 */
public class BaselineGridTextView extends AppCompatTextView {

    private final float FOUR_DIP;

    private float lineHeightMultiplierHint = 1f;
    private float lineHeightHint;
    private boolean maxLinesByHeight;
    private int extraTopPadding;
    private int extraBottomPadding;
    @FontRes
    private int fontResId;

    public BaselineGridTextView(Context context) {
        this(context, null);
    }

    public BaselineGridTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public BaselineGridTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.BaselineGridTextView, defStyleAttr, 0);

        // first check TextAppearance for line height & font attributes
        if (a.hasValue(R.styleable.BaselineGridTextView_android_textAppearance)) {
            int textAppearanceId =
                    a.getResourceId(R.styleable.BaselineGridTextView_android_textAppearance,
                            android.R.style.TextAppearance);
            TypedArray ta = context.obtainStyledAttributes(
                    textAppearanceId, R.styleable.BaselineGridTextView);
            parseTextAttrs(ta);
            ta.recycle();
        }

        // then check view attrs
        parseTextAttrs(a);
        maxLinesByHeight = a.getBoolean(R.styleable.BaselineGridTextView_maxLinesByHeight, false);
        a.recycle();

        FOUR_DIP = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, getResources().getDisplayMetrics());
        computeLineHeight();
    }

    public float getLineHeightMultiplierHint() {
        return lineHeightMultiplierHint;
    }

    public void setLineHeightMultiplierHint(float lineHeightMultiplierHint) {
        this.lineHeightMultiplierHint = lineHeightMultiplierHint;
        computeLineHeight();
    }

    public float getLineHeightHint() {
        return lineHeightHint;
    }

    public void setLineHeightHint(float lineHeightHint) {
        this.lineHeightHint = lineHeightHint;
        computeLineHeight();
    }

    public boolean getMaxLinesByHeight() {
        return maxLinesByHeight;
    }

    public void setMaxLinesByHeight(boolean maxLinesByHeight) {
        this.maxLinesByHeight = maxLinesByHeight;
        requestLayout();
    }

    @FontRes
    public int getFontResId() {
        return fontResId;
    }

    @Override
    public int getCompoundPaddingTop() {
        // include extra padding to place the first line's baseline on the grid
        return super.getCompoundPaddingTop() + extraTopPadding;
    }

    @Override
    public int getCompoundPaddingBottom() {
        // include extra padding to make the height a multiple of 4dp
        return super.getCompoundPaddingBottom() + extraBottomPadding;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        extraTopPadding = 0;
        extraBottomPadding = 0;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getMeasuredHeight();
        height += ensureBaselineOnGrid();
        height += ensureHeightGridAligned(height);
        setMeasuredDimension(getMeasuredWidth(), height);
        checkMaxLines(height, MeasureSpec.getMode(heightMeasureSpec));
    }

    private void parseTextAttrs(TypedArray a) {
        if (a.hasValue(R.styleable.BaselineGridTextView_lineHeightMultiplierHint)) {
            lineHeightMultiplierHint =
                    a.getFloat(R.styleable.BaselineGridTextView_lineHeightMultiplierHint, 1f);
        }
        if (a.hasValue(R.styleable.BaselineGridTextView_lineHeightHint)) {
            lineHeightHint = a.getDimensionPixelSize(
                    R.styleable.BaselineGridTextView_lineHeightHint, 0);
        }
        if (a.hasValue(R.styleable.BaselineGridTextView_android_fontFamily)) {
            fontResId = a.getResourceId(R.styleable.BaselineGridTextView_android_fontFamily, 0);
        }
    }

    /**
     * Ensures line height is a multiple of 4dp.
     */
    private void computeLineHeight() {
        Paint.FontMetrics fm = getPaint().getFontMetrics();
        float fontHeight = Math.abs(fm.ascent - fm.descent) + fm.leading;
        float desiredLineHeight = (lineHeightHint > 0)
                ? lineHeightHint
                : lineHeightMultiplierHint * fontHeight;

        int baselineAlignedLineHeight =
                (int) ((FOUR_DIP * (float) Math.ceil(desiredLineHeight / FOUR_DIP)) + 0.5f);
        setLineSpacing(baselineAlignedLineHeight - fontHeight, 1f);
    }

    /**
     * Ensure that the first line of text sits on the 4dp grid.
     */
    private int ensureBaselineOnGrid() {
        float baseline = getBaseline();
        float gridAlign = baseline % FOUR_DIP;
        if (gridAlign != 0) {
            extraTopPadding = (int) (FOUR_DIP - Math.ceil(gridAlign));
        }
        return extraTopPadding;
    }

    /**
     * Ensure that height is a multiple of 4dp.
     */
    private int ensureHeightGridAligned(int height) {
        float gridOverhang = height % FOUR_DIP;
        if (gridOverhang != 0) {
            extraBottomPadding = (int) (FOUR_DIP - Math.ceil(gridOverhang));
        }
        return extraBottomPadding;
    }

    /**
     * When measured with an exact height, text can be vertically clipped mid-line. Prevent
     * this by setting the {@code maxLines} property based on the available space.
     */
    private void checkMaxLines(int height, int heightMode) {
        if (!maxLinesByHeight || heightMode != MeasureSpec.EXACTLY) return;

        int textHeight = height - getCompoundPaddingTop() - getCompoundPaddingBottom();
        int completeLines = (int) Math.floor(textHeight / getLineHeight());
        setMaxLines(completeLines);
    }
}
