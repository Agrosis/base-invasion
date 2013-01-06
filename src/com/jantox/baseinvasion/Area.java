package com.jantox.baseinvasion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Area {
	
	static List<Entity> entities = new ArrayList<Entity>();
	
	//3114
	//3141
	//14159
	//660
	private Random random = new Random(660);

	public Area() {
		init();
	}
	
	// generates a random map
	private void init() {
		for(int i = 0; i < 20; i++) {
			double x = (random.nextDouble() * 256 + 20) * 5;
			double y = (random.nextDouble() * 256 - 10) * 5;
			addMines(x,y);
		}
		
		for(int i = 0; i < 140; i++) {
			double x = (random.nextDouble() * 256 + 20) * 5;
			double y = (random.nextDouble() * 256 - 10) * 5;
			addTrees(x,y);
		}
		
		TownHall b = new TownHall(400,300);
		LumberPost lp = new LumberPost(340,350);
		for(int i = 0; i < 10; i++) {
			double x = (random.nextGaussian() * 10 + 410 + i);
			double y = (random.nextGaussian() * 10 + 300 + i);
			Slave s = new Slave(x, y);
			if(isEmpty(s)) {
				entities.add(s);
			} else {
				Resources.population --;
			}
		}
		entities.add(b);
		entities.add(lp);
		
		entities.add(new EnemyBase(600, 1100));
		entities.add(new EnemyBase(650, 1150));
		entities.add(new EnemyBase(750, 1125));
		entities.add(new EnemyBase(1250, 875));
		entities.add(new EnemyBase(1290, 825));
		
		entities.add(new EnemyBase(975, 00));
		entities.add(new EnemyBase(1020, 40));
	}
	
	private void addTrees(double x, double y) {
		for(int i = 0; i < 500; i++) {
			double tx = x + random.nextGaussian() * 20;
			double ty = y + random.nextGaussian() * 20;
			
			Tree t = new Tree(tx,ty);
			
			if(isEmpty(t)) {
				entities.add(t);
			} else {
				t = null;
			}
		}
	}
	
	private void addMines(double x, double y) {
		for(int i = 0; i < 75; i++) {
			double tx = x + random.nextGaussian() * 6;
			double ty = y + random.nextGaussian() * 6;
			
			StoneMine sm = new StoneMine(tx,ty);
			
			if(isEmpty(sm)) {
				entities.add(sm);
			} else {
				sm = null;
			}
		}
		
		for(int i = 0; i < 200; i++) {
			double tx = x + random.nextGaussian() * 6;
			double ty = y + random.nextGaussian() * 6;
			
			GoldMine sm = new GoldMine(tx,ty);
			
			if(isEmpty(sm)) {
				entities.add(sm);
			} else {
				sm = null;
			}
		}
	}
	
	public boolean isEmpty(Entity e) {
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i).collides(e)) {
				return false;
			}
		}
		return true;
	}
	
	public int getInfluencePower(double x, double y, double radius) {
		
		return 0;
	}
	
}
