package com.jantox.baseinvasion;

public class Resources {

	public static final int WOOD = 0;
	public static final int STONE = 1;
	public static final int GOLD = 2;
	public static final int FOOD = 3;
	
	public static int wood = 20;
	public static int stone = 20;
	public static int gold = 20;
	public static int food = 20;
	
	public static int population = 0;
	public static int maxpop = 25;
	
	public static boolean canAfford(CostType cost) {
		if(wood >= cost.wood && stone >= cost.stone && gold >= cost.gold && food >= cost.food) {
			return true;
		}
		return false;
	}
	
	public static void purchase(CostType cost) {
		wood -= cost.wood;
		stone -= cost.stone;
		gold -= cost.gold;
		food -= cost.food;
	}
	
	public static void deposit(int type, int amount) {
		if(type == WOOD) {
			wood += amount;
		} else if(type == STONE) {
			stone += amount;
		} else if(type == GOLD) {
			gold += amount;
		} else if(type == FOOD) {
			food += amount;
		}
	}
	
}
