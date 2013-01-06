package com.jantox.baseinvasion;

public class EntityFilter {

	public static final int TREE = 0;
	public static final int STONE_MINE = 1;
	public static final int GOLD_MINE = 2;
	public static final int TOWNHALL = 3;
	public static final int LUMBERPOST = 4;
	public static final int INCOMPLETE = 5;
	
	int filter;
	
	public EntityFilter(int i) {
		this.filter = i;
	}
	
	public boolean accepts(Entity e) {
		if(filter == TREE) {
			if(e instanceof Tree) {
				return true;
			}
		} else if(filter == STONE_MINE) {
			if(e instanceof StoneMine) {
				return true;
			}
		} else if(filter == GOLD_MINE) {
			if(e instanceof GoldMine) {
				return true;
			}
		} else if(filter == TOWNHALL) {
			if(e instanceof TownHall) {
				return true;
			}
		} else if(filter == LUMBERPOST) {
			if(e instanceof LumberPost) {
				return true;
			}
		} else if(filter == INCOMPLETE) {
			if(e instanceof Incomplete) {
				return true;
			}
		}
		return false;
	}
	
}
