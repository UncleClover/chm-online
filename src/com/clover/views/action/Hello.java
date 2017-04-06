package com.clover.views.action;

import com.opensymphony.xwork2.ActionSupport;

public class Hello extends ActionSupport {
	private String message;

	public String getMessage() {
		return message;
	}

	public String execute() {
		message = "Hello World!";
		return SUCCESS;
	}
}
