package model.collectibles;

import java.awt.Point;

import engine.Game;
import model.characters.Hero;
import model.characters.Zombie;
import model.world.CharacterCell;

public class Vaccine implements Collectible {

	public Vaccine() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pickUp(Hero h) {
		h.getVaccineInventory().add(this);

	}

	@Override
	public void use(Hero h) {
		h.getVaccineInventory().remove(this);
		Hero newHero = Game.getAnAvailaveHero();
		Zombie curedZombie = (Zombie) h.getTarget();
		Point p = curedZombie.getLocation();
		Game.zombies.remove(curedZombie);
		Game.availableHeroes.remove(newHero);
		newHero.setLocation(p);
		((CharacterCell) Game.map[p.x][p.y]).setCharacter(newHero);
		Game.heroes.add(newHero);

	}

	public String toString(){
		return "V";
	}

}
