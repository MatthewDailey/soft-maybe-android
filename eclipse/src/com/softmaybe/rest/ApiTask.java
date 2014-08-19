package com.softmaybe.rest;

import android.os.AsyncTask;
import android.util.Log;

public class ApiTask extends AsyncTask<ApiRequest, Void, ApiResult> {

	private final static String TAG = "ApiRequest";
	
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
		// TODO (matt): Show toast on result.
		Log.i(TAG, result.toString());
	}
}
