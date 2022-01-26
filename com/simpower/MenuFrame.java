package com.simpower;


import com.GameConstant.UIConstants;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MenuFrame extends Actor{
	
	private Texture frame;
	
	
	public MenuFrame() {
		
		frame = UIConstants.BANDEAU_MENU_TEXTURE;
		
	}
	
	public void draw(SpriteBatch batch, int coordx, int coordy) {
		batch.draw(frame, coordx, coordy);
	}
}
