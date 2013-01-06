package com.jantox.baseinvasion;

import java.awt.Graphics;

public class GoldMine extends Entity {

	public GoldMine(double x, double y) {
		super(x, y, 8);
		this.sprite = Images.gold;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, (int)x - 8 - View.x, (int)y - 13 - View.y, null);
	}

}
