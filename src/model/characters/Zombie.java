package model.characters;

import java.awt.Point;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.Cell;
import model.world.CharacterCell;

public class Zombie extends Character {
	static int ZOMBIES_COUNT = 1;

	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT, 40, 10);
		ZOMBIES_COUNT++;
	}

	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		setZombieTarget();
//		System.out.println("My Traget is " + this.getTarget());
		if (this.getTarget() == null) {
//			System.out.println("Null have been found");
			return;
		}
		super.attack();
	}

	public void setZombieTarget() {
		this.generateAdjLocations();
		for (Point p : this.getAdjLocations()) {
			Cell atPoint = Game.map[p.x][p.y];
			if (atPoint instanceof CharacterCell) {
				if (((CharacterCell) atPoint).getCharacter() instanceof Hero) {
					this.setTarget(((CharacterCell) atPoint).getCharacter());
//					System.out.println("ZombieTargetHaveBeenSetten");
					break;
				}
			}
		}
	}

	public void removeFromGame() {

	}

}
