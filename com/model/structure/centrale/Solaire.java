package com.model.structure.centrale;

import com.GameConstant.ModelConstants;
import com.model.Model;
import com.simpower.GameScreen;

public class Solaire extends PowerPlant {

	public Solaire(int X, int Y, String type, int index) {
		super(X, Y, type);
		
		this.capacity = ModelConstants.CAPACITY_SOLAIRE;
		this.name = "S" + index;
		this.tenants = 0;
	}

	@Override
	public float getProd(Model game) {
		
		if(game.getWeather().getSun()) {
			
			this.isWorking = true;
			
		}else {
			
			this.isWorking = false;
			
		}
		
		if(isWorking) {
			return this.production = ModelConstants.PRODUCTIVITY_SOLAIRE * this.tenants;
		}
		return 0;
		
	}

	@Override
	public float getEnergy() {
		
		return 0;
	}

	@Override
	public float getPollution() {
		
		return ModelConstants.POLLUTION_SOLAIRE;
	}
	
	
	
	
}
