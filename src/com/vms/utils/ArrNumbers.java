package com.vms.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import lacviet.db.jdbc.ResourceManager;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;
public class ArrNumbers implements UserType {

	public static final String ORCALE_OBJECTYPE_ARRAYSTATION = "ARR_NUMBER";
	public Object assemble(Serializable arg0, Object arg1)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object deepCopy(Object arg0) throws HibernateException {
		// TODO Auto-generated method stub
		return arg0;
	}

	public Serializable disassemble(Object arg0) throws HibernateException {
		// TODO Auto-generated method stub
		return (Serializable) arg0;
	}

	public boolean equals(Object arg0, Object arg1) throws HibernateException {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode(Object arg0) throws HibernateException {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isMutable() {
		// TODO Auto-generated method stub
		return false;
	}

	public Object nullSafeGet(ResultSet arg0, String[] arg1, Object arg2)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		//System.out.println("heello1234567");
		return null;
	}

	@SuppressWarnings("unchecked")
	public void nullSafeSet(PreparedStatement arg0, Object arg1, int arg2)	
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub	
		System.out.println("GET CONNECTION");
		 final Connection connection = ResourceManager.connect();
		 
		 System.out.println("connection="+connection);
		// System.out.println(arg1);
		 //Long[] numArr = new Long[];
		 if (arg1 != null){
			 Integer[] arrayNum = (Integer[]) arg1;
			 final ARRAY array = new ARRAY(ArrayDescriptor.createDescriptor(ORCALE_OBJECTYPE_ARRAYSTATION, connection),connection,arrayNum);
			 //System.out.println(numArr[7]);
			 System.out.println("arrayNum.size="+arrayNum.length);
			 arg0.setArray(arg2, array);			 
		 }
	}

	public Object replace(Object arg0, Object arg1, Object arg2)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	public Class returnedClass() {
		// TODO Auto-generated method stub
		return null;
	}

	public int[] sqlTypes() {
		// TODO Auto-generated method stub
		return new int[]{Types.ARRAY};
	}

}
