package com.softmaybe.rest;

public final class ApiRequest {

	private final String email;
	private final String event;
	
	public ApiRequest(String email, String event) {
		this.email = email;
		this.event = event;
	}

	public String getEmail() {
		return email;
	}

	public String getEvent() {
		return event;
	}

	@Override
	public String toString() {
		return "ApiRequest [email=" + email + ", event=" + event + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((event == null) ? 0 : event.hashCode());
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
		ApiRequest other = (ApiRequest) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		return true;
	}
	
}
