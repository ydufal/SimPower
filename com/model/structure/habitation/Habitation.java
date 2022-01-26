package com.model.structure.habitation;

import com.model.Model;
import com.model.structure.Structure;
import com.simpower.GameScreen;

public abstract class Habitation extends Structure{
	
	protected float energyUse;
	
	public Habitation(int X, int Y, String type) {
		
		super(X, Y, type);
		this.superType = "Housing";
		
	}

	@Override
	public float getProd(Model game) {
			
		return 0;
	}
	
	@Override
	public float getPollution() {
			
		return 0;
	}

	
	
}
