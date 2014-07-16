package com.vms.db.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DOITAC database table.
 * 
 */
@Entity
public class Doitac implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Column(name="TEN_DOI_TAC")
	private String tenDoiTac;

    public Doitac() {
    }

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTenDoiTac() {
		return this.tenDoiTac;
	}

	public void setTenDoiTac(String tenDoiTac) {
		this.tenDoiTac = tenDoiTac;
	}

}