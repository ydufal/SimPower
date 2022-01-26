package com.model.structure.habitation;

import com.GameConstant.ModelConstants;

public class Maison extends Habitation{

	
	
	public Maison(int X, int Y, String type, int index) {
			
		super(X, Y, type);
		this.capacity = ModelConstants.CAPACITY_HOUSE;
		this.name = "H" + index;
		this.tenants = 0;
	}

	@Override
	public float getEnergy() {
		
		return this.tenants * ModelConstants.CONSUMPTION_HOUSE;
	}

}
