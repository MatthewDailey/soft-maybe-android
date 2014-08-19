package com.softmaybe.util;

import java.net.MalformedURLException;
import java.net.URL;

import com.google.common.base.Optional;

public final class Urls {

	private Urls() {};
	
	public static boolean isUrl(String urlString) {
		try {
			new URL(urlString);
			return true;
		} catch (MalformedURLException e) {
			return false;
		}
	}
	
	public static Optional<String> findFirstUrl(String... strings) {
		for (String s :  strings) {
			if (isUrl(s)) {
				return Optional.of(s);
			} 
		}
		return Optional.absent();
	}
}
