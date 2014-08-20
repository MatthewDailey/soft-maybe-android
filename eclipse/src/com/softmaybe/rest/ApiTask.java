package com.softmaybe.rest;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.softmaybe.util.Toasts;

public class ApiTask extends AsyncTask<ApiRequest, Void, ApiResult> {

	private final static String TAG = "ApiRequest";
	
	private final Activity context;
	
	public ApiTask(Activity context) {
		this.context = context;
	}
	
	@Override
	protected ApiResult doInBackground(ApiRequest... params) {
		if (params.length != 1) {
			throw new IllegalArgumentException("Cannot run ApiTask with " +
					"multiple requests.");
		}
		ApiRequest request = params[0];
		
		return SoftMaybeEndpointProvider.get().api(request.getEmail(), 
				request.getEvent());
	}
	
	protected void onPostExecute(ApiResult result) {
		if (result.isSuccess()) {
			Toasts.slow(context, "Added Reminder:\n" + result.getName());
		} else {
			Toasts.slow(context, "Failure!\n" + result.getMessage());
		}
		Log.i(TAG, result.toString());
	}
}
