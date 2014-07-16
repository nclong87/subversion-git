package com.vms.web.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;

import com.vms.db.impl.BophanServiceImpl;


/**
 * The persistent class for the BOPHAN database table.
 * 
 */
@NamedNativeQueries( {	
	@NamedNativeQuery(
				name = BophanServiceImpl.FN_LIST_BOPHAN, 
				query = BophanServiceImpl.SQL_FN_LIST_BOPHAN ,
				resultClass = Bophan.class,
				hints = @QueryHint(name = "org.hibernate.callable", value = "true") 
	)
})
@Entity
public class Bophan implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private Long msbophan;

	private Integer active;

	private String mabophan;

	private Integer msphongban;

	private Integer stt_bp;

	private String tenbophan;

    public Bophan() {
    }

	public Long getMsbophan() {
	
		return msbophan;
	}

	public void setMsbophan(Long msbophan) {
	
		this.msbophan = msbophan;
	}

	public Integer getActive() {
	
		return active;
	}

	public void setActive(Integer active) {
	
		this.active = active;
	}

	public String getMabophan() {
	
		return mabophan;
	}

	public void setMabophan(String mabophan) {
	
		this.mabophan = mabophan;
	}

	public Integer getMsphongban() {
	
		return msphongban;
	}

	public void setMsphongban(Integer msphongban) {
	
		this.msphongban = msphongban;
	}

	

	public Integer getStt_bp() {
	
		return stt_bp;
	}

	public void setStt_bp(Integer stt_bp) {
	
		this.stt_bp = stt_bp;
	}

	public String getTenbophan() {
	
		return tenbophan;
	}

	public void setTenbophan(String tenbophan) {
	
		this.tenbophan = tenbophan;
	}
    
	
}