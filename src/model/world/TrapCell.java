package model.world;

import java.util.Random;

public class TrapCell extends Cell {
	private int trapDamage; // Read Only

	public TrapCell() {
		this.trapDamage = getRandomDamage();
	}

	// Defensive Procedures in case of testing multiple argument constructors
	public TrapCell(boolean isVisible) {
		super(isVisible);
		this.trapDamage = getRandomDamage();
	}

	public static void main(String[] args) {
		TrapCell test = new TrapCell();
		System.out.println(test.getTrapDamage());
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

}
