package model.characters;

import java.awt.Point;
import engine.Game;
import exceptions.*;
import model.world.*;

public class Zombie extends Character {
	static int ZOMBIES_COUNT = 1;

	public Zombie() {
		super("Zombie " + ZOMBIES_COUNT, 40, 10);
		ZOMBIES_COUNT++;
	}

	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		setZombieTarget();
		if (this.getTarget() == null)
			return;
		super.attack();
	}

	public void setZombieTarget() {
		for (Point p : this.getAdjLocations()) {
			Cell atPoint = Game.map[p.x][p.y];
			if (atPoint instanceof CharacterCell) {
				if (((CharacterCell) atPoint).getCharacter() instanceof Hero) {
					this.setTarget(((CharacterCell) atPoint).getCharacter());
					return;
				}
			}
		}
	}

	public void removeFromGame() {
		Game.zombies.remove(this);
		ZOMBIES_COUNT--;
	}

}
