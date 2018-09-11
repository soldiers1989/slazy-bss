package com.slazy.bss.slazy.commons.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

public class BaseModel implements Serializable {

	private String def;

	public String getDef() {
		return def;
	}

	public void setDef(String def) {
		this.def = def;
	}
	
	
	
}
