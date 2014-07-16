package com.vms.db.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;


/**
 * The persistent class for the ROOTMENU database table.
 * 
 */
@Entity
public class Rootmenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String name;

    public Rootmenu() {
    }

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	//bi-directional many-to-one association to Account
	//@OneToMany(mappedBy="rootmenu")
	//private Set<Menu> menus;

	

}