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

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import com.vms.db.services.MenuService;
import com.vms.db.services.UserService;
import com.vms.db.models.Rootmenu;
import com.vms.db.models.UserMenu;
import com.vms.utils.Constances;
import com.vms.utils.NumberUtil;
import com.vms.web.models.AccountExt;
import com.vms.web.models.ValueLabel;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class PermissionAction implements Preparable{
	
	private MenuService menuService;
	private UserService userService;
	private List<Rootmenu> rootmenus;
	private List<ValueLabel> user_menus;
	private AccountExt accountExt;
	private InputStream inputStream;
	private String[] idmenus;
	private LinkedHashMap<String, Object> jsonData; //Tra ve du lieu mang Json (ajax)
	
	
	@Override
	public void prepare() throws Exception {
		// FIXME prepare
		
	}
	
	public PermissionAction(MenuService _menuService, UserService service2) {
		menuService = _menuService;
		userService = service2;
	}
	public String execute() { 
		HttpServletRequest request = ServletActionContext.getRequest();
		String sId = request.getParameter("id");
		accountExt = userService.findNhanvienById(Long.parseLong(sId));
		rootmenus = menuService.getRootmenus();
		user_menus = menuService.getMenuByUser(Long.valueOf(sId));
		return Action.SUCCESS;
	}
	
	public String saveUserMenu() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			if(session.getAttribute(Constances.SESS_USERLOGIN)==null) {
				setInputStream(Constances.END_SESSION);
				return Constances.END_SESSION;
			}
			String sId = request.getParameter("id");
			if(idmenus != null) {
				UserMenu[] array_usermenu = new UserMenu[idmenus.length];
				int id = NumberUtil.parseInt(sId);
				for(int i=0;i<idmenus.length;i++) {
					UserMenu m = new UserMenu();
					m.setAccountid(id);
					m.setMenuid(NumberUtil.parseInt(idmenus[i]));
					array_usermenu[i] = m;
				}
				menuService.saveUserMenu(array_usermenu);
			} 
			setInputStream("OK");
		} catch (Exception e) {
			setInputStream("ERROR");
		}
		return Action.SUCCESS;
	}

	public List<Rootmenu> getRootmenus() {
	
		return rootmenus;
	}

	public void setRootmenus(List<Rootmenu> rootmenus) {
	
		this.rootmenus = rootmenus;
	}

	public List<ValueLabel> getUser_menus() {
	
		return user_menus;
	}

	public void setUser_menus(List<ValueLabel> user_menus) {
	
		this.user_menus = user_menus;
	}

	public LinkedHashMap<String, Object> getJsonData() {
	
		return jsonData;
	}

	public void setJsonData(LinkedHashMap<String, Object> jsonData) {
	
		this.jsonData = jsonData;
	}

	public AccountExt getAccountExt() {
	
		return accountExt;
	}

	public void setAccountExt(AccountExt accountExt) {
	
		this.accountExt = accountExt;
	}

	public String[] getIdmenus() {
	
		return idmenus;
	}

	public void setIdmenus(String[] idmenus) {
	
		this.idmenus = idmenus;
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
	
}
