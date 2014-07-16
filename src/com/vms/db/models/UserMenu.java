package com.vms.db.models;

import java.io.Serializable;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import javax.persistence.*;


/**
 * The persistent class for the USER_MENU database table.
 * 
 */
@Entity
@Table(name="USER_MENU")
public class UserMenu implements SQLData,Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Integer accountid;
	
	private Integer menuid;

    public UserMenu() {
    }

	public Integer getAccountid() {
	
		return accountid;
	}

	public void setAccountid(Integer accountid) {
	
		this.accountid = accountid;
	}

	public Integer getMenuid() {
	
		return menuid;
	}

	public void setMenuid(Integer menuid) {
	
		this.menuid = menuid;
	}

	@Override
	public String getSQLTypeName() throws SQLException {
		return "OBJ_USERMENU";
		
	}

	@Override
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		setAccountid(stream.readInt());
		setMenuid(stream.readInt());
	}

	@Override
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeInt(getAccountid());
		stream.writeInt(getMenuid());
	}

	
	
}