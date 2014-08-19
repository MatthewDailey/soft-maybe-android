package com.softmaybe.rest;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class ApiResult {

	private final boolean success;
	private final String message;
	private final String email;
	private final String name;
	
	public ApiResult(boolean success, String message, String email, String name) {
		this.success = success;
		this.message = message;
		this.email = email;
		this.name = name;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public static JsonDeserializer<ApiResult> getDeserializer() {
		return new JsonDeserializer<ApiResult> () {

			@Override
			public ApiResult deserialize(JsonElement json, Type typeOf,
					JsonDeserializationContext context) throws JsonParseException {
				final JsonObject object = json.getAsJsonObject();
				
				final JsonElement successJson = object.get("success"); 
				final boolean success = successJson.getAsBoolean();
				
				final String message = object.get("message").getAsString();
				final String email = object.get("email").getAsString();
				final String name = object.get("name").getAsString();
				
				return new ApiResult(success, message, email, name);
			}
			
		};
	}
	
}
