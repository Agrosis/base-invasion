package com.jantox.baseinvasion;

import java.awt.Graphics;

public class Incomplete extends Building {
	
	int progress;
	int change;
	
	int sind;
	
	public Incomplete(double x, double y, int building) {
		super(x, y, 20);
		progress = 0;
		sind = 0;
		this.sprite = Images.stonepost.getSubimage(sind, 0, 64, 64);
		
		change = building;
	}
	
	public void build() {
		progress += 4;
		if(progress % 500 == 0 && progress != 0) {
			if(progress <= 3500) {
				sind ++;
				this.sprite = Images.stonepost.getSubimage(sind * 64, 0, 64, 64);
			}
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(this.sprite, (int)x - 30 - View.x, (int)y - 47 - View.y, null);
	}

}
