package com.jantox.baseinvasion;

import java.awt.Graphics;

public class Slave extends Entity {
	
	public int rescar;
	
	int curanim;

	public Slave(double x, double y) {
		super(x+7, y+14, 6);
		curanim = 0;
		this.sprite = Images.slave.getSubimage(0, 0, 16, 16);
		
		Task.Goto t = new Task.Goto(this.getClosestEntity(new EntityFilter(EntityFilter.TREE)));
		t.init(this);
		this.setTask(t);
		this.status = 1;
		
		Resources.population++;
	}
	
	public void tick() {
		if(this.task != null) {
			if(this.task instanceof Task.Goto) {
				if(curanim != 0) {
					curanim = 0;
					this.sprite = Images.slave.getSubimage(curanim * 16, 0, 16, 16);
				}
				
				double dx = this.task.tx - this.x;
				double dy = this.task.ty - this.y;
				double angle = Math.atan2(dy, dx);
				
				double nx = x + Math.cos(angle) * 0.4 * 1;
				double ny = y + Math.sin(angle) * 0.4 * 1;
				
				x = nx;
				y = ny;
				
				if(this.collides(this.task.target)) {
					if(this.task.target instanceof Incomplete) {
						Task.Build t = new Task.Build(this.task.target);
						t.init(this);
					} else {
						Task.Collect t = new Task.Collect(this.task.target);
						t.init(this);
					}
				}
				
				if(!task.target.isAlive()) {
					Task.Goto t = new Task.Goto(this.getClosestEntity(new EntityFilter(EntityFilter.TREE)));
					t.init(this);
				}
				if(task.target.health != task.target.maxhealth) {
					Task.Goto t = new Task.Goto(this.getClosestEntity(new EntityFilter(EntityFilter.TREE), this.task.target));
					t.init(this);
				}
			} else if(this.task instanceof Task.Collect) {
				task.target.damage(20);
				if(!task.target.isAlive()) {
					Area.entities.remove(task.target);
					rescar = Resources.WOOD;
						
					((Task.Collect) task).setGathered();
				}
			} else if(this.task instanceof Task.Deposit) {
				if(curanim != 1) {
					curanim = 1;
					this.sprite = Images.slave.getSubimage(curanim * 16, 0, 16, 16);
				}
				
				double dx = this.task.tx - this.x;
				double dy = this.task.ty - this.y;
				double angle = Math.atan2(dy, dx);
				
				double nx = x + Math.cos(angle) * 0.4 * 1;
				double ny = y + Math.sin(angle) * 0.4 * 1;
				
				x = nx;
				y = ny;
				
				if(this.collides(task.target)) {
					((Task.Deposit) this.task).setDeposited();
				}
			} else if(this.task instanceof Task.Build) {
				((Incomplete)this.task.target).build();
				if(((Incomplete)this.task.target).progress >= 4000) { // done building
					((Task.Build) this.task).setBuilt();
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, (int)x - 7- View.x, (int)y -14- View.y, null);
		if(curanim == 1) {
			g.drawImage(Images.log, (int)x - 7- View.x, (int)y -23- View.y, null);
		}
	}

}
