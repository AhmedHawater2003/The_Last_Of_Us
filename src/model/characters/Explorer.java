package model.characters;

import exceptions.NoAvailableResourcesException;

public class Explorer extends Hero {
	public Explorer(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
	}

	public void useSpecial() throws NoAvailableResourcesException {
		super.useSpecial();
	}
}
