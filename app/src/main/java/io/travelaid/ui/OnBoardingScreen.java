package io.travelaid.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import javax.annotation.Nullable;

import io.travelaid.R;
import io.travelaid.util.ViewPagerData;
import io.travelaid.widget.InkPageIndicator;

public class OnBoardingScreen extends BaseActivity {
	
	@Override
	protected int getContentView() {
		return R.layout.activity_on_boarding_screen;
	}
	
	@Override
	protected void onViewReady(Bundle savedInstanceState, Intent intent) {
		// Init views
		InkPageIndicator indicator = findViewById(R.id.indicator);
		ViewPager pager = findViewById(R.id.pager);
		pager.setAdapter(new OnBoardingScreenAdapter(this));
		indicator.setViewPager(pager);
	}
	
	public void skip(View view) {
		intentTo(HomeActivity.class);
	}
	
	private class OnBoardingScreenAdapter extends PagerAdapter {
		private static final int PAGES = 2;
		private LayoutInflater inflater;
		private Activity host;
		
		@Nullable
		private View busPage;
		@Nullable
		private View bookingPage;
		private ImageView imageView;
		private TextView title;
		private TextView desc;
		
		public OnBoardingScreenAdapter(Activity activity) {
			this.host = activity;
			inflater = LayoutInflater.from(activity);
		}
		
		@NonNull
		@Override
		public Object instantiateItem(@NonNull ViewGroup container, int position) {
			View v = getPage(container, position);
			container.addView(v);
			return v;
		}
		
		private View getPage(ViewGroup container, int position) {
			switch (position) {
				case 0:
					if (busPage == null) {
						busPage = inflater.inflate(R.layout.pager_item, container, false);
						imageView = busPage.findViewById(R.id.pager_image);
						title = busPage.findViewById(R.id.pager_title);
						desc = busPage.findViewById(R.id.pager_desc);
						
						ViewPagerData pagerData = new ViewPagerData(R.drawable.bus,
								R.string.title1, R.string.desc1);
						
						Glide.with(host)
								.load(pagerData.getImage())
								.apply(RequestOptions.priorityOf(Priority.IMMEDIATE))
								.apply(RequestOptions.placeholderOf(R.color.pure_white))
								.apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))
								.into(imageView);
						
						title.setText(pagerData.getTitle());
						desc.setText(pagerData.getDescription());
					}
					return busPage;
				
				case 1:
					if (bookingPage == null) {
						bookingPage = inflater.inflate(R.layout.pager_item, container, false);
						imageView = bookingPage.findViewById(R.id.pager_image);
						title = bookingPage.findViewById(R.id.pager_title);
						desc = bookingPage.findViewById(R.id.pager_desc);
						
						ViewPagerData pagerData = new ViewPagerData(R.drawable.reserved_seat,
								R.string.title2, R.string.desc2);
						
						
						Glide.with(host)
								.load(pagerData.getImage())
								.apply(RequestOptions.priorityOf(Priority.IMMEDIATE))
								.apply(RequestOptions.placeholderOf(R.color.pure_white))
								.apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC))
								.into(imageView);
						
						title.setText(pagerData.getTitle());
						desc.setText(pagerData.getDescription());
					}
					return bookingPage;
				
				default:
					throw new IllegalArgumentException("This page cannot be defined");
			}
		}
		
		@Override
		public int getCount() {
			return PAGES;
		}
		
		@Override
		public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
			return view.equals(o);
		}
		
		@Override
		public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
			container.removeView((View) object);
		}
	}
}
