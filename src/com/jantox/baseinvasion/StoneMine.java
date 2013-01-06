package com.jantox.baseinvasion;

import java.awt.Graphics;

public class StoneMine extends Entity {

	public StoneMine(double x, double y) {
		super(x, y, 12);
		this.sprite = Images.stone;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, (int)x - 8 - View.x, (int)y - 13 - View.y, null);
	}

}
