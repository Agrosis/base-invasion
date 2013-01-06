package com.jantox.baseinvasion;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Entity {

	double x, y;
	double radius;
	boolean selected;
	
	int health;
	int maxhealth;
	boolean alive;
	
	Task task;
	
	protected BufferedImage sprite = null;
	
	int status = 0;
	
	static Random random = new Random(8429);
	
	public Entity(double x, double y, double r) {
		this.x = x;
		this.y = y;
		this.radius = r;
		this.selected = false;
		
		this.task = null;
		this.alive = true;
		
		this.status = 0;
	}
	
	public void tick() {
		if(health <= 0)
			alive = false;
	}
	
	public void render(Graphics g) {
		
	}
	
	public boolean isAlive() {
		return alive;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMaxHealth() {
		return maxhealth;
	}
	
	public void damage(int h) {
		health -= h;
	}
	
	public void setTask(Task t) {
		this.task = t;
	}
	
	public double distance(Entity e) {
		return distance(e.x, e.y);
	}
	
	public double distance(double ex, double ey) {
		return Math.sqrt((ex - this.x) * (ex - this.x) + (ey - this.y) * (ey - this.y));
	}
	
	public boolean collides(Entity e) {
		return distance(e) <= (e.radius + radius);
	}
	
	public Entity getClosestEntity(EntityFilter ef) {
		double dist = 10000000;
		Entity e = null;
		
		for(int i = 0; i < Area.entities.size(); i++) {
			if(ef.accepts(Area.entities.get(i))) {
				if(distance(Area.entities.get(i)) < dist) {
					dist = distance(Area.entities.get(i));
					e = Area.entities.get(i);
				}
			}
		}
		
		return e;
	}
	
	public Entity getClosestEntity(EntityFilter ef, Entity exception) {
		double dist = 10000000;
		Entity e = null;
		
		for(int i = 0; i < Area.entities.size(); i++) {
			if(ef.accepts(Area.entities.get(i))) {
				if(distance(Area.entities.get(i)) < dist) {
					if(Area.entities.get(i) != exception) {
						dist = distance(Area.entities.get(i));
						e = Area.entities.get(i);
					}
				}
			}
		}
		
		return e;
	}
	
}
