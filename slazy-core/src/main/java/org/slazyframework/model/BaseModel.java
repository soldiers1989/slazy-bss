package org.slazyframework.model;

import java.io.Serializable;

public class BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8440430255736126539L;
	private String def;

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}

	public BaseModel(String def) {
		super();
		this.def = def;
	}

	public BaseModel() {
		super();
	}
	
	
	
}
