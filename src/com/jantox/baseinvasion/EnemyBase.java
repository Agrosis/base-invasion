package com.jantox.baseinvasion;

import java.awt.Graphics;

public class EnemyBase extends Building {

	public EnemyBase(double x, double y) {
		super(x, y, 20);
		this.sprite = Images.enemyb;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(this.sprite, (int)x - 30 - View.x, (int)y - 47 - View.y, null);
	}

}
