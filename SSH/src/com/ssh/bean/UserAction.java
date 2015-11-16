package com.ssh.bean;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport {

	@Override
	public String execute() throws Exception {
		return "login";
	}
}
