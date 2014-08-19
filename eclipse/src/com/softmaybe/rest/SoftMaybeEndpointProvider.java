package com.softmaybe.rest;

import retrofit.RestAdapter;

public final class SoftMaybeEndpointProvider {
	
	private static SoftMaybeEndpoint endpoint;

	private SoftMaybeEndpointProvider() {}
	
	public static synchronized SoftMaybeEndpoint get() {
		if (endpoint == null) {
			RestAdapter restAdapter = new RestAdapter.Builder()
			  .setEndpoint("http://www.softmaybe.com")
			  .build();
			endpoint = restAdapter.create(SoftMaybeEndpoint.class);
		}
		return endpoint;
	}
}
