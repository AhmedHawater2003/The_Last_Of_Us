package model.collectibles;

import engine.Game;
import model.characters.Hero;
import model.characters.Zombie;

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
		Game.zombies.remove(curedZombie);
		newHero.addToControlable(curedZombie.getLocation());
	}

}
