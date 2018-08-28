package io.travelaid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import io.travelaid.R;
import io.travelaid.util.AboutPagerAdapter;
import io.travelaid.widget.InkPageIndicator;

public class AboutUsActivity extends BaseActivity {
	ViewPager pager;
	InkPageIndicator indicator;
	
	@Override
	protected int getContentView() {
		return R.layout.activity_about_us;
	}
	
	@Override
	protected void onViewReady(Bundle savedInstanceState, Intent intent) {
		//Bind Views
		pager = findViewById(R.id.pager);
		indicator = findViewById(R.id.indicator);
		
		pager.setAdapter(new AboutPagerAdapter(this));
		pager.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.spacing_normal));
		indicator.setViewPager(pager);

		//getting reference to the toolbar and setting appropriate functions on it
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		if(getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setHomeButtonEnabled(true);
			//getSupportActionBar().setDisplayShowHomeEnabled(true);
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				//starts the home activity
				//startActivity(new Intent(getApplicationContext(),HomeActivity.class));
				intentTo(HomeActivity.class);
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
