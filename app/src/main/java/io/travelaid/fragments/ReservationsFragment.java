package io.travelaid.fragments;

import android.os.Bundle;
import android.view.View;

import io.travelaid.R;
import io.travelaid.ui.BaseActivity;

public class ReservationsFragment extends BaseFragment {
	@Override
	protected int getInflatedView() {
		return R.layout.reservations_fragment;
	}
	
	@Override
	protected void onViewReady(Bundle instanceState, BaseActivity host, View view) {
	
	}
}
