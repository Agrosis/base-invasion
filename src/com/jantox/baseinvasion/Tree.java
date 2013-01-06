package com.jantox.baseinvasion;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Tree extends Entity {

	public Tree(double tx, double ty) {
		super(tx, ty, 8);
		this.sprite = Images.tree;
		this.health = this.maxhealth = 9000 + (int)(Math.random() * ((11000 - 9000) + 1));
	}

	public void tick() {
		super.tick();
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(this.sprite, (int)this.x - 8 - View.x, (int)this.y - 32 - View.y, null);
	}
	
}
