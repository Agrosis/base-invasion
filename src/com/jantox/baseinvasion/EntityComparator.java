package com.jantox.baseinvasion;

import java.util.Comparator;

public class EntityComparator implements Comparator<Entity> {

	@Override
	public int compare(Entity a, Entity b) {
		return ((a.y > b.y) ? 1 : 0);
	}

}
