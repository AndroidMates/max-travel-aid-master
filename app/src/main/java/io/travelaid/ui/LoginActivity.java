package io.travelaid.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

import io.travelaid.R;
import io.travelaid.api.AuthenticationListener;
import io.travelaid.api.DatabaseUpdateListener;
import io.travelaid.api.DocumentAccessListener;
import io.travelaid.api.GlobalUser;
import io.travelaid.api.UserLoginListener;
import io.travelaid.data.Bus;
import io.travelaid.data.Driver;
import io.travelaid.data.Passenger;
import io.travelaid.util.AppUtils;
import io.travelaid.util.UserPrefs;

public class LoginActivity extends BaseActivity implements
		DatabaseUpdateListener, AuthenticationListener, DocumentAccessListener, UserLoginListener {
	//Bind views
	EditText username, password;
	CheckBox userState;
	
	private UserPrefs prefs;
	
	@Override
	protected int getContentView() {
		return R.layout.activity_login;
	}
	
	@Override
	protected void onViewReady(Bundle savedInstanceState, Intent intent) {
		prefs = UserPrefs.get(this);
		prefs.addUserLoginListener(this);
		
		//Init views
		username = findViewById(R.id.username);
		password = findViewById(R.id.password);
		userState = findViewById(R.id.user_type);
	}
	
	@Override
	protected void onDestroy() {
		prefs.removeUserLoginListener(this);
		super.onDestroy();
	}
	
	public void login(View view) {
		//getting text from the views
		String _username = username.getText().toString().trim();
		String _password = password.getText().toString().trim();
		
		if (AppUtils.isValidEmail(_username) && AppUtils.isValidPassword(_password)) {
			//Show dialog
			showDialog(false, "Signing in to your account...");
			
			//Create user account
			AppUtils.signInOrCreateUser(this, _username, _password, LoginActivity.this);
			
		} else AppUtils.showToast(this, "Invalid username or password");
	}
	
	public void register(View view) {
        //getting text from the views
		String _username = username.getText().toString();
		String _password = password.getText().toString();
		
		if (AppUtils.isValidEmail(_username) && AppUtils.isValidPassword(_password)) {
			//Show dialog
			showDialog(false, "Creating account");
			
			//Create user account
			AppUtils.signInOrCreateUser(this, _username, _password, LoginActivity.this);
			
		} else AppUtils.showToast(this, "There seems to be a problem with your credentials");
	}
	
	//region AuthenticationListener
	@Override
	public void onAuthSuccess(@Nullable FirebaseUser user, boolean isNewUser) {
		AppUtils.showToast(getApplicationContext(), "Your login was successful. Finishing up the process...");
		
		GlobalUser globalUser;
		
		if (isNewUser) {
			if (userState.isChecked()) {
				//create driver
				globalUser = new Driver(user.getUid(), user.getDisplayName(), String.valueOf(user.getPhotoUrl()),
						user.getPhoneNumber(), AppUtils.GENDER_MALE, new Bus(), true);
			} else {
				globalUser = new Passenger(user.getUid(), user.getDisplayName(), String.valueOf(user.getPhotoUrl()),
						user.getPhoneNumber(), AppUtils.GENDER_MALE);
			}
			
			//Create User model
			AppUtils.storeOrUpdate(LoginActivity.this, globalUser, LoginActivity.this);
		} else {
			String path = String.format(userState.isChecked() ? AppUtils.DRIVER_REF : AppUtils.PASSENGER_REF + "/%s", user.getUid());
			AppUtils.getCurrentUser(LoginActivity.this, path, LoginActivity.this);
		}
	}
	
	@Override
	public void onAuthFailure(String reason) {
		dismissDialog();
		AppUtils.showToast(getApplicationContext(), reason);
	}
	
	@Override
	public void onAuthAborted(String reason) {
		dismissDialog();
		AppUtils.showToast(getApplicationContext(), reason);
	}
	//endregion AuthenticationListener
	
	//region DatabaseUpdateListener
	@Override
	public void onInstanceSuccess() {
		intentTo(HomeActivity.class);
	}
	
	@Override
	public void onInstanceError(@Nullable String errorMessage) {
		dismissDialog();
		AppUtils.showToast(getApplicationContext(), errorMessage);
	}
	//endregion DatabaseUpdateListener
	
	//region DocumentAccessListener
	@Override
	public void onSuccess(@Nullable DocumentSnapshot snapshot) {
		AppUtils.showLog(LoginActivity.this, snapshot.getData());
		
		//Create bundle and append user model
		GlobalUser globalUser = userState.isChecked() ? snapshot.toObject(Driver.class) : snapshot.toObject(Passenger.class);
		//Set logged in user
		prefs.setLoggedInUser(globalUser);
	}
	
	@Override
	public void onFailure(String reason) {
		dismissDialog();
		AppUtils.showToast(getApplicationContext(), reason);
	}
	//endregion DocumentAccessListener
	
	//region UserLoginListener
	@Override
	public void onUserLogin() {
		//Move to HomeScreen
		dismissDialog();
		intentTo(HomeActivity.class);
	}
	
	@Override
	public void onUserLogout() {
		/*Do nothing here*/
	}
	//endregion UserLoginListener

}
