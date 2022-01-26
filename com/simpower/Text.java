package com.simpower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Text extends Actor {

	BitmapFont font;
	
	public Text(float scale) {
		font = new BitmapFont(Gdx.files.internal("myfont.fnt"), Gdx.files.internal("myfont.png"), false);
		font.setColor(0.5f, 1.0f, 1.0f, 1.0f);
		font.getData().setScale(scale);
		
	}
	
	public void draw(SpriteBatch batch, String string1, int coordx, int coordy) {
		font.draw(batch , string1, coordx, coordy);
	}
	
}
