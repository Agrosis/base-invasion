package com.jantox.baseinvasion;

import java.awt.Graphics;

public class LumberPost extends Building {

	public LumberPost(double x, double y) {
		super(x, y, 20);
		this.sprite = Images.lumberpost;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(this.sprite, (int)x - 30 - View.x, (int)y - 47 - View.y, null);
	}

}
