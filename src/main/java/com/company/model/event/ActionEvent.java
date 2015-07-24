package com.company.model.event;

/**
 * Class for transferring data in MVC.
 * 
 * @author uyushkevich
 *
 */
public class ActionEvent {

	private Object source;

	public ActionEvent(Object source) {
		this.source = source;
	}

	public Object getSource() {
		return source;
	}

}