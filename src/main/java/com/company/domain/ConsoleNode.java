package com.company.domain;

import com.company.enums.ControllerAction;
import com.company.enums.DispatchAction;

import java.util.Map;

/**
 * Class for storing menu items in the some kind tree.
 * 
 * @author vladimir.yushkevich
 *
 */
public class ConsoleNode {

	private DispatchAction dispatchAction;
	private Integer item;
	private String value;
	private ConsoleNode parent;
	private Map<Integer, ConsoleNode> childs;
	private ControllerAction controllerAction;

	public ConsoleNode() {
	}

	public ConsoleNode(DispatchAction dispatchAction, Integer item, String value, ConsoleNode parent,
			ControllerAction controllerAction) {
		this.dispatchAction = dispatchAction;
		this.item = item;
		this.value = value;
		this.parent = parent;
		this.controllerAction = controllerAction;
	}

	public DispatchAction getDispatchAction() {
		return dispatchAction;
	}

	public void setDispatchAction(DispatchAction dispatchAction) {
		this.dispatchAction = dispatchAction;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ConsoleNode getParent() {
		return parent;
	}

	public void setParent(ConsoleNode parent) {
		this.parent = parent;
	}

	public Map<Integer, ConsoleNode> getChilds() {
		return childs;
	}

	public void setChilds(Map<Integer, ConsoleNode> childs) {
		this.childs = childs;
	}

	public ControllerAction getControllerAction() {
		return controllerAction;
	}

	public void setControllerAction(ControllerAction controllerAction) {
		this.controllerAction = controllerAction;
	}

}