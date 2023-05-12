package model.world;

import java.util.Random;

import model.characters.Hero;

public class TrapCell extends Cell {
	@Override
	public String toString() {
		return "TrapCell [trapDamage=" + trapDamage + "]";
	}

	private int trapDamage; // Read Only

	public TrapCell() {
		this.trapDamage = getRandomDamage();
	}

	public int getTrapDamage() {
		return this.trapDamage;
	}

	private int getRandomDamage() {
		int PossibleDamage[] = { 10, 20, 30 };
		Random random = new Random();
		int randomIndex = random.nextInt(PossibleDamage.length);
		return PossibleDamage[randomIndex];
	}

	public void applyDamage(Hero h) {
		h.setCurrentHp(h.getCurrentHp() - this.getTrapDamage());
		if (h.getCurrentHp() <= 0) {
			h.onCharacterDeath();
		}
	}
}
