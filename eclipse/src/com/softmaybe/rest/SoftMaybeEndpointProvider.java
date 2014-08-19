package com.softmaybe.rest;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class SoftMaybeEndpointProvider {
	
	private static SoftMaybeEndpoint endpoint;

	private SoftMaybeEndpointProvider() {}
	
	public static synchronized SoftMaybeEndpoint get() {
		if (endpoint == null) {
			Gson converter = new GsonBuilder()
					.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
					.registerTypeAdapter(ApiResult.class, ApiResult.getDeserializer())
					.create();
			
			
			RestAdapter restAdapter = new RestAdapter.Builder()
			  .setEndpoint("http://www.softmaybe.com")
			  .setConverter(new GsonConverter(converter))
			  .build();
			endpoint = restAdapter.create(SoftMaybeEndpoint.class);
		}
		return endpoint;
	}
}
