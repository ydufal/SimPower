package com.model.structure.centrale;

import com.GameConstant.ModelConstants;
import com.model.Model;
import com.simpower.GameScreen;

public class Charbon extends PowerPlant {

	public Charbon(int X, int Y, String type, int index) {
		super(X, Y, type);
		
		this.capacity = ModelConstants.CAPACITY_CHARBON;
		this.name = "C" + index;
		this.tenants = 0;
	}

	@Override
	public float getProd(Model game) {
		
		if(isWorking) {
			return this.production = ModelConstants.PRODUCTIVITY_CHARBON * this.tenants;
		}
		return 0;
		
	}

	@Override
	public float getEnergy() {
		return 0;
	}

	@Override
	public float getPollution() {
		
		return ModelConstants.POLLUTION_COAL;
	}
	
	
	
	
}
