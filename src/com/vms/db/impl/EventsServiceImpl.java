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
package com.vms.db.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import oracle.jdbc.driver.OracleCallableStatement;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.springframework.transaction.annotation.Transactional;

import com.vms.db.models.Event;
import com.vms.db.models.Loaisukien;
import com.vms.db.services.EventsService;
import com.vms.resources.ConnectionManager;
import com.vms.web.models.EventExt;
import com.vms.web.models.FindEventByCreator;
import com.vms.web.models.FindEventByReceiver;
@Transactional
public class EventsServiceImpl implements EventsService {
	private EntityManager em;
	public EntityManager getEm() {
		return em;
	}
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public static final String SQL_FN_FETCH_EVENTS = "{ ? = call FN_FETCH_EVENTS(:user_id, :date_from, :date_end) } ";
	public static final String FN_FETCH_EVENTS = "FN_FETCH_EVENTS";
	@SuppressWarnings("unchecked")
	@Override
	public List<EventExt> getAll(Long uid, Date from_date, Date end_date) {
		Query query=em.createNamedQuery(FN_FETCH_EVENTS);
		query.setParameter("user_id", uid);
		query.setParameter("date_from", from_date);
		query.setParameter("date_end", end_date);
		return query.getResultList();
		
	}
	@Override
	public long save(Event event) throws Exception {
		// FIXME insert
		long id = event.getId();
		if(id == 0) {
			event.setState(1);
			em.persist(event);
			id = event.getId();
		} else {
			Event tmp = em.find(Event.class, id);
			if(tmp == null) throw new Exception();
			event.setState(tmp.getState());
			em.merge(event);
		}
		return id;
	}
	
	public static final String SQL_PROC_SAVE_NHANVIENEVENT = "{ call PROC_SAVE_NHANVIENEVENT(?,?) } ";
	public static final String PROC_SAVE_NHANVIENEVENT = "PROC_SAVE_NHANVIENEVENT";
	@Override
	public void saveNhanvienEvent(Long[] msnhanvien, long event_id) {
		// FIXME saveNhanvienEvent
		Connection conn = ConnectionManager.connect();		
		OracleCallableStatement stmt = null;
		try {
			System.out.println("***BEGIN saveNhanvienEvent***");
			ArrayDescriptor descriptor = ArrayDescriptor.createDescriptor( "ARR_NUMBER", conn );
			ARRAY arrMsNhanvien =new ARRAY( descriptor, conn, msnhanvien );
			stmt =(OracleCallableStatement)conn.prepareCall(SQL_PROC_SAVE_NHANVIENEVENT );
			stmt.setARRAY(1, arrMsNhanvien);
			stmt.setLong(2, event_id);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("ERROR :" + e.getMessage());
		} 
	}
	@Override
	public List<Loaisukien> getLoaiSuKiens() {
		// FIXME getLoaiSuKiens
		Query query = em.createQuery("select l from Loaisukien l");
		return query.getResultList();
		
	}
	@Override
	public Event findById(Long id) {
		// FIXME findById
		return em.find(Event.class,id);
		
	}
	@Override
	public void removeEvent(long event_id) {
		// FIXME removeEvent
		Query query = em.createNativeQuery("update events set state=0 where id=:event_id");
		query.setParameter("event_id", event_id);
		query.executeUpdate();
	}
	
	public static final String SQL_FIND_EVENT_BY_CREATOR = "{ ? = call FIND_EVENT_BY_CREATOR(:date_from,:date_end,:iDisplayStart, :iDisplayLength, :pi_userCreate) } ";
	public static final String FIND_EVENT_BY_CREATOR = "FIND_EVENT_BY_CREATOR";
	@Override
	public List<FindEventByCreator> findEventByCreator(long createUser,String date_from, String date_end,int iDisplayStart, int iDisplayLength) {
		// FIXME findEventByCreator
		/*Connection conn = ConnectionManager.connect();		
		OracleCallableStatement stmt = null;
		List<FindEventByCreator> list = new ArrayList<FindEventByCreator>();
		try {
			System.out.println("***BEGIN findEventByCreator***");
			stmt =(OracleCallableStatement)conn.prepareCall(SQL_FIND_EVENT_BY_CREATOR );
			stmt.setInt("iDisplayStart", iDisplayStart);
			stmt.setInt("iDisplayLength", iDisplayLength);
			stmt.setLong("pi_userCreate", createUser);
			ResultSet rsdc = stmt.executeQuery();
			while(rsdc.next()) {
				FindEventByCreator findEventByCreator = new FindEventByCreator();
				findEventByCreator.setId(rsdc.getLong("id"));
				findEventByCreator.setLoaithongbao(rsdc.getInt("loaithongbao"));
				findEventByCreator.setNgaysukien(rsdc.getString("ngaysukien"));
				findEventByCreator.setNoidung(rsdc.getString("noidung"));
				findEventByCreator.setState(rsdc.getInt("state"));
				findEventByCreator.setTensukien(rsdc.getString("tensukien"));
				list.add(findEventByCreator);
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("ERROR :" + e.getMessage());
		} */
		Query query = em.createNamedQuery(FIND_EVENT_BY_CREATOR);
		query.setParameter("date_from", date_from);
		query.setParameter("date_end", date_end);
		query.setParameter("iDisplayStart", iDisplayStart);
		query.setParameter("iDisplayLength", iDisplayLength);
		query.setParameter("pi_userCreate", createUser);
		return query.getResultList();
		
	}
	
	public static final String SQL_FIND_EVENT_BY_RECEIVER = "{ ? = call FIND_EVENT_BY_RECEIVER(:date_from,:date_end, :pi_state,:iDisplayStart, :iDisplayLength, :pi_receiver) } ";
	public static final String FIND_EVENT_BY_RECEIVER = "FIND_EVENT_BY_RECEIVER";
	@Override
	public List<FindEventByReceiver> findEventBReceiver(long msnhanvien,int state,
			String date_from, String date_end, int iDisplayStart,
			int iDisplayLength) {
		// FIXME findEventBReceiver
		Query query = em.createNamedQuery(FIND_EVENT_BY_RECEIVER);
		query.setParameter("date_from", date_from);
		query.setParameter("date_end", date_end);
		query.setParameter("pi_state", state);
		query.setParameter("iDisplayStart", iDisplayStart);
		query.setParameter("iDisplayLength", iDisplayLength);
		query.setParameter("pi_receiver", msnhanvien);
		return query.getResultList();
		
	}
	
	
	
	

}
