package com.model.structure.centrale;

import com.GameConstant.ModelConstants;
import com.model.Model;
import com.simpower.GameScreen;

public class Eolienne extends PowerPlant{

	public Eolienne(int X, int Y, String type, int index) {
		super(X, Y, type);
		this.capacity = ModelConstants.CAPACITY_EOLIENNE;
		this.name = "W" + index;
		this.tenants = 0;
	}

	@Override
	public float getProd(Model game) {
		
		if(game.getWeather().getWind()) {
			
			this.isWorking = true;
			
		}else {
			
			this.isWorking = false;
			
		}
		
		if(isWorking) {
			return this.production = ModelConstants.PRODUCTIVITY_EOLIENNE * this.tenants;
		}
		
		return 0;
		
	}

	@Override
	public float getEnergy() {
		
		return 0;
	}

	@Override
	public float getPollution() {
		
		return ModelConstants.POLLUTION_WINDTURBINE;
	}
	
	

}
