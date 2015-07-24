package com.company.ctrl;

import com.company.model.event.ActionEvent;

/**
 * Interface for listening any action from View in MVC model.
 * 
 * @author uyushkevich
 *
 */
public interface Controller {

	/**
	 * Handle action event.
	 * 
	 * @param ae
	 *            Action event object
	 */
	void performAction(ActionEvent ae);
}