package io.travelaid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import io.travelaid.R;
import io.travelaid.data.Passenger;
import io.travelaid.util.AppUtils;
import io.travelaid.util.UserPrefs;

/**
 * Splash activity
 */
public class SplashActivity extends BaseActivity {
	
	@Override
	protected int getContentView() {
		return R.layout.activity_splash;
	}
	
	@Override
	protected void onViewReady(Bundle savedInstanceState, Intent intent) {
		UserPrefs prefs = UserPrefs.get(this);
		//todo: remove this user
		prefs.setLoggedInUser(new Passenger(
				String.valueOf(System.currentTimeMillis()), getString(R.string.dummy_username),
				getString(R.string.quabynah_profile_url),
				"0554022344", AppUtils.GENDER_MALE
		));
		
		//Show screen for 0.8 secs and navigate to the appropriate screen after user login state
		// is verified
		new Handler().postDelayed(() -> {
			if (prefs.isLoggedIn()) {
				intentTo(HomeActivity.class);
			} else {
				intentTo(LoginActivity.class);
			}
		}, 1850);
	}
}
