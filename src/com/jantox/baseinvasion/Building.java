package com.jantox.baseinvasion;

import java.awt.Graphics;

public class Building extends Entity {

	public Building(double x, double y, double r) {
		super(x, y, 20);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		//g.drawImage(Images.townhall, (int)x - 30 - View.x, (int)y - 47 - View.y, null);
	}

}
