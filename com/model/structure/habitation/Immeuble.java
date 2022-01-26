package com.model.structure.habitation;

import com.GameConstant.ModelConstants;

public class Immeuble extends Habitation{

	public Immeuble(int X, int Y, String type, int index) {
		
		super(X, Y, type);
		
		this.capacity = ModelConstants.CAPACITY_BUILDING;
		this.energyUse = this.tenants * 3;
		this.name = "B" + index;
		this.tenants = 0;
	}

	@Override
	public float getEnergy() {
		
		return ModelConstants.CONSUMPTION_BUILDING * tenants;
	}

}
