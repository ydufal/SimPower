package com.model.structure.centrale;

import com.GameConstant.ModelConstants;
import com.model.Model;
import com.simpower.GameScreen;

public class Nucleaire extends PowerPlant{

	public Nucleaire(int X, int Y, String type, int index) {
		super(X, Y, type);
		this.name = ("");
		this.capacity = ModelConstants.CAPACITY_NUCLEAIRE;
		this.name = "N" + index;
		this.tenants = 0;
	}

	@Override
	public float getProd(Model game) {
		
		if(isWorking) {
			return this.production = ModelConstants.PRODUCTIVITY_NUCLEAIRE * this.tenants;
		}
		
		return 0;
	}

	@Override
	public float getEnergy() {
		return 0;
	}

	@Override
	public float getPollution() {
		
		return ModelConstants.POLLUTION_NUCLEAIRE;
	}
	
	
	
}
