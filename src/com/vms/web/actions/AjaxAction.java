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
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import com.vms.db.models.Account;
import com.vms.db.models.Event;
import com.vms.db.models.Loaisukien;
import com.vms.db.services.EventsService;
import com.vms.db.services.NhanvienService;
import com.vms.db.services.UserService;
import com.vms.utils.Constances;
import com.vms.utils.DateUtils;
import com.vms.utils.NumberUtil;
import com.vms.web.models.EventExt;
import com.vms.web.models.Nhanvien;

public class AjaxAction implements Preparable {
	private EventsService eventsService;
	private NhanvienService nhanvienService;
	private LinkedHashMap<String, Object> jsonData; //Tra ve du lieu mang Json (ajax)
	private InputStream inputStream; //Tra ve du lieu kieu chuoi (ajax)
	@Override
	public void prepare() throws Exception {
		// FIXME prepare
		
	}
	
	public AjaxAction( EventsService service1, NhanvienService service2) {
		eventsService = service1;
		nhanvienService = service2;
	}
	
	public String execute() { //Hien thi man hinh quan ly user
		return Action.SUCCESS;
	}
	
	public String getNhanvienByEvent() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Long event_id = NumberUtil.parseLong(request.getParameter("id"));
		List<Nhanvien> nhanviens = nhanvienService.findByEvent(event_id);
		jsonData = new LinkedHashMap<String, Object>();
		jsonData.put("data", nhanviens);
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
	

	public void setInputStream(InputStream inputStream) {
	
		this.inputStream = inputStream;
	}

	
}
