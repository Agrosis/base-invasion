package com.jantox.baseinvasion;

public class CostType {

	public int wood;
	public int stone;
	public int gold;
	public int food;
	
	public CostType(int a, int b, int c, int d) {
		this.wood = a;
		this.stone = b;
		this.gold = c;
		this.food = d;
	}
	
	public static CostType getCostOf(Building b) {
		if(b instanceof LumberPost) {
			return new CostType(15, 5, 0, 0);
		}
		
		return null;
	}
	
}
