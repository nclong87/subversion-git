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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import com.vms.db.models.Account;
import com.vms.db.models.Event;
import com.vms.db.models.Loaisukien;
import com.vms.db.services.EventsService;
import com.vms.db.services.UserService;
import com.vms.utils.Constances;
import com.vms.utils.DateUtils;
import com.vms.utils.NumberUtil;
import com.vms.web.models.AccountExt;
import com.vms.web.models.EventExt;
import com.vms.web.models.FindEventByCreator;
import com.vms.web.models.FindEventByReceiver;

public class EventAction implements Preparable {
	private UserService userService;
	private EventsService eventsService;
	private LinkedHashMap<String, Object> jsonData; //Tra ve du lieu mang Json (ajax)
	private InputStream inputStream; //Tra ve du lieu kieu chuoi (ajax)
	private Long[] users;
	private Event event;
	private List<Loaisukien> loaisukiens;
	@Override
	public void prepare() throws Exception {
		// FIXME prepare
		
	}
	
	public EventAction( UserService service1, EventsService service2) {
		userService = service1;
		eventsService = service2;
	}
	
	public String execute() { //Hien thi man hinh quan ly user
		return Action.SUCCESS;
	}
	
	public String myEvent() { 
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
	
	public String ajMyEvent() {
		
		System.out.println("***ajMyEvent***");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute(Constances.SESS_USERLOGIN);
		if(account==null) return Action.ERROR;
		//Tao chuoi dieu kien where
		String sSearch = request.getParameter("sSearch").trim();
		String date_from = "";
		String date_end = "";
		try {
			JSONArray arrayJson = (JSONArray) new JSONObject(sSearch).get("array");
			for(int i=0;i<arrayJson.length();i++) {
				String name = arrayJson.getJSONObject(i).getString("name");
				String value = arrayJson.getJSONObject(i).getString("value");
				if(value.isEmpty()==false) {
					if(name.equals("date_from"))
						date_from = value;
					if(name.equals("date_end"))
						date_end = value;
				}
			}
			
		} catch (JSONException e) {
			System.out.println("Parse JSON Error : "+e.getMessage());
		}
		
		Integer iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
		Integer iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
		if(request.getParameter("sEcho").equals("1")) date_from = DateUtils.getCurrentDateSQL();
		List<FindEventByCreator> list = eventsService.findEventByCreator(account.getId(),date_from,date_end, iDisplayStart, iDisplayLength);
		jsonData = new LinkedHashMap<String, Object>();
		List<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
		for(int i=0;i<list.size() && i<iDisplayLength;i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			FindEventByCreator eventByCreator = list.get(i);
			map.put("stt", i+1);
			map.put("id", eventByCreator.getId());
			map.put("loaithongbao", eventByCreator.getLoaithongbao());
			map.put("ngaysukien", eventByCreator.getNgaysukien());
			map.put("noidung", eventByCreator.getNoidung());
			map.put("state", eventByCreator.getState());
			map.put("tensukien", eventByCreator.getTensukien());
			items.add(map);
		}
		jsonData.put("sEcho", Integer.parseInt(request.getParameter("sEcho")));
		jsonData.put("iTotalRecords", list.size());
		jsonData.put("iTotalDisplayRecords", list.size());
		jsonData.put("aaData", items);
		return Action.SUCCESS;
	}
	
	public String searchEvent() { 
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
	
	public String ajSearchEvent() {
		
		System.out.println("***ajSearchEvent***");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute(Constances.SESS_USERLOGIN);
		if(account==null) return Action.ERROR;
		//Tao chuoi dieu kien where
		String sSearch = request.getParameter("sSearch").trim();
		long msnhanvien = 0;
		String date_from = "";
		String date_end = "";
		int state = 0;
		try {
			JSONArray arrayJson = (JSONArray) new JSONObject(sSearch).get("array");
			for(int i=0;i<arrayJson.length();i++) {
				String name = arrayJson.getJSONObject(i).getString("name");
				String value = arrayJson.getJSONObject(i).getString("value");
				if(value.isEmpty()==false) {
					if(name.equals("date_from"))
						date_from = value;
					if(name.equals("date_end"))
						date_end = value;
					if(name.equals("msnhanvien"))
						msnhanvien = NumberUtil.parseLong(value);
					if(name.equals("state"))
						state = NumberUtil.parseInt(value);
				}
			}
			
		} catch (JSONException e) {
			System.out.println("Parse JSON Error : "+e.getMessage());
		}
		jsonData = new LinkedHashMap<String, Object>();
		List<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
		if(msnhanvien == 0) {
			jsonData.put("iTotalRecords", 0);
			jsonData.put("iTotalDisplayRecords", 0);
		} else {
			Integer iDisplayStart = Integer.parseInt(request.getParameter("iDisplayStart"));
			Integer iDisplayLength = Integer.parseInt(request.getParameter("iDisplayLength"));
			List<FindEventByReceiver> list = eventsService.findEventBReceiver(msnhanvien,state,date_from,date_end,iDisplayStart, iDisplayLength);
			for(int i=0;i<list.size() && i<iDisplayLength;i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				FindEventByReceiver eventByReceiver = list.get(i);
				map.put("stt", i+1);
				map.put("id", eventByReceiver.getId());
				map.put("loaithongbao", eventByReceiver.getLoaithongbao());
				map.put("ngaysukien", eventByReceiver.getNgaysukien());
				map.put("noidung", eventByReceiver.getNoidung());
				map.put("state", eventByReceiver.getState());
				map.put("tensukien", eventByReceiver.getTensukien());
				items.add(map);
			}
			jsonData.put("iTotalRecords", list.size());
			jsonData.put("iTotalDisplayRecords", list.size());
		}
		
		jsonData.put("sEcho", Integer.parseInt(request.getParameter("sEcho")));
		jsonData.put("aaData", items);
		return Action.SUCCESS;
	}
	
	public String getEvents() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute(Constances.SESS_USERLOGIN);
		if(account==null) {
			setInputStream(Constances.END_SESSION);
			return Constances.END_SESSION;
		}
		String miliStart = request.getParameter("start");
		String miliEnd = request.getParameter("end");
		Date start_date = new Date(Long.parseLong(miliStart));
		Date end_date = new Date(Long.parseLong(miliEnd));
		List<EventExt> eventExts = eventsService.getAll(account.getId(), start_date, end_date);
		jsonData = new LinkedHashMap<String, Object>();
		jsonData.put("data", eventExts);
		return Action.SUCCESS;
	}
	
	public String addEvent() { 
		HttpServletRequest request = ServletActionContext.getRequest();
		loaisukiens = eventsService.getLoaiSuKiens();
		return Action.SUCCESS;
	}
	public String editEvent() { 
		HttpServletRequest request = ServletActionContext.getRequest();
		Long event_id = NumberUtil.parseLong(request.getParameter("id"));
		event = eventsService.findById(event_id);
		if(event == null) return Action.ERROR;
		loaisukiens = eventsService.getLoaiSuKiens();
		return Action.SUCCESS;
	}
	
	public String saveEvent() {
		try {
			//HttpServletRequest request = ServletActionContext.getRequest();
			
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Account account = (Account) session.getAttribute(Constances.SESS_USERLOGIN);
			if(account==null) throw new Exception("END_SESSION");
			if(event.getTensukien().isEmpty()) {
				throw new Exception("EMPTY_TENSUKIEN");
			}
			if(users == null) {
				throw new Exception("EMPTY_NHANVIEN");
			}
			java.util.Date date = DateUtils.parseDate(event.getNgaysukien(), "dd/MM/yyyy");
			event.setNgaysukien(DateUtils.parseToSQLDate(date));
			event.setNgaygui(DateUtils.parseToSQLDate(DateUtils.add(date, Calendar.DATE, (-event.getSendBefore()))));
			event.setDateCreate(DateUtils.getCurrentDateSQL());
			event.setUserCreate(account.getId());
			//System.out.println(event.getNgaysukien());
			//System.out.println(event.getTensukien());
			long event_id = eventsService.save(event);
			eventsService.saveNhanvienEvent(users, event_id);
			setInputStream("OK");
		} catch (Exception e) {
			System.out.println("ERROR :" + e.getMessage());
			setInputStream(e.getMessage());
		}
		return Action.SUCCESS;
	}
	
	public String removeEvent() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			Account account = (Account) session.getAttribute(Constances.SESS_USERLOGIN);
			if(account==null) throw new Exception("END_SESSION");
			Long event_id = NumberUtil.parseLong(request.getParameter("id"));
			eventsService.removeEvent(event_id);
			setInputStream("OK");
		} catch (Exception e) {
			System.out.println("ERROR :" + e.getMessage());
			setInputStream(e.getMessage());
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
	
	

	public List<Loaisukien> getLoaisukiens() {
	
		return loaisukiens;
	}

	public void setLoaisukiens(List<Loaisukien> loaisukiens) {
	
		this.loaisukiens = loaisukiens;
	}

	public Long[] getUsers() {
	
		return users;
	}

	public void setUsers(Long[] users) {
	
		this.users = users;
	}

	public void setInputStream(InputStream inputStream) {
	
		this.inputStream = inputStream;
	}

	public Event getEvent() {
	
		return event;
	}

	public void setEvent(Event event) {
	
		this.event = event;
	}
	
	
	
}
