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
import com.vms.db.models.Menu;
import com.vms.db.models.Rootmenu;
import com.vms.db.models.UserMenu;
import com.vms.web.models.ValueLabel;
public interface MenuService {
	public List<Menu> getAll();
	public List<Menu> getMenus(String strWhere,int iDisplayStart,int iDisplayLength);
	public String saveMenu(Menu menu);
	public List<Rootmenu> getRootmenus();
	public List<ValueLabel> getMenuByUser(Long id);
	public List<Menu> loadByRoot(int rootmenu);
	public void saveUserMenu(UserMenu[] usermenus);
}
