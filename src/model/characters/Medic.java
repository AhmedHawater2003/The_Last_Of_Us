package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;

public class Medic extends Hero {
	public Medic(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg, maxActions);
	}

	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException {
		if (this.getTarget().equals(null) || this.getTarget() instanceof Zombie || !this.targetIsAdj())
			throw new InvalidTargetException("InvalidTargetException");
		super.useSpecial();
		this.getTarget().setCurrentHp(this.getTarget().getMaxHp());

	}
}
