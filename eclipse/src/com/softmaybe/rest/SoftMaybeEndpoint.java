package com.softmaybe.rest;

import java.util.Map;

import retrofit.http.POST;
import retrofit.http.Query;

public interface SoftMaybeEndpoint {

	@POST("/api")
	Map<String, String> api(@Query("email") String email, @Query("event") String event);
	
}
