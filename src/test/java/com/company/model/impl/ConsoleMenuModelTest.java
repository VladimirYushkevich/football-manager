package com.company.model.impl;

import com.company.enums.DispatchAction;
import com.company.enums.MenuAction;
import com.company.enums.Position;
import com.company.model.event.ActionEvent;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;

import static org.junit.Assert.assertEquals;

public class ConsoleMenuModelTest {

	@Spy
	private ConsoleMenuModel consoleMenuModel = new ConsoleMenuModel();

	@Before
	public void before() {
		consoleMenuModel.reset();
	}

	@Test
	public void shouldUpdateModelWhenInputIsCorrect() {
		arrangeAndAssertStartMenu(MenuAction.MAIN_MENU);

		arrangeAndAssertMenuSelect(1, String.valueOf(MenuAction.START_GAME),
				DispatchAction.MENU);
		arrangeAndAssertMenuSelect(6, String.valueOf(MenuAction.RETURN),
				DispatchAction.MENU);

		arrangeAndAssertMenuSelect(1, String.valueOf(MenuAction.START_GAME),
				DispatchAction.MENU);

		int itemsBeforeTeamSelection = consoleMenuModel.getCurrentNode()
				.getChilds().size();
		arrangeAndAssertMenuSelect(1, String.valueOf(MenuAction.TEAM_CHOICE),
				DispatchAction.MENU);
		arrangeAndAssertMenuSelect(7, String.valueOf(MenuAction.RETURN),
				DispatchAction.MENU);
		assertEquals(itemsBeforeTeamSelection, consoleMenuModel
				.getCurrentNode().getChilds().size());

		int itemsBeforePositionSelection = consoleMenuModel.getCurrentNode()
				.getChilds().size();
		arrangeAndAssertMenuSelect(2,
				String.valueOf(MenuAction.POSITION_CHOICE), DispatchAction.MENU);
		arrangeAndAssertMenuSelect(5, String.valueOf(MenuAction.RETURN),
				DispatchAction.MENU);
		assertEquals(itemsBeforePositionSelection, consoleMenuModel
				.getCurrentNode().getChilds().size());

		arrangeAndAssertMenuSelect(3, String.valueOf(MenuAction.SHOW_PROFILE),
				DispatchAction.PLAYER);
		int itemsBeforeShowProfileSelection = consoleMenuModel.getCurrentNode()
				.getChilds().size();
		arrangeAndAssertMenuSelect(3, String.valueOf(MenuAction.SHOW_PROFILE),
				DispatchAction.PLAYER);
		assertEquals(itemsBeforeShowProfileSelection, consoleMenuModel
				.getCurrentNode().getChilds().size());
		arrangeAndAssertMenuSelect(6, String.valueOf(MenuAction.RETURN),
				DispatchAction.MENU);

		arrangeAndAssertMenuSelect(1, String.valueOf(MenuAction.START_GAME),
				DispatchAction.MENU);
		arrangeAndAssertMenuSelect(4, String.valueOf(MenuAction.PLAY_MATCH),
				DispatchAction.MATCH);
		int itemsBeforePlaySelection = consoleMenuModel.getCurrentNode()
				.getChilds().size();
		arrangeAndAssertMenuSelect(4, String.valueOf(MenuAction.PLAY_MATCH),
				DispatchAction.MATCH);
		assertEquals(itemsBeforePlaySelection, consoleMenuModel
				.getCurrentNode().getChilds().size());
		arrangeAndAssertMenuSelect(6, String.valueOf(MenuAction.RETURN),
				DispatchAction.MENU);

		arrangeAndAssertMenuSelect(1, String.valueOf(MenuAction.START_GAME),
				DispatchAction.MENU);
		arrangeAndAssertMenuSelect(5, String.valueOf(MenuAction.SHOW_TABLE),
				DispatchAction.MATCH);
		int itemsBeforeShowTableSelection = consoleMenuModel.getCurrentNode()
				.getChilds().size();
		arrangeAndAssertMenuSelect(5, String.valueOf(MenuAction.SHOW_TABLE),
				DispatchAction.MATCH);
		assertEquals(itemsBeforeShowTableSelection, consoleMenuModel
				.getCurrentNode().getChilds().size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenNotValidItemIsChosen() {
		arrangeAndAssertStartMenu(MenuAction.MAIN_MENU);

		arrangeAndAssertMenuSelect(2, String.valueOf(MenuAction.START_GAME),
				DispatchAction.MENU);
	}

	@Test
	public void shouldUpdateModelWhenPositionIsSelected() {
		arrangeAndAssertStartMenu(MenuAction.MAIN_MENU);

		arrangeAndAssertMenuSelect(1, String.valueOf(MenuAction.START_GAME),
				DispatchAction.MENU);

		arrangeAndAssertMenuSelect(2,
				String.valueOf(MenuAction.POSITION_CHOICE), DispatchAction.MENU);
		consoleMenuModel
				.getCurrentNode()
				.getChilds()
				.forEach(
						(k, v) -> {
							if (v.getDispatchAction() == DispatchAction.PLAYER) {
								arrangeAndAssertMenuSelect(v.getItem(), String
										.valueOf(Position.findByValue(v
												.getValue())),
										DispatchAction.PLAYER);
								arrangeAndAssertMenuSelect(2, String
										.valueOf(MenuAction.POSITION_CHOICE),
										DispatchAction.MENU);
							}
						});

	}

	@Test
	public void shouldUpdateModelWhenPositionTeamIsSelected() {
		arrangeAndAssertStartMenu(MenuAction.MAIN_MENU);

		arrangeAndAssertMenuSelect(1, String.valueOf(MenuAction.START_GAME),
				DispatchAction.MENU);

		arrangeAndAssertMenuSelect(1, String.valueOf(MenuAction.TEAM_CHOICE),
				DispatchAction.MENU);
		consoleMenuModel
				.getCurrentNode()
				.getChilds()
				.forEach(
						(k, v) -> {
							if (v.getDispatchAction() == DispatchAction.TEAM) {
								arrangeAndAssertMenuSelect(v.getItem(),
										v.getValue(), DispatchAction.TEAM);
								arrangeAndAssertMenuSelect(1,
										String.valueOf(MenuAction.TEAM_CHOICE),
										DispatchAction.MENU);
							}
						});

	}

	private void arrangeAndAssertStartMenu(MenuAction menu) {
		consoleMenuModel.notifyListeners(new ActionEvent(consoleMenuModel
				.getStartMenuConsoleMenuNode()));
		assertEquals(DispatchAction.MENU, consoleMenuModel.getCurrentNode()
				.getDispatchAction());
		assertEquals(String.valueOf(menu), consoleMenuModel.getCurrentNode()
				.getValue());
	}

	private void arrangeAndAssertMenuSelect(int menuItem, String menu,
			DispatchAction commandAction) {
		consoleMenuModel.notifyListeners(new ActionEvent(consoleMenuModel
				.getCurrentNode().getChilds().get(menuItem)));
		assertEquals(commandAction, consoleMenuModel.getCurrentNode()
				.getDispatchAction());
		assertEquals(menu, consoleMenuModel.getCurrentNode().getValue());
	}

}