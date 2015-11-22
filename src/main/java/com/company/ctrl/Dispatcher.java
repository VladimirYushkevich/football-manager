package com.company.ctrl;

import com.company.model.event.ActionEvent;

/**
 * Interface for dispatching appropriate action.
 * 
 * @author vladimir.yushkevich
 *
 */
public interface Dispatcher {

	/**
	 * Dispatch action.
	 * 
	 * @param ae
	 *            Action event object
	 */
	void execute(ActionEvent ae);

}