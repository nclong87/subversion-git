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
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import oracle.jdbc.driver.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.springframework.transaction.annotation.Transactional;

import com.vms.db.models.Menu;
import com.vms.db.models.Rootmenu;
import com.vms.db.models.UserMenu;
import com.vms.db.services.MenuService;
import com.vms.resources.ConnectionManager;
import com.vms.utils.Constances;
import com.vms.web.models.ValueLabel;
@Transactional
public class MenuServiceImpl implements MenuService {
	private EntityManager em;
	public EntityManager getEm() {
		return em;
	}
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	@Override
	public List<Menu> getAll() {
		// FIXME getAll
		Query query=em.createQuery("SELECT m FROM Menu m");
		return query.getResultList();
	}
	
	@Override
	public String saveMenu(Menu menu) {
		// FIXME saveMenu
		System.out.println("menu.getactive()="+menu.getActive());
		if(menu.getId()!=null)
			em.merge(menu);
		else
			em.persist(menu);
		return Constances.OK;
	}
	@Override
	public List<Menu> getMenus(String strWhere, int iDisplayStart,int iDisplayLength) {
		// FIXME getMenus
		Query query = em.createQuery("SELECT m FROM Menu m WHERE"+strWhere+" ORDER BY id DESC");
		query.setFirstResult(iDisplayStart);
		query.setMaxResults(iDisplayLength);
		return query.getResultList();
	}
	@Override
	public List<Rootmenu> getRootmenus() {
		Query query=em.createQuery("SELECT rm FROM Rootmenu rm");
		return query.getResultList();
	}
	public static final String SQL_FN_MENU_OF_USER = "{ ? = call FN_MENU_OF_USER(:id)}";
	public static final String FN_MENU_OF_USER = "FN_MENU_OF_USER";
	@Override
	public List<ValueLabel> getMenuByUser(Long id) {
		Query query=em.createNamedQuery(FN_MENU_OF_USER);
		query.setParameter("id", id);
		return query.getResultList();
	}
	@Override
	public List<Menu> loadByRoot(int rootmenu) {
		String sQuery = "";
		if(rootmenu==0) {
			sQuery = "SELECT m FROM Menu m where active = 1";
		} else {
			sQuery = "select m from Menu m where active = 1 and idrootmenu="+rootmenu;
		}
		Query query=em.createQuery(sQuery);
		return query.getResultList();
	}
	public static final String SQL_PROC_INSERT_USERMENUS = "{ call PROC_INSERT_USERMENUS(?) } ";
	public static final String PROC_INSERT_USERMENUS = "PROC_INSERT_USERMENUS";
	@Override
	public void saveUserMenu(UserMenu[] usermenus) {
		// FIXME saveUserMenu
		Connection conn = ConnectionManager.connect();		
		OracleCallableStatement stmt = null;
		try {
			System.out.println("***BEGIN IMPORT***");
			ArrayDescriptor descriptor = ArrayDescriptor.createDescriptor( "ARR_USERMENU", conn );
			ARRAY array_usermenu =new ARRAY( descriptor, conn, usermenus );
			stmt =(OracleCallableStatement)conn.prepareCall(SQL_PROC_INSERT_USERMENUS );
			stmt.setARRAY(1, array_usermenu);
			stmt.executeUpdate();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("ERROR :" + e.getMessage());
		} 
		
		
	}
	
	

}
