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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import com.vms.db.models.UserMenu;
import com.vms.db.services.MenuService;
import com.vms.db.services.TestService;
import com.vms.db.services.UserService;
import com.vms.web.models.AccountExt;
import com.vms.web.models.Phongban;

public class TestAction implements Preparable {
	private TestService testService;
	private UserService userService;
	private MenuService menuService;
	private LinkedHashMap<String, Object> jsonData;
	@Override
	public void prepare() throws Exception {
		// FIXME prepare
		
	}
	
	public TestAction(TestService service1, UserService service2, MenuService service3) {
		testService = service1;
		userService = service2;
		menuService = service3;
	}
	
	public String test() {
		//testService.insertTest("TEST");
		System.out.println("Hello");
		/*HttpServletRequest request = ServletActionContext.getRequest();
		String msnhanvien = request.getParameter("msnhanvien");
		System.out.println("msnhanvien = "+msnhanvien);
		List accounts = userService.findNhanvien(null, Integer.valueOf(msnhanvien),0,10);*/
		List<UserMenu> usermenus = new ArrayList<UserMenu>();
		//UserMenu[] usermenus = new UserMenu[];
		UserMenu m = new UserMenu();
		m.setAccountid(1);
		m.setMenuid(2);
		usermenus.add(m);
		menuService.saveUserMenu((UserMenu[]) usermenus.toArray());
		List accounts = new ArrayList();
		jsonData = new LinkedHashMap<String, Object>();
		jsonData.put("data", accounts);
		
		return Action.SUCCESS;
	}

	public LinkedHashMap<String, Object> getJsonData() {
	
		return jsonData;
	}

	public void setJsonData(LinkedHashMap<String, Object> jsonData) {
	
		this.jsonData = jsonData;
	}
	
}
