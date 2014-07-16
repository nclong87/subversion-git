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
package com.vms.web.actions;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.vms.db.models.UserMenu;
import com.vms.utils.DateUtils;

public class Test {

	/** FIXME Comment this
	 *
	 * @description 
	 * @author nclong
	 * @param args
	 * @return void
	 */
	public static void main(String[] args) {
		// FIXME main
		//String str = "{'array':[{\"name\":\"account.id\",\"value\":\"\"},{\"name\":\"account.username\",\"value\":\"\"},{\"name\":\"account.password\",\"value\":\"\"},{\"name\":\"account.idgroup\",\"value\":\"ADMIN\"}]}";
		/*List<UserMenu> lst = new ArrayList<UserMenu>();
		UserMenu m = new UserMenu();
		m.setAccountid(1);
		m.setMenuid(2);
		lst.add(m);
		lst.
		for(int i=0;i<lst.size();i++) {
			System.out.println(lst.get(i).getAccountid());
		}*/
		
		//System.out.println("Hello");
		/*long l = 1341075600;
		Date date = new Date(l*1000);
		System.out.println(date.toString());*/
		String str = "20/07/2012";
		str = DateUtils.parseStringDateSQL(str, "dd/MM/yyyy");
		System.out.println(str);
		
	}

}
