package io.travelaid.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;

import io.travelaid.R;
import io.travelaid.ui.BaseActivity;

public class AboutPagerAdapter extends PagerAdapter {
	private BaseActivity host;
	private LayoutInflater inflater;
	
	private static final int PAGES = 4;
	
	@Nullable
	private View aboutApp = null;
	@Nullable
	private View aboutDev1 = null;
	@Nullable
	private View aboutDev2 = null;
	@Nullable
	private View aboutDev3 = null;
	
	private TextView username, description;
	private ImageView avatar;
	
	public AboutPagerAdapter(BaseActivity host) {
		this.host = host;
		this.inflater = LayoutInflater.from(host);
	}
	
	
	@NonNull
	@Override
	public Object instantiateItem(@NonNull ViewGroup container, int position) {
		View page = getPage(container, position);
		container.addView(page);
		return container;
	}
	
	private View getPage(ViewGroup container, int position) {
		switch (position) {
			case 0:
				if (aboutApp == null) {
					aboutApp = inflater.inflate(R.layout.about_app, container, false);
				}
				return aboutApp;
			case 1:
				if (aboutDev1 == null) {
					aboutDev1 = inflater.inflate(R.layout.about_dev, container, false);
					username = aboutDev1.findViewById(R.id.dev_username);
					description = aboutDev1.findViewById(R.id.dev_description);
					avatar = aboutDev1.findViewById(R.id.dev_avatar);
					
					username.setText(parseString(R.string.developer_quabynah));
					HtmlUtils.setTextWithNiceLinks(description, parseString(R.string.desc_quabynah));
					GlideApp.with(host.getApplicationContext())
							.load(parseString(R.string.quabynah_profile_url))
							.placeholder(R.drawable.avatar_placeholder)
							.error(R.drawable.avatar_placeholder)
							.circleCrop()
							.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
							.into(avatar);
					
				}
				return aboutDev1;
			case 2:
				if (aboutDev2 == null) {
					aboutDev2 = inflater.inflate(R.layout.about_dev, container, false);
					username = aboutDev2.findViewById(R.id.dev_username);
					description = aboutDev2.findViewById(R.id.dev_description);
					avatar = aboutDev2.findViewById(R.id.dev_avatar);
					
					username.setText(parseString(R.string.developer_fred));
					HtmlUtils.setTextWithNiceLinks(description, parseString(R.string.desc_fred));
					GlideApp.with(host.getApplicationContext())
							.load(parseString(R.string.fred_profile_url))
							.placeholder(R.drawable.avatar_placeholder)
							.error(R.drawable.avatar_placeholder)
							.circleCrop()
							.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
							.into(avatar);
				}
				return aboutDev2;
			case 3:
				if (aboutDev3 == null) {
					aboutDev3 = inflater.inflate(R.layout.about_dev, container, false);
					username = aboutDev3.findViewById(R.id.dev_username);
					description = aboutDev3.findViewById(R.id.dev_description);
					avatar = aboutDev3.findViewById(R.id.dev_avatar);
					
					username.setText(parseString(R.string.developer_ali));
					HtmlUtils.setTextWithNiceLinks(description, parseString(R.string.desc_ali));
					GlideApp.with(host.getApplicationContext())
							.load(parseString(R.string.ali_profile_url))
							.placeholder(R.drawable.avatar_placeholder)
							.error(R.drawable.avatar_placeholder)
							.circleCrop()
							.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
							.into(avatar);
				}
				return aboutDev3;
			default:
				throw new IllegalArgumentException("Cannot access current page");
		}
		
	}
	
	@Override
	public int getCount() {
		return PAGES;
	}
	
	@Override
	public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
		container.removeView((View) object);
	}
	
	@Override
	public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
		return view == o;
	}
	
	private String parseString(@StringRes int id) {
		return host.getString(id);
	}
}
