package com.softmaybe.util;

import android.content.Context;
import android.content.SharedPreferences;

public final class Prefs {

	// Do not instantiate.
	private Prefs() {}
	
	private final static String PREFS_FILENAME = "softmaybe.prefs";
	private final static String EMAIL_PREF = "email";
	
	public static String getEmail(Context context) {
		SharedPreferences settings = context.getSharedPreferences(
				PREFS_FILENAME, Context.MODE_PRIVATE);
		return settings.getString(EMAIL_PREF, "" /* defaultValue */);
	}
	
	public static void setEmail(String email, Context context) {
		SharedPreferences settings = context.getSharedPreferences(
				PREFS_FILENAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(EMAIL_PREF, email);
		editor.commit();
	}
}
