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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import com.vms.db.models.Account;
import com.vms.db.models.Menu;
import com.vms.db.models.Vmsgroup;
import com.vms.db.services.BophanService;
import com.vms.db.services.MenuService;
import com.vms.db.services.NhanvienService;
import com.vms.db.services.PhongbanService;
import com.vms.db.services.TestService;
import com.vms.db.services.UserService;
import com.vms.utils.Constances;
import com.vms.utils.NumberUtil;
import com.vms.utils.StringUtil;
import com.vms.web.models.AccountExt;
import com.vms.web.models.Bophan;
import com.vms.web.models.Nhanvien;
import com.vms.web.models.Phongban;

public class SearchAction implements Preparable {
	private PhongbanService phongbanService;
	private BophanService bophanService;
	private NhanvienService nhanvienService;
	private MenuService menuService;
	private List<Phongban> phongbans;
	private LinkedHashMap<String, Object> jsonData; //Tra ve du lieu mang Json (ajax)
	private InputStream inputStream; //Tra ve du lieu kieu chuoi (ajax)
	private Long[] ids;
	@Override
	public void prepare() throws Exception {
		// FIXME prepare
		
	}
	
	public SearchAction( PhongbanService service1,BophanService service2, NhanvienService service3, MenuService service4) {
		phongbanService = service1;
		bophanService = service2;
		nhanvienService = service3;
		menuService = service4;
	}
	
	public String execute() { //Hien thi man hinh quan ly user
		//groups = userService.getAllGroup();
		phongbans = phongbanService.getAll();
		HttpServletRequest request = ServletActionContext.getRequest();
		String type = request.getParameter("type");
		System.out.println("type="+type);
		if(type!=null && type.equals("2"))
			return "search_user_2";
		return "search_user_1";
	}
	public String ajListBoPhan() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String msphongban = request.getParameter("msphongban");
		List<Bophan> bophans = bophanService.getAll(Integer.valueOf(msphongban));
		jsonData = new LinkedHashMap<String, Object>();
		jsonData.put("data", bophans);
		return Action.SUCCESS;
	}
	
	public String ajListNhanvienByBophan() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String msbophan = request.getParameter("msbophan");
		List<Nhanvien> nhanviens = nhanvienService.getByBophan(Integer.valueOf(msbophan));
		jsonData = new LinkedHashMap<String, Object>();
		jsonData.put("data", nhanviens);
		return Action.SUCCESS;
	}
	public String ajSearchMenu() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String rootmenu = request.getParameter("root_menu");
		jsonData = new LinkedHashMap<String, Object>();
		List<Menu> menus = menuService.loadByRoot(NumberUtil.parseInt(rootmenu));
		jsonData.put("data", menus);
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

	public List<Phongban> getPhongbans() {
	
		return phongbans;
	}

	public void setPhongbans(List<Phongban> phongbans) {
	
		this.phongbans = phongbans;
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
