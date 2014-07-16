package com.vms.db.models;

import java.io.Serializable;
import javax.persistence.*;


import com.vms.db.impl.MenuServiceImpl;
import com.vms.web.models.ValueLabel;



/**
 * The persistent class for the MENU database table.
 * 
 */
@NamedNativeQueries( {	
	@NamedNativeQuery(
				name = MenuServiceImpl.FN_MENU_OF_USER, 
				query = MenuServiceImpl.SQL_FN_MENU_OF_USER ,
				resultClass = ValueLabel.class,
				hints = @QueryHint(name = "org.hibernate.callable", value = "true") 
	)
})

@Entity
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MENU_AUTO_GENERATOR", sequenceName="SEQ_MENU", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MENU_AUTO_GENERATOR")
	private Long id;

	@Column(name="\"ACTION\"")
	private String action;

	private Integer active;

	private String namemenu;
	
	private Integer idrootmenu;

    public Menu() {
    }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getActive() {
		return this.active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}


	public String getNamemenu() {
		return this.namemenu;
	}

	public void setNamemenu(String namemenu) {
		this.namemenu = namemenu;
	}

	public Integer getIdrootmenu() {
	
		return idrootmenu;
	}

	public void setIdrootmenu(Integer idrootmenu) {
	
		this.idrootmenu = idrootmenu;
	}
	
}