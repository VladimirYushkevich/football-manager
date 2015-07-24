package com.company.view;

import java.util.Optional;
import java.util.Scanner;

import com.company.ctrl.Controller;
import com.company.domain.ConsoleNode;
import com.company.model.event.ActionEvent;

/**
 * Console view.
 * 
 * @author uyushkevich
 *
 */
public class ConsoleView implements View {

	private ConsoleNode consoleNode;

	@Override
	public void update(ActionEvent ae) {
		consoleNode = (ConsoleNode) ae.getSource();
		System.out.println("------------------ Select option: -------------");
		consoleNode.getChilds().forEach((k, v) -> System.out.println(String.format("%s. %s", k, v.getValue())));
		System.out.println("-----------------------------------------------");
	}

	@Override
	public void showData(String data) {
		System.out.println("***********************************************");
		System.out.println(data);
		System.out.println("***********************************************");
	}

	@SuppressWarnings("resource")
	@Override
	public void startFireEvents(Controller controller) {
		int menuItem = 0;

		while (true) {
			try {
				menuItem = Integer.valueOf(new Scanner(System.in).nextLine());
			} catch (NumberFormatException exception) {
				System.out.println("enter number");
				continue;
			}

			Optional<ConsoleNode> selctedConsoleNode = Optional.ofNullable(consoleNode.getChilds().get(menuItem));
			if (!selctedConsoleNode.isPresent()) {
				System.out.println("enter correct menu item");
				continue;
			}

			controller.performAction(new ActionEvent(selctedConsoleNode.get()));
		}
	}

}