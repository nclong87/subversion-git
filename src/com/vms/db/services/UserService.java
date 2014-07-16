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


import java.util.List;

import com.vms.db.models.Account;
import com.vms.db.models.Vmsgroup;
import com.vms.web.models.AccountExt;

public interface UserService {
	public Account checkLogin(String username,String password);
	public String getMenu(Account account);
	public List<Vmsgroup> getAllGroup();
	public String saveAccount(Account account);
	public List<Account> getAccounts(String strWhere,int iDisplayStart,int iDisplayLength);
	public void lockAccounts(Long[] ids);
	public List<AccountExt> findNhanvien(String username,Integer msnhanvien, int iDisplayStart, int iDisplayLength);
	public AccountExt findNhanvienById(Long id);
	public void updatePassword(Long uid,String password);
}
