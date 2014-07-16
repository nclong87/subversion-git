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

import java.sql.Clob;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;



import com.vms.db.models.Account;
import com.vms.db.models.Table1;
import com.vms.db.models.Vmsgroup;
import com.vms.db.services.TestService;
import com.vms.db.services.UserService;
import com.vms.utils.Constances;
import com.vms.utils.NumberUtil;
import com.vms.utils.StringUtil;
import com.vms.web.models.AccountExt;

@Transactional
public class UserServiceImpl implements UserService {
	private EntityManager em;
	public EntityManager getEm() {
		return em;
	}
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	@Override
	public Account checkLogin(String username, String password) {
		// FIXME checkLogin
		Query query = em.createQuery("select a from Account a where active = 1 and a.username = ? and a.password = ?");
		query.setParameter(1, username);
		query.setParameter(2, password);
		List<Account> accounts = query.getResultList();
		if(accounts.size()>0)
			return accounts.get(0);
		return null;
	}
	
	private static final String GETLISTMENU = "SELECT GETLISTMENU(?,?) AS RS FROM DUAL";
	@Override
	public String getMenu(Account account) {
		// FIXME getMenu
		Query query = em.createNativeQuery(GETLISTMENU);
		query.setParameter(1, account.getId());
		query.setParameter(2, null);
		return StringUtil.clobToString((Clob)query.getSingleResult());
		
	}
	@Override
	public List<Vmsgroup> getAllGroup() {
		// FIXME getAllGroup
		Query query = em.createQuery("select g from Vmsgroup g where active = 1");
		return query.getResultList();
		
	}
	private boolean existUsername(String username) {
		Query query = em.createQuery("select a from Account a where username = ?");
		query.setParameter(1, username);
		List<Account> lstAccount = query.getResultList();
		if(lstAccount.size()==0)
			return false;
		return true;
			
	}
	@Override
	public String saveAccount(Account account) {
		// FIXME insertAccount
		System.out.println("account.getId()="+account.getId());
		System.out.println("account.getUsername()="+account.getUsername());
		if(account.getId()==null) {
			if(existUsername(account.getUsername()))
				return Constances.ACCOUNT_EXIST;
			em.persist(account);
			if(account.getId()!=null)
				return Constances.OK;
		} else {
			if(account.getPassword().isEmpty()) {
				Account tmpAccount = em.find(Account.class, account.getId());
				account.setPassword(tmpAccount.getPassword());
			}
			em.merge(account);
			return Constances.OK;
		}
		return Constances.ERROR;
		
	}
	@Override
	public List<Account> getAccounts(String strWhere,int iDisplayStart,int iDisplayLength) {
		// FIXME getAccounts
		Query query = em.createQuery("select a from Account a where"+strWhere+" order by id desc");
		//Query query = em.createQuery("select a from Account a");
		query.setFirstResult(iDisplayStart);
		query.setMaxResults(iDisplayLength);
		/*Iterator<Object[]> itr = query.getResultList().iterator();
		List<AccountExt> lstResult = new ArrayList();	
		while (itr.hasNext()) {		
			Object[] array = itr.next();
			AccountExt accountExt = new AccountExt();
			accountExt.setId(NumberUtil.longValue(array[0]));
			codefb.setIdcode((String)array[0]);
			codefb.setIddep((String)array[1]);
			codefb.setIdkindcard((String)array[2]);
			codefb.setNamecode((String)array[3]);
			codefb.setToaccess((String)array[4]);
			codefb.setCallout((String)array[5]);
			codefb.setFilepath((String)array[6]);
			codefb.setFlagcode((String)array[7]);
			codefb.setBackuped(NumberUtil.intValue(array[8]));
			codefb.setPricode(NumberUtil.intValue(array[9]));
			codefb.setTmp1code((String)array[10]);
			codefb.setTmp2code((String)array[11]);
			codefb.setTmp3code((String)array[12]);
			if(out[0] == -1) {				
				out[0] = NumberUtil.intValue(array[13]);
			}				
			lstResult.add(codefb);		
		}
		return lstResult;*/
		return query.getResultList();
		
	}
	@Override
	public void lockAccounts(Long[] ids) {
		// FIXME lockAccounts
		String tmp = "";
		for(int i=0;i<ids.length;i++) {
			if(i==ids.length-1)
				tmp+=ids[i];
			else
				tmp+=ids[i]+",";
		}
		Query query = em.createNativeQuery("UPDATE ACCOUNTS SET ACTIVE=0 WHERE ID IN ("+tmp+")");
		query.executeUpdate();
	}
	public static final String SQL_FIND_ACCOUNTS = "{ ? = call FIND_ACCOUNTS(:iDisplayStart,:iDisplayLength,:pi_username,:pi_msnhanvien)}";
	public static final String FIND_ACCOUNTS = "FIND_ACCOUNTS";
	@Override
	public List<AccountExt> findNhanvien(String username, Integer msnhanvien, int iDisplayStart, int iDisplayLength) {
		Query query=em.createNamedQuery(FIND_ACCOUNTS);
		query.setParameter("iDisplayStart", iDisplayStart);
		query.setParameter("iDisplayLength", iDisplayLength);
		query.setParameter("pi_username", username);
		query.setParameter("pi_msnhanvien", msnhanvien);
		return query.getResultList();
		
	}
	
	public static final String SQL_FN_FIND_ACCOUNT_BYID = "{ ? = call FN_FIND_ACCOUNT_BYID(:id)}";
	public static final String FN_FIND_ACCOUNT_BYID = "FN_FIND_ACCOUNT_BYID";
	@Override
	public AccountExt findNhanvienById(Long id) {
		Query query=em.createNamedQuery(FN_FIND_ACCOUNT_BYID);
		query.setParameter("id", id);
		return (AccountExt) query.getSingleResult();
	}
	@Override
	public void updatePassword(Long uid, String password) {
		// FIXME updatePassword
		Query query = em.createQuery("Update Account set password = :pi_password where id = :pi_uid");
		query.setParameter("pi_password", password);
		query.setParameter("pi_uid", uid);
		query.executeUpdate();
	}
	
	

}
