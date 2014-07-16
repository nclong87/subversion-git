package com.vms.db.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the "EVENTS" database table.
 * 
 */
@Entity
@Table(name="\"EVENTS\"")
@SequenceGenerator(name="EVENT_AUTO_INCREASE", sequenceName="SEQ_EVENTS", allocationSize=1)
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EVENT_AUTO_INCREASE")
	private long id;

	@Column(name="DATE_CREATE")
	private String dateCreate;

	@Column(name="LOAISUKIEN_ID")
	private Integer loaisukienId;

	private Integer loaithongbao;
	
	@Column(name="SEND_BEFORE")
	private Integer sendBefore;

	private String ngaygui;

	private String ngaysukien;

	private String noidung;

	@Column(name="\"STATE\"")
	private Integer state;

	private String tensukien;

	@Column(name="USER_CREATE")
	private Long userCreate;

    public Event() {
    }

	public long getId() {
	
		return id;
	}

	public void setId(long id) {
	
		this.id = id;
	}

	public String getDateCreate() {
	
		return dateCreate;
	}

	public void setDateCreate(String dateCreate) {
	
		this.dateCreate = dateCreate;
	}

	
	public Integer getSendBefore() {
	
		return sendBefore;
	}

	public void setSendBefore(Integer sendBefore) {
	
		this.sendBefore = sendBefore;
	}

	public Integer getLoaisukienId() {
	
		return loaisukienId;
	}

	public void setLoaisukienId(Integer loaisukienId) {
	
		this.loaisukienId = loaisukienId;
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

	public Integer getState() {
	
		return state;
	}

	public void setState(Integer state) {
	
		this.state = state;
	}

	public String getTensukien() {
	
		return tensukien;
	}

	public void setTensukien(String tensukien) {
	
		this.tensukien = tensukien;
	}

	public Long getUserCreate() {
	
		return userCreate;
	}

	public void setUserCreate(Long userCreate) {
	
		this.userCreate = userCreate;
	}

	

}