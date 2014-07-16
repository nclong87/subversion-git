package com.vms.web.models;

import java.io.Serializable;
import javax.persistence.*;

import com.vms.db.impl.BophanServiceImpl;
import com.vms.db.impl.EventsServiceImpl;



/**
 * The persistent class for the ACCOUNTS database table.
 * 
 */
@NamedNativeQueries( {	
	@NamedNativeQuery(
				name = EventsServiceImpl.FN_FETCH_EVENTS, 
				query = EventsServiceImpl.SQL_FN_FETCH_EVENTS ,
				resultClass = EventExt.class,
				hints = @QueryHint(name = "org.hibernate.callable", value = "true") 
	)
})

@Entity
public class EventExt implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private String tensukien;
	private Integer loaisukien_id;
	private String noidung;
	private String ngaysukien;
	private Integer loaithongbao;
	private String ngaygui;
	private String date_create;
	private Long user_create;
	private Integer state;

    public EventExt() {
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

	public Integer getLoaisukien_id() {
	
		return loaisukien_id;
	}

	public void setLoaisukien_id(Integer loaisukien_id) {
	
		this.loaisukien_id = loaisukien_id;
	}

	public String getNoidung() {
	
		return noidung;
	}

	public void setNoidung(String noidung) {
	
		this.noidung = noidung;
	}

	public String getNgaysukien() {
	
		return ngaysukien;
	}

	public void setNgaysukien(String ngaysukien) {
	
		this.ngaysukien = ngaysukien;
	}

	public Integer getLoaithongbao() {
	
		return loaithongbao;
	}

	public void setLoaithongbao(Integer loaithongbao) {
	
		this.loaithongbao = loaithongbao;
	}

	public String getNgaygui() {
	
		return ngaygui;
	}

	public void setNgaygui(String ngaygui) {
	
		this.ngaygui = ngaygui;
	}

	public String getDate_create() {
	
		return date_create;
	}

	public void setDate_create(String date_create) {
	
		this.date_create = date_create;
	}

	public Long getUser_create() {
	
		return user_create;
	}

	public void setUser_create(Long user_create) {
	
		this.user_create = user_create;
	}

	public Integer getState() {
	
		return state;
	}

	public void setState(Integer state) {
	
		this.state = state;
	}

	

	
	
	
}