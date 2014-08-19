package com.softmaybe.rest;

import retrofit.http.POST;
import retrofit.http.Query;

public interface SoftMaybeEndpoint {

	@POST("/api")
	ApiResult api(@Query("email") String email, @Query("event") String event);
	
}
