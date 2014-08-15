package com.softmaybe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

	private final static String TAG = "MainActivity";
	
	private final static String PREFS_FILENAME = "softmaybe.prefs";
	private final static String EMAIL_PREF = "email";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	    // Get intent, action and MIME type
	    Intent intent = getIntent();
	    if (Intent.ACTION_SEND.equals(intent.getAction()) && 
	    		"text/plain".equals(intent.getType())) {
            handleSendText(intent); 
	    } else {
	    	showEmailInput();
	    }
	}

	private void handleSendText(Intent intent) {
	    String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
	    if (sharedText != null) {
	    	Log.i(TAG, "Received share: " + sharedText);
	    }
	}
	
	private void showEmailInput() {
		setContentView(R.layout.activity_main);
	}
	
	private void saveEmailAndClose() {
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_save) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
