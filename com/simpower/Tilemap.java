package com.simpower;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.GameConstant.GUIConstants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.model.structure.Structure;

public class Tilemap {
	
	public ArrayList<Tile> base;
	public ArrayList<Tile> objects;
	private Texture grass;
	private Texture water;
	private Texture uranium;
	public Texture charcoal;
	private FileHandle file;
	private String[][] map;
	
	
	
	public Tilemap() {
		
		grass = GUIConstants.GRASS_TEXTURE;
		water = GUIConstants.WATER_TEXTURE;
		charcoal = GUIConstants.COAL_TEXTURE;
		uranium = GUIConstants.URANIUM_TEXTURE;
	
		base = new ArrayList<Tile>();
		objects = new ArrayList<Tile>();
		map = new String[10][10];
		
		try {
			fillMap();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void render(SpriteBatch batch) {
		for(Tile tile : base) {
			
			tile.render(batch);
		}
		for(Tile tile : objects) {
			
			tile.render(batch);

		}
	}
	
	public void fillMap() throws IOException {
		
		String[][] mapAlt = {
				{"g", "w", "g", "g", "g", "g", "g", "g", "g", "g"},
				{"g", "w", "w", "w", "w", "g", "g", "c", "g", "c"},
				{"g", "g", "g", "g", "w", "g", "g", "g", "g", "g"},
				{"g", "g", "g", "g", "w", "g", "g", "g", "g", "g"},
				{"g", "g", "g", "g", "w", "w", "g", "g", "g", "u"},
				{"g", "g", "g", "g", "g", "w", "g", "g", "g", "g"},
				{"g", "g", "g", "g", "g", "w", "g", "c", "g", "g"},
				{"g", "u", "u", "g", "g", "w", "g", "g", "g", "g"},
				{"g", "g", "g", "g", "g", "w", "w", "g", "g", "g"},
				{"g", "g", "g", "g", "g", "g", "w", "g", "g", "g"}	
		};
		
		
		int i = 0;
		for(String[] tab : mapAlt) {
			
			this.map[i] = tab;
			
			System.out.print(map[0][i] + " ");
			i++;
		}
		
		
		/*
		file = Gdx.files.internal("map.txt");
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file.path()));
		String s = "";
		int count = 0;
		while((s = bufferedReader.readLine()) != null) {
			
			map[count] = s.split(" ");
			count++;
			System.out.println(count);
		}
		bufferedReader.close();
		*/
		
		
		
		for(int row = 9; row >= 0; row--) {
			for(int col = 9; col >= 0; col--) {
				
				float x = (row - col) * 64 / 2F; 
				float y = (col + row) * 32 / 2F;
				
				if(map[row][col].equals("g")) {
					base.add(new Tile(grass, new Vector2(row, col), new Vector2(x, y)));
				} else if(map[row][col].equals("w")) {
					base.add(new Tile(water, new Vector2(row, col), new Vector2(x, y)));
				} else if(map[row][col].equals("c")){
					base.add(new Tile(charcoal, new Vector2(row, col), new Vector2(x, y)));
				} else if(map[row][col].equals("u")) {
					base.add(new Tile(uranium, new Vector2(row, col), new Vector2(x, y)));
				}
			}
		}
		
	
	}
	
	public int search(int x, int y) {
		
		int index = -1;
		for (Tile t : objects) {
			index += 1;
			if(t.tilemapPos.x == x && t.tilemapPos.y == y) {
				return index;
			}
		}
		index = -1;
		
		return index;
	}
	
	public String[][] getMap(){
		return this.map;
	}
	
	
}
