package com.company.model;

import com.company.model.event.ActionEvent;
import com.company.model.event.ActionListener;

/**
 * Interface for model in MVC.
 * 
 * @author uyushkevich
 *
 */
public interface Model {

	/**
	 * Adds an <code>EventListener</code> to the model.
	 *
	 * @param l
	 *            the listener to add
	 */
	void addEventListener(ActionListener l);

	/**
	 * Removes an <code>EventListener</code> from the model.
	 *
	 * @param l
	 *            the listener to remove
	 */
	void removeEventListener(ActionListener l);

	/**
	 * Notifies all listeners that have registered interest for notification on
	 * this event type.
	 *
	 * @param ae
	 *            the <code>ActionEvent</code> to deliver to listeners.
	 */
	void notifyListeners(ActionEvent ae);

}
