package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;

public class Medic extends Hero {
	public Medic(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
	}

	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException {
		if (this.getTarget().equals(null) || this.getTarget() instanceof Zombie)
			throw new InvalidTargetException("InvalidTargetException");
		super.useSpecial();
		if (this.getSupplyInventory().isEmpty())
			throw new NoAvailableResourcesException("NoAvailableResourcesException");
		this.getSupplyInventory().remove(0);
		this.getTarget().setCurrentHp(getMaxHp());

	}
}
