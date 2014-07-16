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

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.vms.db.services.BophanService;
import com.vms.db.services.NhanvienService;
import com.vms.web.models.Bophan;
import com.vms.web.models.Nhanvien;
@Transactional
public class NhanvienServiceImpl implements NhanvienService {
	private EntityManager em;
	public EntityManager getEm() {
		return em;
	}
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	public static final String SQL_FN_FIND_NHANVIEN_BY_BOPHAN = "{ ? = call FN_FIND_NHANVIEN_BY_BOPHAN(:pi_msbophan)}";
	public static final String FN_FIND_NHANVIEN_BY_BOPHAN = "FN_FIND_NHANVIEN_BY_BOPHAN";
	@Override
	public List<Nhanvien> getByBophan(Integer msbophan) {
		Query query=em.createNamedQuery(FN_FIND_NHANVIEN_BY_BOPHAN);
		query.setParameter("pi_msbophan", msbophan);
		return query.getResultList();
		
	}
	
	public static final String SQL_FN_FIND_NHANVIEN_BY_EVENT = "{ ? = call FN_FIND_NHANVIEN_BY_EVENT(:pi_eventId) } ";
	public static final String FN_FIND_NHANVIEN_BY_EVENT = "FN_FIND_NHANVIEN_BY_EVENT";
	@Override
	public List<Nhanvien> findByEvent(long event_id) {
		// FIXME findByEvent
		Query query=em.createNamedQuery(FN_FIND_NHANVIEN_BY_EVENT);
		query.setParameter("pi_eventId", event_id);
		return query.getResultList();
		
	}

}
