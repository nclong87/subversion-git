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

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.vms.db.models.Table1;
import com.vms.db.services.TestService;
import com.vms.web.models.Phongban;

@Transactional
public class TestServiceImpl implements TestService {
	public static final String SQL_GET_LIST_PHONGBAN = "{ ? = call GET_LIST_PHONGBAN()}";
	public static final String GET_LIST_PHONGBAN = "GET_LIST_PHONGBAN";
	private EntityManager em;
	public EntityManager getEm() {
		return em;
	}
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	@Override
	public void insertTest(String test) {
		System.out.println("Begin insertTest");
		Table1 table1 = new Table1();
		table1.setColumn1(test);
		em.persist(table1);
		System.out.println("end insertTest");
	}
	@Override
	public List<Phongban> getAll() {
		Query query=em.createNamedQuery(GET_LIST_PHONGBAN);
		return query.getResultList();
		
	}

}
