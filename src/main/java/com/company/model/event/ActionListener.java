package com.company.model.event;

/**
 * The listener interface for receiving action events. The class that is
 * interested in processing an action event implements this interface, and the
 * object created with that class is registered with a component, using the
 * component's <code>addActionListener</code> method. When the action event
 * occurs, that object's <code>actionPerformed</code> method is invoked.
 *
 *  * @author vladimir.yushkevich
 */
public interface ActionListener {

	/**
	 * Invoked when an action occurs.
	 * 
	 * @param ae ActionEvent
	 */
	void actionPerformed(ActionEvent ae);

}