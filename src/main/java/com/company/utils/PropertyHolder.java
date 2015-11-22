package com.company.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Utility class for extraction application properties.
 * 
 * @author vladimir.yushkevich
 *
 */
public class PropertyHolder {

	private Map<String, String> properties;

	private PropertyHolder(String bundle) {
		properties = new HashMap<>();
		initProperites(bundle);
	}

	public static PropertyHolder getInstance(String bundle) {
		return new PropertyHolder(bundle);
	}

	public Map<String, String> getProperties() {
		return properties;
	}

	private void initProperites(String bundle) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle(bundle);
		Enumeration<String> keys = resourceBundle.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = resourceBundle.getString(key);
			properties.put(key, value);
		}
	}

}
