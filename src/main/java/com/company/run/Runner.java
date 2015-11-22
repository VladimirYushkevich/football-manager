package com.company.run;

import com.company.ctrl.DispatchController;

/**
 * Application launcher.
 *
 * @author vladimir.yushkevich
 *
 */
public class Runner {

	public static void main(String[] args) throws Exception {

		DispatchController.getInstance().start();

	}
}