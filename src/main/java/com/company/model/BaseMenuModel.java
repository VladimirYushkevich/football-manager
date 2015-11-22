package com.company.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.company.domain.ConsoleNode;
import com.company.model.event.ActionEvent;
import com.company.model.event.ActionListener;

/**
 * Base realization of Mode.
 * 
 * @author vladimir.yushkevich
 *
 */
public abstract class BaseMenuModel implements Model {

	private List<ActionListener> consoleListeners = new ArrayList<>();

	protected ConsoleNode currentNode;

	@Override
	public void addEventListener(ActionListener l) {
		consoleListeners.add(l);
	}

	@Override
	public void removeEventListener(ActionListener l) {
		consoleListeners.remove(l);
	}

	@Override
	public void notifyListeners(ActionEvent ae) {
		Optional<ConsoleNode> consoleNode = Optional
				.ofNullable((ConsoleNode) ae.getSource());
		if (!consoleNode.isPresent()) {
			throw new IllegalArgumentException("Menu item is not found.");
		}
		currentNode = currentNode.getChilds().get(consoleNode.get().getItem());
		consoleListeners.forEach(l -> l.actionPerformed(new ActionEvent(
				currentNode)));
	}

	public ConsoleNode getCurrentNode() {
		return currentNode;
	}

}