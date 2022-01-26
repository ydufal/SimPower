package com.model.structure.centrale;

import com.model.structure.Structure;

public abstract class PowerPlant extends Structure{
	
	protected boolean isWorking;
	protected float production; 
	
	public PowerPlant(int X, int Y, String type) {
		
		super(X, Y, type);
		this.superType = "Power";
		isWorking = true;
	}
	
	
	
	
}
