package com.vms.web.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;

import com.vms.db.impl.PhongbanServiceImpl;


/**
 * The persistent class for the PHONGBAN database table.
 * 
 */
@NamedNativeQueries( {	
	@NamedNativeQuery(
				name = PhongbanServiceImpl.GET_LIST_PHONGBAN, 
				query = PhongbanServiceImpl.SQL_GET_LIST_PHONGBAN ,
				resultClass = Phongban.class,
				hints = @QueryHint(name = "org.hibernate.callable", value = "true") 
	)
})
@Entity
public class Phongban implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Integer msphongban;

	private Integer activee;
	
	private String maphongban;

	private Integer stt_pb;

	private String tenphongban;

    public Phongban() {
    }

	public Integer getMsphongban() {
	
		return msphongban;
	}

	public void setMsphongban(Integer msphongban) {
	
		this.msphongban = msphongban;
	}

	public Integer getActivee() {
	
		return activee;
	}

	public void setActivee(Integer activee) {
	
		this.activee = activee;
	}

	public String getMaphongban() {
	
		return maphongban;
	}

	public void setMaphongban(String maphongban) {
	
		this.maphongban = maphongban;
	}

	
	public Integer getStt_pb() {
	
		return stt_pb;
	}

	public void setStt_pb(Integer stt_pb) {
	
		this.stt_pb = stt_pb;
	}

	public String getTenphongban() {
	
		return tenphongban;
	}

	public void setTenphongban(String tenphongban) {
	
		this.tenphongban = tenphongban;
	}

	

}