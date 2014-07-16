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
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import com.vms.db.models.Account;
import com.vms.db.services.UserService;
import com.vms.utils.Constances;

public class SettingsAction implements Preparable {
	private UserService userService;
	private LinkedHashMap<String, Object> jsonData; //Tra ve du lieu mang Json (ajax)
	private InputStream inputStream; //Tra ve du lieu kieu chuoi (ajax)
	private Long[] ids;
	@Override
	public void prepare() throws Exception {
		// FIXME prepare
		
	}
	
	public SettingsAction( UserService service1) {
		userService = service1;
	}
	
	public String execute() { //Hien thi man hinh quan ly user
		//groups = userService.getAllGroup();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			if(session.getAttribute(Constances.SESS_USERLOGIN)==null) throw new Exception();
			return Action.SUCCESS;
		} catch (Exception e) {
			setInputStream(Constances.END_SESSION);
			return Constances.END_SESSION;
		}
	}
	public String ajChangePassword() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Account account = (Account) session.getAttribute(Constances.SESS_USERLOGIN);
			if(account == null) {
				setInputStream(Constances.END_SESSION);
				return Constances.END_SESSION;
			}
			String password = request.getParameter("password");
			System.out.println("account.id =" + account.getId());
			System.out.println("newpas =" + password);
			userService.updatePassword(account.getId(), password);
			setInputStream("OK");
		}  catch (Exception e) {
			setInputStream(Constances.END_SESSION);
			return Constances.END_SESSION;
		}
		return Action.SUCCESS;
	}
	
	
	public void setInputStream(String str) {
		try {
			this.inputStream =  new ByteArrayInputStream( str.getBytes("UTF-8") );
		} catch (UnsupportedEncodingException e) {			
			System.out.println("ERROR :" + e.getMessage());
		}
	}

	public InputStream getInputStream() {
	
		return inputStream;
	}

	public LinkedHashMap<String, Object> getJsonData() {
	
		return jsonData;
	}

	public void setJsonData(LinkedHashMap<String, Object> jsonData) {
	
		this.jsonData = jsonData;
	}

	public Long[] getIds() {
	
		return ids;
	}

	public void setIds(Long[] ids) {
	
		this.ids = ids;
	}

	public void setInputStream(InputStream inputStream) {
	
		this.inputStream = inputStream;
	}

	
	
}
