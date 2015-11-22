package com.company.enums;

/**
 * Position enums.
 * 
 * @author vladimir.yushkevich
 *
 */
public enum Position {

	GK("Goalkeeper"), DF("Defender"), MF("Midfielder"), FW(
			"Forward");

	private String value;

	Position(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static Position findByValue(String value) {
		if (null == value) {
			return null;
		}

		for (Position position : values()) {
			if (position.getValue().equals(value)) {
				return position;
			}
		}

		return null;
	}

	@Override
	public String toString() {
		return value;
	}
	
}
