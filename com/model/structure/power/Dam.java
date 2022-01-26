package com.model.structure.power;

import com.GameConstant.ModelConstants;
import com.model.Model;

/**
 * @brief define a dam
 * @author ang3d
 *
 */
public class Dam extends PowerPlant {
	
	
	//constructor
	public Dam(int X, int Y, String type, int index) {
		super(X, Y, type);
		
		this.capacity = ModelConstants.CAPACITY_DAM;
		this.name = "D" + index;
		this.tenants = 0;
		
	}
	
	//***********************************
	//Public overridden methods
	//***********************************
	
	//method that return the production of the plant if it is working 
	@Override
	public float getProd(Model game) {
		
		if(isWorking) {
			return this.production = ModelConstants.PRODUCTIVITY_DAM * this.tenants;
		}
		return 0;
		
	}

	//method that return the pollution generated by the plant
	@Override
	public float getPollution() {
		
		return ModelConstants.POLLUTION_DAM;
	}
	
	
	
	
	
}
