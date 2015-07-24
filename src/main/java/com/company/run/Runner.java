package com.company.run;

import com.company.ctrl.DispatchController;

/**
 * Application launcher.
 * 
 * @author uyushkevich
 *
 */
public class Runner {

	public static void main(String[] args) throws Exception {

		DispatchController.getInstance().start();

	}
}