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
@Entity
public class ValueLabel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String value;

	private String label;

    public ValueLabel() {
    }

	public String getValue() {
	
		return value;
	}

	public void setValue(String value) {
	
		this.value = value;
	}

	public String getLabel() {
	
		return label;
	}

	public void setLabel(String label) {
	
		this.label = label;
	}

	

	
    
	
}