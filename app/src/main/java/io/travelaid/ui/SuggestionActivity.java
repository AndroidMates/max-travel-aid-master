package io.travelaid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import io.travelaid.R;

public class SuggestionActivity extends BaseActivity {
	
	@Override
	protected int getContentView() {
		return R.layout.activity_suggestion;
	}
	
	@Override
	protected void onViewReady(Bundle savedInstanceState, Intent intent) {
		if (getSupportActionBar() != null) {
			getSupportActionBar().setHomeButtonEnabled(true);
			getSupportActionBar().setDisplayShowHomeEnabled(true);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				intentTo(HomeActivity.class);
		}
		return super.onOptionsItemSelected(item);
	}
}
