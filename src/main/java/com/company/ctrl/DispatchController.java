package com.company.ctrl;

import java.util.HashMap;
import java.util.Map;

import com.company.ctrl.impl.MatchDispatcher;
import com.company.ctrl.impl.MenuDispatcher;
import com.company.ctrl.impl.PlayerDispatcher;
import com.company.domain.ConsoleNode;
import com.company.enums.DispatchAction;
import com.company.model.Model;
import com.company.model.event.ActionEvent;
import com.company.model.impl.ConsoleMenuModel;
import com.company.model.impl.MatchDayModel;
import com.company.view.ConsoleView;
import com.company.view.MatchDayView;
import com.company.view.View;

/**
 * The implementation of facade controller. Listers event and delegates
 * execution to appropriate controller. Knows about all view and models in the
 * system. Registers model's listeners.
 * 
 * @author uyushkevich
 *
 */
public class DispatchController implements Controller {

	private static volatile DispatchController instance;

	private Model consoleMenuModel;
	private Model matchDayModel;

	private View consoleView;
	private View matchDayView;

	private final Map<DispatchAction, Dispatcher> dispatchers;

	private DispatchController() {
		this.consoleMenuModel = new ConsoleMenuModel();
		this.matchDayModel = new MatchDayModel();

		this.consoleView = new ConsoleView();
		this.matchDayView = new MatchDayView();

		dispatchers = new HashMap<>();
		dispatchers.put(DispatchAction.MENU, new MenuDispatcher(consoleMenuModel, consoleView));
		dispatchers.put(DispatchAction.PLAYER, new PlayerDispatcher(consoleMenuModel, consoleView));
		dispatchers.put(DispatchAction.MATCH, new MatchDispatcher(matchDayModel, matchDayView));
	}

	public static DispatchController getInstance() {
		DispatchController localInstance = instance;
		if (localInstance == null) {
			synchronized (DispatchController.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new DispatchController();
				}
			}
		}
		return localInstance;
	}

	public void start() {
		matchDayModel.addEventListener(l -> {
			matchDayView.update(l);
		});

		consoleMenuModel.addEventListener(l -> {
			consoleView.update(l);
		});

		dispatchers.get(DispatchAction.MENU)
				.execute(new ActionEvent(((ConsoleMenuModel) consoleMenuModel).getStartMenuConsoleMenuNode()));

		consoleView.startFireEvents(this);
	}

	@Override
	public void performAction(ActionEvent ae) {
		ConsoleNode consoleNode = (ConsoleNode) ae.getSource();

		dispatchers.get(consoleNode.getDispatchAction()).execute(ae);
	}

}