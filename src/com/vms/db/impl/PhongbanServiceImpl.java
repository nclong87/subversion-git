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

import com.vms.db.models.Menu;
import com.vms.db.services.MenuService;
import com.vms.db.services.PhongbanService;
import com.vms.utils.Constances;
import com.vms.web.models.Phongban;
@Transactional
public class PhongbanServiceImpl implements PhongbanService {
	private EntityManager em;
	public EntityManager getEm() {
		return em;
	}
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	public static final String SQL_GET_LIST_PHONGBAN = "{ ? = call GET_LIST_PHONGBAN()}";
	public static final String GET_LIST_PHONGBAN = "GET_LIST_PHONGBAN";
	@Override
	public List<Phongban> getAll() {
		Query query=em.createNamedQuery(GET_LIST_PHONGBAN);
		return query.getResultList();
		
	}
	

}
