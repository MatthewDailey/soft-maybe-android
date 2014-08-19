package com.softmaybe.rest;

import retrofit.RestAdapter;

public final class SoftMaybeEndpointProvider {

	private SoftMaybeEndpointProvider() {}
	
	public static SoftMaybeEndpoint getEndpoint() {
		RestAdapter restAdapter = new RestAdapter.Builder()
		  .setEndpoint("http://www.softmaybe.com")
		  .build();
		
		return restAdapter.create(SoftMaybeEndpoint.class);
	}
}
