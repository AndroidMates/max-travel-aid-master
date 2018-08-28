package io.travelaid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.target.Target;

import io.travelaid.R;
import io.travelaid.api.GlobalUser;
import io.travelaid.api.UserLoginListener;
import io.travelaid.fragments.ProfileFragment;
import io.travelaid.fragments.ReservationsFragment;
import io.travelaid.util.AppUtils;
import io.travelaid.util.GlideApp;
import io.travelaid.util.UserPrefs;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, UserLoginListener {
	
	//User extra
	public static final String EXTRA_USER = "EXTRA_USER";
	
	//Prefs
	private UserPrefs prefs;
	
	//instances of the classes for the DrawerLayout
	private DrawerLayout mDrawerLayout;
	private NavigationView mNavView;
	private Toolbar toolbar;
	private ActionBarDrawerToggle mToggle;
	private GlobalUser globalUser;
	
	@Override
	protected int getContentView() {
		return R.layout.activity_home;
	}
	
	@Override
	protected void onViewReady(Bundle savedInstanceState, Intent intent) {
		//Init prefs and add listener
		prefs = UserPrefs.get(this);
		prefs.addUserLoginListener(this);
		globalUser = prefs.getUser();
		
		//instantiation drawer classes
		mDrawerLayout = findViewById(R.id.drawer);
		toolbar = findViewById(R.id.toolbar);
		mNavView = findViewById(R.id.nav_view);
		
		setSupportActionBar(toolbar);
		
		mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
		mDrawerLayout.addDrawerListener(mToggle);
		mToggle.syncState();
		
		mNavView.setNavigationItemSelectedListener(this);
		if (globalUser != null) {
			setupHeaderView(mNavView.getHeaderView(0));
		}
		
	}
	
	private void setupHeaderView(View headerView) {
		ImageView avatar = headerView.findViewById(R.id.header_avatar);
		TextView username = headerView.findViewById(R.id.header_username);
		TextView email = headerView.findViewById(R.id.header_email);
		
		//Load image for user
		GlideApp.with(this)
				.asBitmap()
				.load(globalUser.profile)
				.placeholder(R.drawable.avatar_placeholder)
				.error(R.drawable.avatar_placeholder)
				.fallback(R.drawable.avatar_placeholder)
				.circleCrop()
				.override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
				.into(avatar);
		
		if (AppUtils.isTextNullOrEmpty(globalUser.username) || AppUtils.isTextNullOrEmpty(globalUser.phoneNumber)) {
			email.setVisibility(View.GONE);
			username.setText(R.string.profile_update_prompt);
		} else {
			username.setText(globalUser.username);
			email.setText(globalUser.phoneNumber);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_passenger, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {
			case R.id.menu_about:
				//starts the about us activity
				intentTo(AboutUsActivity.class);
				break;
			case R.id.menu_suggestion:
				intentTo(SuggestionActivity.class);
				break;
			case R.id.menu_settings:
				//startActivity(new Intent(this, SettingActivity.class));
				break;
			case R.id.menu_search:
				startActivity(new Intent(this, SearchActivity.class));
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
		if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
			mDrawerLayout.closeDrawer(GravityCompat.START);
		} else super.onBackPressed();
	}
	
	//todo: set profile navigation here
	public void viewProfile(View view) {
		mDrawerLayout.closeDrawer(GravityCompat.START);
	}
	
	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
		switch (menuItem.getItemId()) {
			//ids for the items in nthe navigation drawer
			case R.id.profile:
				navigateToFragment(new ProfileFragment(), R.id.frame_home);
				break;
			case R.id.reservations:
				navigateToFragment(new ReservationsFragment(), R.id.frame_home);
				break;
			case R.id.payment_method:
				/*Moved to settings*/
				break;
			case R.id.upcoming_trips:
				navigateToFragment(new ProfileFragment(), R.id.frame_home);
				break;
			case R.id.made_trips:
				navigateToFragment(new ProfileFragment(), R.id.frame_home);
				break;
			case R.id.logout:
				auth.signOut(); //Sign out of firebase
				prefs.logout(); //Sign out of application
				break;
		}
		
		mDrawerLayout.closeDrawer(GravityCompat.START);
		return true;
	}
	
	@Override
	public void onUserLogin() {
		/*do nothing here*/
	}
	
	@Override
	public void onUserLogout() {
		intentTo(LoginActivity.class);
	}
	
	@Override
	protected void onDestroy() {
		prefs.removeUserLoginListener(this);
		super.onDestroy();
	}
}
