package io.travelaid.api;

/**
 * Listens for the login & logout state of a user
 */
public interface UserLoginListener {
	
	void onUserLogin();
	
	void onUserLogout();
}
