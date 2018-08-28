/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.garlandview.header;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import android.view.View;

import io.codelabs.garlandview.TailItemTransformer;
import io.codelabs.garlandview.TailLayoutManager;


/**
 * Implementation of {@link TailLayoutManager.PageTransformer}.
 */
public class HeaderTransformer implements TailLayoutManager.PageTransformer<HeaderItem> {

    private final TailItemTransformer mTransformer;

    public HeaderTransformer() {
        mTransformer = new TailItemTransformer();
    }

    /**
     * Constructor that specifies inner item transformer.
     * @param transformer inner item transformer.
     */
    public HeaderTransformer(@NonNull TailItemTransformer transformer) {
        mTransformer = transformer;
    }

    @Override
    public void transformPage(@NonNull HeaderItem item, float scrollPosition) {
        final View header = item.getHeader();
        final TailItemTransformer.TransformParams params = mTransformer.getParamsForPosition(
                scrollPosition, header.getWidth(), header.getHeight());

        ViewCompat.setPivotX(header, params.pivotX);
        ViewCompat.setScaleX(header, params.scale);
        ViewCompat.setScaleY(header, params.scale);
        ViewCompat.setAlpha(header, scrollPosition < 0 ? params.alphaLeft : params.alphaRight);
        ViewCompat.setTranslationY(header, params.offsetY);

        ViewCompat.setAlpha(item.getHeaderAlphaView(), 1 - params.alphaChild);

        mTransformer.transformPage(item, scrollPosition);
    }

}
