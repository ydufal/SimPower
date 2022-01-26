package com.model.habitant;

import java.text.DecimalFormat;

import com.model.Model;

public class People {
	
	private String name;
	private String namePower;
	private String nameHouse;
	
	private int satisfaction;
	
	public People(String name, String namePower, String nameHouse) {
		this.name = name;
		this.nameHouse = nameHouse;
		this.namePower = namePower;
		this.satisfaction = 100;
	}
	
	
	
	
	public void SatisUpdate(Model game) {
		
		
		float coef = (float) (1 - (0.001*game.getPollution()));
		
		this.satisfaction = 100;
		
		if(this.namePower == null) {
			this.satisfaction -= 50;
		}
		if(this.nameHouse == null) {
			this.satisfaction -= 50;
		}
		
		this.satisfaction *= coef;
	}
	
	
	
	
	
	public void setName(String name) {
		this.name = name;
	}
	public void setNamePower(String name) {
		this.namePower = name;
	}
	public void setNameHouse(String name) {
		this.nameHouse = name;
	}
	public void setSatis(int satis) {
		this.satisfaction = satis;
	}
	
	public String getName() {
		return this.name;
	}
	public String getNamePower() {
		return this.namePower;
	}
	public String getNameHouse() {
		return this.nameHouse;
	}
	public float getSatisfaction() {
		return this.satisfaction;
	}
}
