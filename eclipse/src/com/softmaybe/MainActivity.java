package com.softmaybe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.common.base.Optional;
import com.softmaybe.rest.ApiRequest;
import com.softmaybe.rest.ApiTask;
import com.softmaybe.util.Prefs;
import com.softmaybe.util.Urls;

public class MainActivity extends ActionBarActivity {

	private final static String TAG = "MainActivity";
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	    // Get intent, action and MIME type
	    Intent intent = getIntent();
	    if (Intent.ACTION_SEND.equals(intent.getAction()) && 
	    		"text/plain".equals(intent.getType())) {
            handleSendText(intent); 
	    } else {
	    	fillInEmailInput();
	    }
	}

	private void handleSendText(Intent intent) {
	    String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
	    if (sharedText != null) {
	    	Log.i(TAG, "Received share: " + sharedText);
	    	showSharedText(sharedText);
			String email = fillInEmailInput();	    	
	    	if (!email.equals("")) {
	    		storeReminderAndClose(email, sharedText);
	    	}
	    }
	}

	private void showSharedText(String shared) {
		TextView eventText = (TextView) findViewById(R.id.event_text);
		eventText.setText(shared);
	}
	
	private String fillInEmailInput() {
		TextView emailInput = (TextView) findViewById(R.id.email_input);
		String email = Prefs.getEmail(getBaseContext());
		emailInput.setText(email);
		return email;
	}
	
	private void saveEmailAndClose() {
		// TODO (matt): Toast message when closing.
		TextView emailInputView = (TextView) findViewById(R.id.email_input);
		String email = emailInputView.getText().toString();
		Prefs.setEmail(email, getBaseContext());

		// If the event text is set, we'll try to store an event.
		TextView eventTextView = (TextView) findViewById(R.id.event_text);
		String eventText = eventTextView.getText().toString();
		if (!eventText.equals(getString(R.string.default_event_text))) {
			storeReminderAndClose(email, eventText);
		} else {
			finish();
		}
	}
	
	private void storeReminderAndClose(String email, String sharedText) {
		Optional<String> url = Urls.findFirstUrl(sharedText.split(" "));
		if (url.isPresent()) {
			ApiRequest request = new ApiRequest(email, url.get());
			new ApiTask().execute(request);
			// TODO (matt): Toast message when storing reminder.
			Log.i(TAG, request.toString());
		} else {
			Log.i(TAG, "Unable to parse event url from : " +  sharedText);
		}
		finish();
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
			saveEmailAndClose();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
