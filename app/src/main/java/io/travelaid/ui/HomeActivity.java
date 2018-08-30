package io.travelaid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import io.travelaid.R;
import io.travelaid.api.GlobalUser;
import io.travelaid.api.UserLoginListener;
import io.travelaid.data.Bus;
import io.travelaid.data.Passenger;
import io.travelaid.data.Seat;
import io.travelaid.fragments.ProfileFragment;
import io.travelaid.fragments.ReservationsFragment;
import io.travelaid.util.AppUtils;
import io.travelaid.util.GlideApp;
import io.travelaid.util.RecyclerViewAdapter;
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

	//instances of the recycler View
	private List<Bus> _bus;
	private RecyclerView _recyclerView;
	private RecyclerViewAdapter _adapter;
	
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

		populateBusDetails();

		_recyclerView = findViewById(R.id.recycler_view_id);
		_adapter = new RecyclerViewAdapter(this,_bus);
		_recyclerView.setLayoutManager(new GridLayoutManager(this,3));
		_recyclerView.setAdapter(_adapter);

		
	}

	//populates the recycler view with dummy bus details
	private void populateBusDetails(){

		// dummy instances of passengers
		Passenger passenger = new Passenger("","","","","");
		Passenger passenger1 = new Passenger("","","","","");
		Passenger passenger2 = new Passenger("","","","","");
		Passenger passenger3 = new Passenger("","","","","");
		Passenger passenger4 = new Passenger("","","","","");
		Passenger passenger5 = new Passenger("","","","","");
		Passenger passenger6 = new Passenger("","","","","");
		Passenger passenger7 = new Passenger("","","","","");
		Passenger passenger8 = new Passenger("","","","","");

		// dummy instances of seats
		List<Seat> seats = new ArrayList<>();
		seats.add(new Seat("1",false,passenger1));
		seats.add(new Seat("2",false,passenger2));
		seats.add(new Seat("3",false,passenger3));
		seats.add(new Seat("4",false,passenger4));
		seats.add(new Seat("5",false,passenger5));
		seats.add(new Seat("6",false,passenger6));
		seats.add(new Seat("7",false,passenger7));
		seats.add(new Seat("8",false,passenger8));


		_bus = new ArrayList<>();
		_bus.add(new Bus("GT-654-14",R.drawable.bus1,42,seats,8L ,"VIP"));
		_bus.add(new Bus("GT-844-08",R.drawable.bus2,45,seats,9L ,"VVIP"));
		_bus.add(new Bus("GT-154-13",R.drawable.bus3,65,seats,10L ,"STC"));
		_bus.add(new Bus("GT-354-12",R.drawable.bus4,55,seats,11L ,"A.O T&T"));
		_bus.add(new Bus("GT-654-18",R.drawable.bus5,66,seats,12L ,"EXPRESS"));
		_bus.add(new Bus("GT-844-09",R.drawable.bus6,44,seats,13L ,"VVIP"));
		_bus.add(new Bus("GT-154-15",R.drawable.bus7,38,seats,14L ,"YUTONG"));
		_bus.add(new Bus("GT-354-17",R.drawable.bus8,60,seats,15L ,"A.O T&T"));
		_bus.add(new Bus("GT-654-14",R.drawable.bus1,42,seats,8L ,"VIP"));
		_bus.add(new Bus("GT-844-08",R.drawable.bus2,45,seats,9L ,"VVIP"));
		_bus.add(new Bus("GT-154-13",R.drawable.bus3,65,seats,10L ,"STC"));
		_bus.add(new Bus("GT-354-12",R.drawable.bus4,55,seats,11L ,"A.O T&T"));
		_bus.add(new Bus("GT-654-18",R.drawable.bus5,66,seats,12L ,"EXPRESS"));
		_bus.add(new Bus("GT-844-09",R.drawable.bus6,44,seats,13L ,"VVIP"));
		_bus.add(new Bus("GT-154-15",R.drawable.bus7,38,seats,14L ,"YUTONG"));
		_bus.add(new Bus("GT-354-17",R.drawable.bus8,60,seats,15L ,"A.O T&T"));
		_bus.add(new Bus("GT-654-14",R.drawable.bus1,42,seats,8L ,"VIP"));
		_bus.add(new Bus("GT-844-08",R.drawable.bus2,45,seats,9L ,"VVIP"));
		_bus.add(new Bus("GT-154-13",R.drawable.bus3,65,seats,10L ,"STC"));
		_bus.add(new Bus("GT-354-12",R.drawable.bus4,55,seats,11L ,"A.O T&T"));
		_bus.add(new Bus("GT-654-18",R.drawable.bus5,66,seats,12L ,"EXPRESS"));
		_bus.add(new Bus("GT-844-09",R.drawable.bus6,44,seats,13L ,"VVIP"));
		_bus.add(new Bus("GT-154-15",R.drawable.bus7,38,seats,14L ,"YUTONG"));
		_bus.add(new Bus("GT-354-17",R.drawable.bus8,60,seats,15L ,"A.O T&T"));

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
