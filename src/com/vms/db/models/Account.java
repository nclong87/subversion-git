package com.vms.db.models;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the ACCOUNTS database table.
 * 
 */


@Entity
@Table(name="ACCOUNTS")
@SequenceGenerator(name="ACCOUNTS_AUTO_INCREASE", sequenceName="SEQ_ACCOUNTS", allocationSize=1)
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer active;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACCOUNTS_AUTO_INCREASE")
	private Long id;

	private String password;

	private String username;

	private Integer msnhanvien;

    public Account() {
    }

	

	public Integer getActive() {
	
		return active;
	}



	public void setActive(Integer active) {
	
		this.active = active;
	}



	public Long getId() {
	
		return id;
	}



	public void setId(Long id) {
	
		this.id = id;
	}



	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public Integer getMsnhanvien() {
	
		return msnhanvien;
	}



	public void setMsnhanvien(Integer msnhanvien) {
	
		this.msnhanvien = msnhanvien;
	}

	
	
}