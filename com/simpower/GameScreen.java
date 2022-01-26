package com.simpower;

import java.util.Collections;
import com.GameConstant.GUIConstants;
import com.UI.ASHMenu;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.model.Model;
import com.model.habitant.People;
import com.model.structure.Structure;
import com.model.structure.centrale.Barrage;
import com.model.structure.centrale.Charbon;
import com.model.structure.centrale.Eolienne;
import com.model.structure.centrale.Nucleaire;
import com.model.structure.centrale.Solaire;
import com.model.structure.habitation.Immeuble;
import com.model.structure.habitation.Maison;


public class GameScreen extends ScreenAdapter {
	
	private Stage stage;
	private SpriteBatch batch;
	
	private ASHMenu user;
	
	private Model game;
	
	private Tilemap map;
	
	private Text money;
	private Text satisfaction;
	private Text production;
	private Text people;
	private Text pollution;
	private Text energyUsed;
	private Text wind;
	private Text rain;
	private Text sun;
	
	
	private Text instructions;
	
	private MenuFrame bandeau;
	
	private OrthographicCamera camera;
	
	private Texture texture;
	
	private String type;
	
	private boolean select;
	private boolean select2;
	private boolean select3;
	
	
	private int tile_x;
	private int tile_y;
	
	private int index;
	
	private float periodS;
	private float periodW;
	
	private float timeS;
	private float timeW;
	
	private String userChoice;
	private String tile;

	public GameScreen(SpriteBatch batch) {
		
		
		
		this.stage = new Stage(new ScreenViewport());
		this.batch = batch;
		
		this.game = new Model();
		
		this.user = new ASHMenu(this, this.game);
		
		this.map = new Tilemap();
		
		this.wind = new Text(0.8f);
		this.rain = new Text(0.8f);
		this.sun = new Text(0.8f);
		this.money = new Text(0.5f);
		this.satisfaction = new Text(0.5f);
		this.production = new Text(0.5f);
		this.people = new Text(0.5f);
		this.pollution = new Text(0.5f);
		this.energyUsed = new Text(0.5f);
		
		
		
		this.instructions = new Text(1);
		
		this.bandeau = new MenuFrame();
		
		this.camera = new OrthographicCamera(1280, 720);

		this.select = false;
		this.select2 = false;
		this.select3 = false;
		
		
		timeS = 0f;
		timeW = 0f;
		periodS = 1f;
		periodW = 5f;
		
		
		index = 1;
		
		this.tile = "";
			
	}
	
	@Override
	public void show() {
		
		Gdx.input.setInputProcessor(stage);
		
		camera.position.x = GUIConstants.CAMERA_INIT_POS_X;
		camera.position.y = GUIConstants.CAMERA_INIT_POS_Y;
		
		user.addActors(this.stage);
		
		stage.addActor(wind);
		stage.addActor(rain);
		stage.addActor(sun);
		stage.addActor(money);
		stage.addActor(satisfaction);
		stage.addActor(production);
		stage.addActor(people);
		stage.addActor(pollution);
		stage.addActor(energyUsed);
		stage.addActor(instructions);
		stage.addActor(bandeau);
		
		
		

		
	}

	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0,  0,  0,  0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		
		timeW += Gdx.graphics.getDeltaTime();
		timeS += Gdx.graphics.getDeltaTime();
	    if(timeW > periodW){
	        timeW-=periodW;
	        this.game.getWeather().update();
	        
	    }
	    
	    if(timeS > 2f) {
	    	timeS-=periodS;
	    	this.game.updateMoney();
	    	this.game.updateSatis();
	    	this.game.updateProd();
	    	this.game.updateEnergy();
	    	this.game.updatePeople();
	    	this.game.updatePollution();
	    }
	    
		if(select && !select3) {
			tileselection(texture, type);
		}
			
	    if(!select && !select3) {
	    	objectSelection();
	    }
	    
	    if(select3 && !select) {
	    	delete();
	    }
	    
		
		cameraInput();
		camera.update();
		
		
		
		
		batch.begin();
		
		map.render(batch);
		
		bandeau.draw(batch, 0, 670);
		
		stage.act();
		stage.draw();
		
		if(game.getWeather().getWind()) {
			wind.draw(batch, "Wind" , 50, 100);
		}
		if(game.getWeather().getRain()) {
			rain.draw(batch, "Rain" , 50, 50);
		}else if(game.getWeather().getSun()) {
			sun.draw(batch, "Sun" , 50, 50);
		}
		
		
		money.draw(batch, this.game.getMoney() + "$", 100, 700);
		satisfaction.draw(batch, this.game.getSatisfaction() + "%", 230, 700);
		production.draw(batch, this.game.getProduction() + "", 420, 700);
		people.draw(batch, this.game.getPeople() + "", 920, 700);
		pollution.draw(batch, this.game.getPollution() + "", 1100, 700);
		energyUsed.draw(batch, this.game.getEnergyUsed() + "", 1230, 700);
		
		
		if (select) {
			instructions.draw(batch, "Select a tile !", 500, 600);
		}
		
		if (select2) {
			user.getInformationMenu().getBackground().setVisible(true);
		}else {
			user.getInformationMenu().getBackground().setVisible(false);
		}
		
		if (select3) {
			instructions.draw(batch, "Click on the object you want to delete !", 300, 600);
		}
		
		
		batch.end();
		
		
	}
	
	private void cameraInput() {
		if(Gdx.input.isKeyPressed(Input.Keys.Q)) {
			camera.position.x -= GUIConstants.CAMERA_SPEED;
			
		} else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
			camera.position.x += GUIConstants.CAMERA_SPEED;
			
		} else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
			camera.position.y -= GUIConstants.CAMERA_SPEED;
			
		} else if(Gdx.input.isKeyPressed(Input.Keys.Z)) {
			camera.position.y += GUIConstants.CAMERA_SPEED;
			
		} else if(Gdx.input.isKeyPressed(Input.Keys.R)) {
			camera.position.x = GUIConstants.CAMERA_INIT_POS_X;
			camera.position.y = GUIConstants.CAMERA_INIT_POS_Y;
			camera.zoom = GUIConstants.CAMERA_INIT_ZOOM;
		}
		
		
	}
	
	private void tileselection(Texture texture, String type) {
		
		getMouseClick();
		
		if(tile_x <10 && tile_x >= 0 && tile_y < 10 && tile_y >= 0 && user.isPossible(this, this.game, this.tile_x, this.tile_y, this.tile)) {
			
			map.objects.add(new Tile(texture, new Vector2(tile_x, tile_y), new Vector2((tile_x-tile_y)*64/2F, (tile_x+tile_y)*32/2F)));
			
			switch(userChoice) {
			
			case "Building" : 	
				game.S.add(new Immeuble(tile_x, tile_y, type, index));
			break;
			
			case "House" :	
				game.S.add(new Maison(tile_x, tile_y, type, index));
			break;
			
			case "Nuclear" :	
				game.S.add(new Nucleaire(tile_x, tile_y, type, index));
			break;
			
			case "Coal" :	
				game.S.add(new Charbon(tile_x, tile_y, type, index));
			break;
			
			case "Solar" :	
				game.S.add(new Solaire(tile_x, tile_y, type, index));
			break;
			
			case "Wind" :	
				game.S.add(new Eolienne(tile_x, tile_y, type, index));
			break;
			
			case "DamRightOriented" :	
				game.S.add(new Barrage(tile_x, tile_y, type, index));
			break;
			
			case "DamLeftOriented" :	
				game.S.add(new Barrage(tile_x, tile_y, type, index));
			break;
				
			}
			
			game.getMap()[tile_x][tile_y] = 1;
			
			Collections.sort(map.objects); 
			
			select2 = false;
			select = false;
				
			user.getPowerMenuTable().getPowerPlantMenu().setVisible(false);
			user.getHouseMenu().getHousingMenuTable().setVisible(false);
				
			
			index += 1;
			if(game.S.get(game.S.size()-1).getSuperType() == "Housing") {
				game.setnbHousing(1);
			}else if(game.S.get(game.S.size()-1).getSuperType() == "Power") {
				game.setnbPower(1);
			}
			
			user.getWorkerMenu().updateStructure(game);
			
			
			
		} else {
			select = true;
		}
			
		
	}
	
	private void delete() {
		
		getMouseClick();
		String bType;
		if(tile_x <10 && tile_x >= 0 && tile_y < 10 && tile_y >= 0 && game.search(tile_x, tile_y) != -1) {
			bType = game.S.get(game.search(tile_x, tile_y)).getSuperType();
			select3 = false;
			
			
			
			user.getInformationMenu().update(game, tile_x, tile_y);
			
			user.getWorkerMenu().DeletePeopleStructure(game.S.get(game.search(tile_x, tile_y)).getName(), game);
			
			map.objects.remove(map.search(tile_x, tile_y));
			game.S.remove(game.search(tile_x, tile_y));
			
			game.getMap()[tile_x][tile_y] = 0;
			
			if(bType == "Housing") {
				game.setnbHousing(-1);
			}else if(bType == "Power") {
				game.setnbPower(-1);
			}
			
			user.getWorkerMenu().updateStructure(game);
			
		} else {
			
			select3 = true;
		
		}
		
	}
	
	private void objectSelection() {
		
		getMouseClick();
			
		if(tile_x <10 && tile_x >= 0 && tile_y < 10 && tile_y >= 0 && game.search(tile_x, tile_y) != -1) {
			
			select2 = true;		
			user.getInformationMenu().update(game, tile_x, tile_y);
			user.getInformationMenu().getInformationMenuTable().setVisible(true);
				
				
		} else {
			
			select2 = false;
			user.getInformationMenu().getInformationMenuTable().setVisible(false);
		}
		
	}
	
	private void getMouseClick() {
		
		if(Gdx.input.justTouched()) {
			Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(mousePos);
			
			float mapx = (mousePos.x/64 + mousePos.y/32)- 1.3f;
			float mapy = (mousePos.y/32 - mousePos.x/64)+ 0.7f;
			//System.out.println(mousePos.x + " : " + mousePos.y);
			
			tile_x = (int) mapx;
			tile_y = (int) mapy;
		}
	}
	
	public void setUserChoice(String userChoice) {
		this.userChoice = userChoice;
	}
	
	
	public boolean getSelect() {
		return this.select;
	}
	
	public void setSelect(boolean bool) {
		this.select = bool;
	}
	
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	

	public void setselect2(boolean b) {
		this.select2 = b;
		
	}
	
	public void setselect3(boolean b) {
		this.select3 = b;
		
	}

	public void setBuildType(String string) {
		this.type = string;
		
	}
	
	public Stage getStage() {
		return this.stage;
	}

	
	
	public void setTile(String s) {
		this.tile = s;
	}
	
	public Tilemap getTilemap() {
		return this.map;
	}
}
