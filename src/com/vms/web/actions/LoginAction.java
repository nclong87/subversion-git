/*
 * ============== Project : 1080 Information Software ==========================
 * Copyright (c) Lac Viet Cop.
 * 23 Nguyễn Thị Huỳnh (76/23 Nguyễn Văn Trỗi), P.8, Q.Phú Nhuận, Tp.HCM.
 * All rights reserved. 
 * This software is the confidential and proprietary information of Lac Viet Cop. 
 * ("Confidential Information"). You shall not disclose such Confidential Information 
 * and shall use it only in accordance with the terms of the license agreement 
 * you entered into with Lac Viet.
 * ===================================================================================
 */
package com.vms.web.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import com.vms.db.models.Account;
import com.vms.db.services.UserService;
import com.vms.utils.Constances;

public class LoginAction implements Preparable {
	private UserService userService;
	private String username;
	private String password;
	private InputStream inputStream;
	private String message;

	@Override
	public void prepare() throws Exception {
		// FIXME prepare
		
	}
	
	public LoginAction(UserService service1) {
		userService = service1;
	}
	
	public String doLogin() {
		if(username.isEmpty()==false) {
			Account account = userService.checkLogin(username, password);
			if(account==null) {
				message = "Login failed, try again!";
				return Action.ERROR;
			}
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			session.setAttribute(Constances.SESS_USERLOGIN, account);
			String sMenu = userService.getMenu(account);
			System.out.println("Menu = "+sMenu);
			session.setAttribute(Constances.SESS_MENU, sMenu);
			
		} else {
			message = "Username not empty!";
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
	
	public String doLogout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.invalidate();
		return Action.SUCCESS;
	}

	/* Getter and Setter */
	
	public String getMessage() {
		
		return message;
	}

	public void setMessage(String message) {
	
		this.message = message;
	}
	public String getUsername() {
		
		return username;
	}

	public void setUsername(String username) {
	
		this.username = username;
	}

	public String getPassword() {
	
		return password;
	}

	public void setPassword(String password) {
	
		this.password = password;
	}

	public InputStream getInputStream() {
	
		return inputStream;
	}

	public void setInputStream(String str) {
	
		try {
			this.inputStream =  new ByteArrayInputStream( str.getBytes("UTF-8") );
		} catch (UnsupportedEncodingException e) {			
			System.out.println("ERROR :" + e.getMessage());
		}
	}
	
}
