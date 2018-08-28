/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.util;

import android.graphics.ColorMatrix;
import android.util.Property;

/**
 * An extension to {@link ColorMatrix} which caches the saturation value for animation purposes.
 */
public class ObservableColorMatrix extends ColorMatrix {

    private float saturation = 1f;

    public ObservableColorMatrix() {
        super();
    }

    float getSaturation() {
        return saturation;
    }

    @Override
    public void setSaturation(float saturation) {
        this.saturation = saturation;
        super.setSaturation(saturation);
    }

    public static final Property<ObservableColorMatrix, Float> SATURATION =
            AnimUtils.createFloatProperty(
                    new AnimUtils.FloatProp<ObservableColorMatrix>("saturation") {
                        @Override
                        public float get(ObservableColorMatrix ocm) {
                            return ocm.getSaturation();
                        }

                        @Override
                        public void set(ObservableColorMatrix ocm, float saturation) {
                            ocm.setSaturation(saturation);
                        }
                    });

}
