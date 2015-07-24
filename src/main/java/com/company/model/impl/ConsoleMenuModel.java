package com.company.model.impl;

import com.company.domain.ConsoleNode;
import com.company.enums.ControllerAction;
import com.company.enums.DispatchAction;
import com.company.enums.MenuAction;
import com.company.model.BaseMenuModel;
import com.company.model.MenuBuilder;

/**
 * Particular console model.
 * 
 * @author uyushkevich
 *
 */
public class ConsoleMenuModel extends BaseMenuModel {

	private ConsoleNode startConsoleMenuNode;

	public ConsoleMenuModel() {
		init();
	}

	public void reset() {
		init();
	}

	private void init() {
		currentNode = new ConsoleNode();

		ConsoleNode mainMenuNode = MenuBuilder.buildSelectMenu(-1, currentNode,
				DispatchAction.MENU, MenuAction.MAIN_MENU,
				ControllerAction.SELECT);
		ConsoleNode startGameNode = MenuBuilder.buildDefaultMenu(mainMenuNode,
				1, String.valueOf(MenuAction.START_GAME), 6);

		MenuBuilder.buildSelectTeamMenu(1, startGameNode);
		MenuBuilder.buildSelectPositionMenu(2, startGameNode);
		MenuBuilder.buildShowNode(3, startGameNode, DispatchAction.PLAYER,
				MenuAction.SHOW_PROFILE, ControllerAction.GET);
		MenuBuilder.buildShowNode(4, startGameNode, DispatchAction.MATCH,
				MenuAction.PLAY_MATCH, ControllerAction.UPDATE);
		MenuBuilder.buildShowNode(5, startGameNode, DispatchAction.MATCH,
				MenuAction.SHOW_TABLE, ControllerAction.GET);

		startConsoleMenuNode = mainMenuNode;
	}

	public ConsoleNode getStartMenuConsoleMenuNode() {
		return startConsoleMenuNode;
	}

}