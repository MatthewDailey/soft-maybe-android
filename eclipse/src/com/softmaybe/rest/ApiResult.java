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
				
				String message = null;
				JsonElement messageJson = object.get("message");
				if (messageJson != null) {
						message = messageJson.getAsString();
				}
				String email = null;
				JsonElement emailJson =	object.get("email");
				if (emailJson != null) {
					email = emailJson.getAsString();
				}
				String name = null;
				JsonElement nameJson = object.get("name");
				if (nameJson != null) {
					name = nameJson.getAsString();
				}
				
				return new ApiResult(success, message, email, name);
			}
			
		};
	}

	@Override
	public String toString() {
		return "ApiResult [success=" + success + ", message=" + message
				+ ", email=" + email + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (success ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApiResult other = (ApiResult) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (success != other.success)
			return false;
		return true;
	}
	
}
