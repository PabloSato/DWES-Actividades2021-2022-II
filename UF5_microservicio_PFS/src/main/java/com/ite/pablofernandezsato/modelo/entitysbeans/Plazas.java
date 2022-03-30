package com.ite.pablofernandezsato.modelo.entitysbeans;

import java.io.Serializable;

public class Plazas implements Serializable{
	private static final long serialVersionUID = 1L;
	
	int plazas_quedan;

	public Plazas() {
		super();
	}

	public Plazas(int plazas_quedan) {
		super();
		this.plazas_quedan = plazas_quedan;
	}

	public int getPlazas_quedan() {
		return plazas_quedan;
	}

	public void setPlazas_quedan(int plazas_quedan) {
		this.plazas_quedan = plazas_quedan;
	}

	@Override
	public String toString() {
		return "Plazas [plazas_quedan=" + plazas_quedan + "]";
	}
	

	
}
