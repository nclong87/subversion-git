package com.vms.web.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;

import com.vms.db.impl.EventsServiceImpl;

@NamedNativeQueries( {	
	@NamedNativeQuery(
		name = EventsServiceImpl.FIND_EVENT_BY_RECEIVER, 
		query = EventsServiceImpl.SQL_FIND_EVENT_BY_RECEIVER ,
		resultClass = FindEventByReceiver.class,
		hints = @QueryHint(name = "org.hibernate.callable", value = "true") 
	)
})
@Entity
public class FindEventByReceiver implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;

	private String tensukien;

	private String ngaysukien;

	private String noidung;

	private Integer loaithongbao;

	private Integer state;

    public FindEventByReceiver() {
    }



	public Long getId() {
	
		return id;
	}

	public void setId(Long id) {
	
		this.id = id;
	}

	public String getTensukien() {
	
		return tensukien;
	}

	public void setTensukien(String tensukien) {
	
		this.tensukien = tensukien;
	}

	public String getNgaysukien() {
	
		return ngaysukien;
	}

	public void setNgaysukien(String ngaysukien) {
	
		this.ngaysukien = ngaysukien;
	}

	public String getNoidung() {
	
		return noidung;
	}

	public void setNoidung(String noidung) {
	
		this.noidung = noidung;
	}

	public Integer getLoaithongbao() {
	
		return loaithongbao;
	}

	public void setLoaithongbao(Integer loaithongbao) {
	
		this.loaithongbao = loaithongbao;
	}

	public Integer getState() {
	
		return state;
	}

	public void setState(Integer state) {
	
		this.state = state;
	}

	
	

}