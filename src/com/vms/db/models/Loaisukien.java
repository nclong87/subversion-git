package com.vms.db.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the LOAISUKIEN database table.
 * 
 */
@Entity
public class Loaisukien implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String tenloaisukien;

	private String text;

    public Loaisukien() {
    }

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTenloaisukien() {
		return this.tenloaisukien;
	}

	public void setTenloaisukien(String tenloaisukien) {
		this.tenloaisukien = tenloaisukien;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

}