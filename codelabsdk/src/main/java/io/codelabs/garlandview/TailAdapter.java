/*
 * Copyright (c) 2018. Property of Dennis Kwabena Bilson. No unauthorized duplication of this material should be made without prior permission from the developer
 */

package io.codelabs.garlandview;


import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter class for {@link TailRecyclerView}.
 * @param <T> outer item class.
 */
public abstract class TailAdapter<T extends TailItem> extends RecyclerView.Adapter<T> {}
