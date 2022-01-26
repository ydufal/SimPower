package com.model;

public class Meteo {
	
	private boolean wind;
	private boolean rain;
	private boolean sun;
	
	
	public Meteo() {
		this.wind = false;
		this.rain = false;
		this.sun = false;
	}
	
	public void update() {
		
		double random = Math.random() * 100;
		
		if(random < 20) {
			this.rain = true;
			this.sun = false;
		}else if (random >= 20 && random < 40) {
			this.rain = false;
			this.sun = false;
		}else {
			this.rain = false;
			this.sun = true;
		}
		
		if(random < 50) {
			this.wind = true;
		}else {
			this.wind = false;
		}
	}
	
	
	public boolean getWind() {
		return this.wind;
	}
	public boolean getSun() {
		return this.sun;
	}
	public boolean getRain() {
		return this.rain;
	}
}
