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
import com.vms.web.models.Bophan;
@Transactional
public class BophanServiceImpl implements BophanService {
	private EntityManager em;
	public EntityManager getEm() {
		return em;
	}
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	public static final String SQL_FN_LIST_BOPHAN = "{ ? = call FN_LIST_BOPHAN(:pi_msphongban)}";
	public static final String FN_LIST_BOPHAN = "FN_LIST_BOPHAN";
	@Override
	public List<Bophan> getAll(Integer msphongban) {
		Query query=em.createNamedQuery(FN_LIST_BOPHAN);
		query.setParameter("pi_msphongban", msphongban);
		System.out.println("query");
		return query.getResultList();
		
	}
	

}
