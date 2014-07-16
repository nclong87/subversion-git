package com.vms.web.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;

import com.vms.db.impl.UserServiceImpl;

/*@SqlResultSetMappings({
	@SqlResultSetMapping(name=UserServiceImpl.FIND_ACCOUNTS, 
			columns={
			@ColumnResult(name="id"),
			@ColumnResult(name="username"),
			@ColumnResult(name="active"),
			@ColumnResult(name="msnhanvien"),
			@ColumnResult(name="honhanvien"),
			@ColumnResult(name="tennhanvien")
			}
	)
})*/
@NamedNativeQueries( {	
	@NamedNativeQuery(
				name = UserServiceImpl.FIND_ACCOUNTS, 
				query = UserServiceImpl.SQL_FIND_ACCOUNTS ,
				resultClass = AccountExt.class,
				hints = @QueryHint(name = "org.hibernate.callable", value = "true") 
	),
	@NamedNativeQuery(
			name = UserServiceImpl.FN_FIND_ACCOUNT_BYID, 
			query = UserServiceImpl.SQL_FN_FIND_ACCOUNT_BYID ,
			resultClass = AccountExt.class,
			hints = @QueryHint(name = "org.hibernate.callable", value = "true") 
			)
})

@Entity
public class AccountExt implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;

	private String username;
	
	private Integer active;
	
	private String msnhanvien;
	
	private String honhanvien;
	
	private String tennhanvien;

    public AccountExt() {
    }

	public Long getId() {
	
		return id;
	}

	public void setId(Long id) {
	
		this.id = id;
	}

	public String getUsername() {
	
		return username;
	}

	public void setUsername(String username) {
	
		this.username = username;
	}

	public Integer getActive() {
	
		return active;
	}

	public void setActive(Integer active) {
	
		this.active = active;
	}

	public String getMsnhanvien() {
	
		return msnhanvien;
	}

	public void setMsnhanvien(String msnhanvien) {
	
		this.msnhanvien = msnhanvien;
	}

	public String getHonhanvien() {
	
		return honhanvien;
	}

	public void setHonhanvien(String honhanvien) {
	
		this.honhanvien = honhanvien;
	}

	public String getTennhanvien() {
	
		return tennhanvien;
	}

	public void setTennhanvien(String tennhanvien) {
	
		this.tennhanvien = tennhanvien;
	}
    
	
	
	
}