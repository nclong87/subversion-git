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
package com.vms.db.services;
import java.sql.Date;
import java.util.List;

import com.vms.db.models.Event;
import com.vms.db.models.Loaisukien;
import com.vms.web.models.EventExt;
import com.vms.web.models.FindEventByCreator;
import com.vms.web.models.FindEventByReceiver;
public interface EventsService {
	public long save(Event event) throws Exception ;
	public void removeEvent(long event_id);
	public List<EventExt> getAll(Long uid,Date from_date,Date end_date);
	public void saveNhanvienEvent(Long[] msnhanvien,long event_id);
	public List<Loaisukien> getLoaiSuKiens();
	public Event findById(Long id);
	public List<FindEventByCreator> findEventByCreator(long createUser,String date_from, String date_end,int iDisplayStart, int iDisplayLength);
	public List<FindEventByReceiver> findEventBReceiver(long msnhanvien,int state,String date_from, String date_end,int iDisplayStart, int iDisplayLength);
}
