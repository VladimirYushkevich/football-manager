package com.company.enums;

/**
 * Menu action enums.
 * 
 * @author uyushkevich
 *
 */
public enum MenuAction {

	POSITION_CHOICE("Select position"), TEAM_CHOICE("Select team"), START_GAME("Start game"), EXIT_GAME(
			"Exit"), MAIN_MENU("Main menu"), RETURN("Return from current menu"), SHOW_PROFILE(
					"Show profile"), PLAY_MATCH("Play game"), SHOW_TABLE("Show table");

	private String value;

	private MenuAction(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static MenuAction findByValue(String value) {
		if (null == value) {
			return null;
		}

		for (MenuAction menu : values()) {
			if (menu.getValue().equals(value)) {
				return menu;
			}
		}

		return null;
	}

	@Override
	public String toString() {
		return value;
	}
}
