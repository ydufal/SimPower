package com.simpower;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tile implements Comparable<Tile>{
	
	public Texture texture;
	public Vector2 tilemapPos;
	public Vector2 worldPos;
	
	public Tile(Texture texture, Vector2 tilemapPos, Vector2 worldPos) {
		this.texture = texture;
		this.tilemapPos = tilemapPos;
		this.worldPos = worldPos;
		
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(texture,  worldPos.x, worldPos.y);
	}

	@Override
	public int compareTo(Tile t) {

		return (int) ((t.tilemapPos.x*10 + t.tilemapPos.y) - (this.tilemapPos.x*10 + this.tilemapPos.y)) ;
	}
}
