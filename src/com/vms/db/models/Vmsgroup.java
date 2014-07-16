package com.vms.db.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the VMSGROUP database table.
 * 
 */
@Entity
public class Vmsgroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Integer active;

	private String namegroup;

	//bi-directional many-to-one association to Account

    public Vmsgroup() {
    }

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getActive() {
		return this.active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getNamegroup() {
		return this.namegroup;
	}

	public void setNamegroup(String namegroup) {
		this.namegroup = namegroup;
	}

	
	
}