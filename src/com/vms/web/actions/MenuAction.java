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
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.vms.db.services.MenuService;
import com.vms.db.services.RootMenuService;
import com.vms.db.models.Account;
import com.vms.db.models.Menu;
import com.vms.db.models.Rootmenu;
import com.vms.utils.Constances;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class MenuAction implements Preparable{
	
	private MenuService menuService;
	private RootMenuService rootMenuService;
	private List<Rootmenu> lstRootMenu;
	private Menu menu;
	private InputStream inputStream;
	private List<Menu> lstMenu;
	private LinkedHashMap<String, Object> jsonData; //Tra ve du lieu mang Json (ajax)
	
	public LinkedHashMap<String, Object> getJsonData() {
	
		return jsonData;
	}

	public void setJsonData(LinkedHashMap<String, Object> jsonData) {
	
		this.jsonData = jsonData;
	}

	public List<Menu> getLstMenu() {
	
		return lstMenu;
	}

	public void setLstMenu(List<Menu> lstMenu) {
	
		this.lstMenu = lstMenu;
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

	public Menu getMenu() {
	
		return menu;
	}

	public void setMenu(Menu menu) {
	
		this.menu = menu;
	}

	public List<Rootmenu> getLstRootMenu() {
	
		return lstRootMenu;
	}

	public void setLstRootMenu(List<Rootmenu> lstRootMenu) {
	
		this.lstRootMenu = lstRootMenu;
	}

	public MenuService getMenuService() {
	
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
	
		this.menuService = menuService;
	}
	
	public RootMenuService getRootMenuService() {
		
		return rootMenuService;
	}

	public void setRootMenuService(RootMenuService rootMenuService) {
	
		this.rootMenuService = rootMenuService;
	}
	@Override
	public void prepare() throws Exception {
		// FIXME prepare
		
	}
	
	public MenuAction(MenuService _menuService, RootMenuService _rootMenuService) {
		menuService = _menuService;
		rootMenuService=_rootMenuService;
	}
	public String execute() { //Hien thi man hinh quan ly user
		lstRootMenu=rootMenuService.getAll();
		return Action.SUCCESS;
	}
	public String ajLoadMenu(){
		System.out.println("***ajLoadMenu***");
		HttpServletRequest request = ServletActionContext.getRequest();
		//Tao chuoi dieu kien where
		String strWhere = " 1=1";
		Integer iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		Integer iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
		String sSearch = request.getParameter("sSearch").trim();
		System.out.println("sSearch="+sSearch);
		try {
			JSONArray arrayJson = (JSONArray) new JSONObject(sSearch).get("array");
			for(int i=0;i<arrayJson.length();i++) {
				String name = arrayJson.getJSONObject(i).getString("name");
				String value = arrayJson.getJSONObject(i).getString("value");
				if(value.isEmpty()==false) {
					if(name.equals("menu.namemenu"))
						strWhere+="and UPPER(namemenu) like '%"+value.toUpperCase()+"%'";
					if(name.equals("menu.action"))
						strWhere+=" and UPPER(action) like '%"+value.toUpperCase()+"%'";
					if(name.equals("menu.rootmenu.id"))
						strWhere+=" and rootmenu.id="+value;
					if(name.equals("menu.active"))
						strWhere+=" and active="+value;
				}
			}
			
		} catch (JSONException e) {
			System.out.println("Parse JSON Error : "+e.getMessage());
		}
		
		List<Menu> lstMenu = menuService.getMenus(strWhere, iDisplayStart, iDisplayLength+1);
		jsonData = new LinkedHashMap<String, Object>();
		List items = new ArrayList();
		for(int i=0 ; i<lstMenu.size() && i<iDisplayLength ; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("stt", i+1);
			map.put("id", lstMenu.get(i).getId());
			map.put("namemenu", lstMenu.get(i).getNamemenu());
			map.put("action", lstMenu.get(i).getAction());
			//map.put("idrootmenu", lstMenu.get(i).getRootmenu().getId());
			//map.put("namerootmenu", lstMenu.get(i).getRootmenu().getName());
			map.put("active", lstMenu.get(i).getActive());
			items.add(map);
		}
		jsonData.put("sEcho", Integer.parseInt(request.getParameter("sEcho")));
		jsonData.put("iTotalRecords", lstMenu.size());
		jsonData.put("iTotalDisplayRecords", lstMenu.size());
		jsonData.put("aaData", items);
		return Action.SUCCESS;
	}
	public String ajSaveMenu(){
		if(menu==null){
			setInputStream(Constances.ERROR);
		}
		else{
			String strError="";
			if(menu.getNamemenu().trim().isEmpty())
				strError += "Namemenu is empty <br/>";
			if(menu.getAction().trim().isEmpty())
				strError += "Action is empty <br/>";
			/*if(menu.getRootmenu().getId()==0)
				strError += "RootMenu is empty <br/>";*/
			System.out.println("strError="+strError);
			if(strError.isEmpty()){
				setInputStream(menuService.saveMenu(menu));
			}
			else{
				setInputStream(strError);
			}
		}
		return Action.SUCCESS;
	}
}
