package com.simpower;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class MainMenuScreen extends ScreenAdapter {
	
	SimPower game;
	private Stage stage;
	private int money;
	private String m;
	private SpriteBatch batch;
	private BitmapFont font;
	public MainMenuScreen(SpriteBatch batch, SimPower game) {
		this.batch = batch;
		this.game = game;
	}
	
	
	
	



	@Override
	public void show() {
		
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		
	    Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
		
		font = new BitmapFont(Gdx.files.internal("myfont.fnt"), Gdx.files.internal("myfont.png"), false);
		font.setColor(0.5f, 1.0f, 1.0f, 1.0f);
		font.getData().setScale(0.5f);
		
		
		Label title = new Label("SIMPOWER", mySkin, "big");
		title.setSize(10, 10);
		title.setPosition(Gdx.graphics.getWidth()/2, (Gdx.graphics.getHeight()/2) + 200);
		title.setAlignment(Align.center);
		title.setFontScale(1);
		stage.addActor(title);
		
		Button button1 = new Button(mySkin, "small");
		button1.setSize(200, 100);
		button1.setPosition(100, 100);
		button1.addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				game.setScreen(game.screen);
				return true;
			}
		});
		stage.addActor(button1);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,  0,  0,  0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		m = "money : " + this.money;
		//update();
		
		
		batch.begin();
		font.draw(batch, m, 500, 100);
		batch.end();
		
		stage.act();
		stage.draw();
		
	}
		
	public void update() {
		this.money += 1;
		m = "money : " + this.money;
	}
	
}
