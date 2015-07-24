package com.company.view;

import com.company.ctrl.Controller;
import com.company.model.event.ActionEvent;

/**
 * View part of MVC mode. Responsible for event generation (controller
 * notification) and data rendering.
 * 
 * @author uyushkevich
 *
 */
public interface View {

	void update(ActionEvent ae);

	void showData(String data);

	default void startFireEvents(Controller controller) {
	};

}