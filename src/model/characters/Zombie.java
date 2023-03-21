package model.characters;

public class Zombie extends Character {
	static int ZOMBIES_COUNT;

	public Zombie() {// constructor to set the name of the zombie and his power
		super("Zombie " + (ZOMBIES_COUNT + 1), 40, 10);// i used the count +1 to start from 1 not zero
		ZOMBIES_COUNT++;
	}
}
