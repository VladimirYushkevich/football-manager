package com.company.ctrl.impl;

import com.company.ctrl.Dispatcher;
import com.company.domain.ConsoleNode;
import com.company.model.Model;
import com.company.model.event.ActionEvent;
import com.company.model.impl.ConsoleMenuModel;
import com.company.view.ConsoleView;
import com.company.view.View;

/**
 * Responsible for handling of all menu events.
 * 
 * @author vladimir.yushkevich
 *
 */
public class MenuDispatcher implements Dispatcher {

	private ConsoleMenuModel model;
	private ConsoleView view;

	public MenuDispatcher(Model model, View view) {
		this.model = (ConsoleMenuModel) model;
		this.view = (ConsoleView) view;
	}

	@Override
	public void execute(ActionEvent ae) {
		ConsoleNode consoleNode = (ConsoleNode) ae.getSource();
		switch (consoleNode.getControllerAction()) {
		case SELECT:
			select(ae);
			break;
		case EXIT:
			exit(ae, view);
			break;
		default:
			throw new IllegalArgumentException("Now disparcher's actions have been found in PlayerController");
		}
	}

	private void select(ActionEvent ae) {
		model.notifyListeners(ae);
	}

	private void exit(ActionEvent ae, ConsoleView view) {
		view.showData("Bye");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

}