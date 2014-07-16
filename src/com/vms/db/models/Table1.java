package com.vms.db.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TABLE1 database table.
 * 
 */
@Entity
@SequenceGenerator(name="TABLE1_AUTO_INCREASE", sequenceName="SEQ_TEST", allocationSize=1)
public class Table1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TABLE1_AUTO_INCREASE")
	private long id;

	private String column1;

    public Table1() {
    }

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getColumn1() {
		return this.column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

}