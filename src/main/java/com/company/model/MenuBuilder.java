package com.company.model;

import com.company.domain.ConsoleNode;
import com.company.enums.ControllerAction;
import com.company.enums.DispatchAction;
import com.company.enums.MenuAction;
import com.company.enums.Position;
import com.company.service.TeamService;
import com.company.service.impl.TeamServiceImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.company.utils.MapInitilizer.entriesToMap;
import static com.company.utils.MapInitilizer.entry;

/**
 * Utility class for building any menu tree.
 * 
 * @author vladimir.yushkevich
 *
 */
public class MenuBuilder {

	private static final TeamService TEAM_SERVICE = new TeamServiceImpl();

	private MenuBuilder() {
	}

	public static void buildSelectTeamMenu(int menuItem, ConsoleNode parentNode) {

		ConsoleNode currentNode = buildDefaultNode(parentNode, menuItem, String.valueOf(MenuAction.TEAM_CHOICE), 7);

		TEAM_SERVICE.getTeams().stream().forEach(t -> {
			Map<Integer, ConsoleNode> currentChilds = new HashMap<>(currentNode.getChilds());
			int size = currentNode.getChilds().size() - 1;
			ConsoleNode node = new ConsoleNode(DispatchAction.PLAYER, size, t.getName(), parentNode,
					ControllerAction.UPDATE);
			node.setChilds(parentNode.getChilds());
			currentChilds.put(size, node);
			currentNode.setChilds(currentChilds);
		});
	}

	public static void buildSelectPositionMenu(int menuItem, ConsoleNode parentNode) {

		ConsoleNode currentNode = buildDefaultNode(parentNode, menuItem, String.valueOf(MenuAction.POSITION_CHOICE), 5);

		Stream.of(Position.values()).forEach(p -> {
			Map<Integer, ConsoleNode> currentChilds = new HashMap<>(currentNode.getChilds());
			int size = currentNode.getChilds().size() - 1;
			ConsoleNode node = new ConsoleNode(DispatchAction.PLAYER, size, String.valueOf(p), parentNode,
					ControllerAction.UPDATE);
			node.setChilds(parentNode.getChilds());
			currentChilds.put(size, node);
			currentNode.setChilds(currentChilds);
		});

		// update previously initialized menu
		parentNode.getChilds().get(1).getChilds().forEach((k, v) -> {
			if (v.getControllerAction() != ControllerAction.EXIT)
				v.setChilds(parentNode.getChilds());
		});
	}

	public static ConsoleNode buildShowNode(int menuItem, ConsoleNode parentNode, DispatchAction dispatchAction,
			MenuAction menuAction, ControllerAction controllerAction) {

		ConsoleNode currentNode = new ConsoleNode(dispatchAction, menuItem, String.valueOf(menuAction), parentNode,
				controllerAction);

		parentNode.getChilds().put(menuItem, currentNode);

		currentNode.setChilds(new HashMap<>(parentNode.getChilds()));

		return currentNode;
	}

	public static ConsoleNode buildSelectMenu(int menuItem, ConsoleNode parentNode, DispatchAction dispatchAction,
			MenuAction menuAction, ControllerAction controllerAction) {

		ConsoleNode currentNode = new ConsoleNode(dispatchAction, menuItem, String.valueOf(menuAction), parentNode,
				controllerAction);
		parentNode.setChilds(
				Collections.unmodifiableMap(Stream.of(entry(menuItem, currentNode)).collect(entriesToMap())));

		return currentNode;
	}

	public static ConsoleNode buildDefaultMenu(ConsoleNode parentNode, int menuItem, String value, int returnItem) {

		ConsoleNode returnNode = new ConsoleNode(DispatchAction.MENU, returnItem, String.valueOf(MenuAction.RETURN),
				parentNode, ControllerAction.SELECT);

		ConsoleNode exitNode = new ConsoleNode(DispatchAction.MENU, 0, String.valueOf(MenuAction.EXIT_GAME), null,
				ControllerAction.EXIT);
		exitNode.setChilds(new HashMap<>());

		ConsoleNode currentNode = new ConsoleNode(DispatchAction.MENU, menuItem, value, parentNode,
				ControllerAction.SELECT);

		parentNode.setChilds(Collections
				.unmodifiableMap(Stream.of(entry(menuItem, currentNode), entry(0, exitNode)).collect(entriesToMap())));
		returnNode.setChilds(new HashMap<>(parentNode.getChilds()));

		currentNode.setChilds(Collections
				.unmodifiableMap(Stream.of(entry(returnItem, returnNode), entry(0, exitNode)).collect(entriesToMap())));

		return currentNode;
	}

	private static ConsoleNode buildDefaultNode(ConsoleNode parentNode, int menuItem, String value, int returnItem) {

		ConsoleNode returnNode = new ConsoleNode(DispatchAction.MENU, returnItem, String.valueOf(MenuAction.RETURN),
				parentNode, ControllerAction.SELECT);
		ConsoleNode exitNode = new ConsoleNode(DispatchAction.MENU, 0, String.valueOf(MenuAction.EXIT_GAME), null,
				ControllerAction.EXIT);
		exitNode.setChilds(new HashMap<>());

		ConsoleNode currentNode = new ConsoleNode(DispatchAction.MENU, menuItem, value, parentNode,
				ControllerAction.SELECT);

		currentNode.setChilds(Collections
				.unmodifiableMap(Stream.of(entry(returnItem, returnNode), entry(0, exitNode)).collect(entriesToMap())));

		Map<Integer, ConsoleNode> parentChilds = new HashMap<>(parentNode.getChilds());
		parentChilds.put(menuItem, currentNode);
		parentNode.setChilds(parentChilds);
		returnNode.setChilds(parentNode.getChilds());

		return currentNode;
	}

}