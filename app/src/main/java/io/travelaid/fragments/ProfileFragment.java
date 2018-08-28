package io.travelaid.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import io.travelaid.R;
import io.travelaid.ui.BaseActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment {
	
	@Override
	protected int getInflatedView() {
		return R.layout.fragment_profile;
	}
	
	@Override
	protected void onViewReady(Bundle instanceState, BaseActivity host, View view) {
		//todo: init here
	}
	
	
}
