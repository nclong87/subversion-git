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
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.jboss.aspects.concurrent.MutexAspect.TryLockMutex;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import com.vms.db.models.Account;
import com.vms.db.models.Vmsgroup;
import com.vms.db.services.PhongbanService;
import com.vms.db.services.TestService;
import com.vms.db.services.UserService;
import com.vms.utils.Constances;
import com.vms.utils.StringUtil;
import com.vms.web.models.AccountExt;
import com.vms.web.models.Phongban;

public class UserAction implements Preparable {
	private UserService userService;
	private PhongbanService phongbanService;
	private List<Vmsgroup> groups;
	private List<Phongban> phongbans;

	private Account account;
	private LinkedHashMap<String, Object> jsonData; //Tra ve du lieu mang Json (ajax)
	private InputStream inputStream; //Tra ve du lieu kieu chuoi (ajax)
	private Long[] ids;
	@Override
	public void prepare() throws Exception {
		
	}
	public UserAction(UserService service1, PhongbanService service2) {
		userService = service1;
		phongbanService = service2;
	}
	
	public String execute() { //Hien thi man hinh quan ly user
		//groups = userService.getAllGroup();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			if(session.getAttribute(Constances.SESS_USERLOGIN)==null) throw new Exception();
			phongbans = phongbanService.getAll();
			return Action.SUCCESS;
		} catch (Exception e) {
			setInputStream(Constances.END_SESSION);
			return Constances.END_SESSION;
		}
		
	}
	public String ajLoadAccounts() {
		System.out.println("***ajLoadAccounts***");
		HttpServletRequest request = ServletActionContext.getRequest();
		//Tao chuoi dieu kien where
		String strWhere = " 1=1";
		Integer iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		Integer iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
		String sSearch = request.getParameter("sSearch").trim();
		System.out.println("sSearch="+sSearch);
		String username = null;
		int msnhanvien = 0;
		try {
			JSONArray arrayJson = (JSONArray) new JSONObject(sSearch).get("array");
			for(int i=0;i<arrayJson.length();i++) {
				String name = arrayJson.getJSONObject(i).getString("name");
				String value = arrayJson.getJSONObject(i).getString("value");
				if(value.isEmpty()==false) {
					if(name.equals("account.username"))
						username = value;
					if(name.equals("account.msnhanvien"))
						msnhanvien = Integer.parseInt(value);
				}
			}
			
		} catch (JSONException e) {
			System.out.println("Parse JSON Error : "+e.getMessage());
		}
		
		List<AccountExt> lstAccount = userService.findNhanvien(username, msnhanvien, iDisplayStart, iDisplayLength+1);
		jsonData = new LinkedHashMap<String, Object>();
		List<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
		for(int i=0;i<lstAccount.size() && i<iDisplayLength;i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			AccountExt account_ext = lstAccount.get(i);
			map.put("stt", i+1);
			map.put("id", account_ext.getId());
			map.put("username", account_ext.getUsername());
			map.put("msnhanvien", account_ext.getMsnhanvien());
			map.put("honhanvien", account_ext.getHonhanvien());
			map.put("tennhanvien", account_ext.getTennhanvien());
			map.put("active", account_ext.getActive());
			items.add(map);
		}
		jsonData.put("sEcho", Integer.parseInt(request.getParameter("sEcho")));
		jsonData.put("iTotalRecords", lstAccount.size());
		jsonData.put("iTotalDisplayRecords", lstAccount.size());
		jsonData.put("aaData", items);
		return Action.SUCCESS;
	}
	public String ajSaveAccount() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			if(session.getAttribute(Constances.SESS_USERLOGIN)==null) {
				setInputStream(Constances.END_SESSION);
				return Constances.END_SESSION;
			}
			if(account==null) {
				setInputStream("ERROR");
			} else {
				//System.out.println("account.getVmsgroup().getId()="+account.getVmsgroup().getId());
				setInputStream(userService.saveAccount(account));
			}
		} catch (Exception e) {
			setInputStream("ERROR");
		}
		return Action.SUCCESS;
	}
	
	public String ajLockAccount() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			if(session.getAttribute(Constances.SESS_USERLOGIN)==null) {
				setInputStream(Constances.END_SESSION);
				return Constances.END_SESSION;
			}
			if(ids!=null)
				userService.lockAccounts(ids);
			setInputStream("OK");
		} catch (Exception e) {
			setInputStream("ERROR");
		}
		return Action.SUCCESS;
	}
	/* Getter and Setter */
	public List<Vmsgroup> getGroups() {
	
		return groups;
	}

	public void setGroups(List<Vmsgroup> groups) {
	
		this.groups = groups;
	}

	public Account getAccount() {
	
		return account;
	}

	public void setAccount(Account account) {
	
		this.account = account;
	}

	public LinkedHashMap<String, Object> getJsonData() {
	
		return jsonData;
	}

	public void setJsonData(LinkedHashMap<String, Object> jsonData) {
	
		this.jsonData = jsonData;
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

	public Long[] getIds() {
	
		return ids;
	}

	public void setIds(Long[] ids) {
	
		this.ids = ids;
	}
	public List<Phongban> getPhongbans() {
		
		return phongbans;
	}

	public void setPhongbans(List<Phongban> phongbans) {
	
		this.phongbans = phongbans;
	}
	
}
