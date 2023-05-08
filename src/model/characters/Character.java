package model.characters;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
import model.world.CharacterCell;

public class Character { // Abstract class Again After testing
	private String name; // Read Only
	private Point location;
	private int maxHp, currentHp, attackDmg; // maxHp & attackDmg are READ only
	private HashSet<Point> adjLocations = new HashSet<Point>();
	private Character target;

	public static void main(String[] args) {
		Hero h = new Hero("h", 100, 10, 2);
		Hero h2 = new Hero("h", 100, 10, 2);
		Zombie z = new Zombie();
		z.setLocation(new Point(2, 2));
		h.setLocation(new Point(2, 1));
		h2.setLocation(new Point(1, 2));
		try {
			z.attack();
			System.out.println(h.getCurrentHp());
			System.out.println(h2.getCurrentHp());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Character(String name, int maxHp, int attackDmg) {
		this.name = name;
		this.maxHp = maxHp;
		this.attackDmg = attackDmg;
		this.currentHp = this.maxHp;
	}

	public Character() {
		// TODO Auto-generated constructor stub
	}

	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		if (!this.getAdjLocations().contains(this.getTarget().getLocation())) {
			throw new InvalidTargetException("Target is not in range");
		}
		this.target.setCurrentHp(this.target.getCurrentHp() - this.attackDmg);
		this.target.defend(this);
		if (this.target.getCurrentHp() <= 0) {
			this.target.onCharacterDeath();
			this.target = null;
		}

	}

	public void defend(Character c) {
		if (c.currentHp < this.attackDmg / 2) {
			c.currentHp = 0;
			c.onCharacterDeath();
		} else
			c.currentHp = c.currentHp - this.attackDmg / 2;

	}

	public void onCharacterDeath() {
		if (this.getCurrentHp() <= 0) {
			((CharacterCell) Game.map[location.x][location.y]).setCharacter(null);
			// Game.freeCellsLocations.add(this.getLocation());
			removeCharacter(this, Game.heroes, Game.zombies);
		}
	}

	public static void spawnZombie() {
		Point freeLocation = Game.getAFreeCellLocation();
		if (freeLocation != null) {
			Zombie spawnedZombie = new Zombie();
			Game.zombies.add(spawnedZombie);
			spawnedZombie.setLocation(freeLocation);
			((CharacterCell) Game.map[freeLocation.x][freeLocation.y]).setCharacter(spawnedZombie);
		}
	}

	public void removeCharacter(Character character, ArrayList<Hero> heroes, ArrayList<Zombie> zombies) {
		if (character instanceof Hero) {
			Game.freeCellsLocations.add(this.getLocation());
			((Hero) character).setAdjCellsVisiblity(false);
			heroes.remove((Hero) character);
		} else if (character instanceof Zombie) {
			Game.deadZombieLocations.add(this.getLocation());
			((Zombie) character).removeFromGame();
			spawnZombie();
		}
	}

	public void generateAdjLocations() {
		this.adjLocations.clear();
		this.adjLocations.add(this.location);
		this.addMainAdjLocations();
		this.addDiagonaAdjlCells();
	}

	public void addMainAdjLocations() {
		int x = location.x, y = location.y;
		for (int i = -1; i <= 1; i += 2) {
			if (x + i >= 0 && x + i < 15)
				this.adjLocations.add(new Point(location.x + i, location.y));
			if (y + i >= 0 && y + i < 15)
				this.adjLocations.add(new Point(location.x, location.y + i));

		}
	}

	public void addDiagonaAdjlCells() {
		int x = location.x, y = location.y;
		for (int i = -1; i <= 1; i += 2) {
			for (int j = -1; j <= 1; j += 2) {
				if (x + i >= 0 && x + i < 15 && y + j >= 0 && y + j < 15)
					this.adjLocations.add(new Point(location.x + i, location.y + j));
			}
		}
	}

	public HashSet<Point> getAdjLocations() {
		return adjLocations;
	}

	public void setAdjLocations(HashSet<Point> adjLocations) {
		this.adjLocations = adjLocations;
	}

	@Override
	public String toString() {
		return "Character [name=" + name + "]";
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
		this.generateAdjLocations();
	}

	public int getCurrentHp() {
		return currentHp;
	}

	public void setCurrentHp(int currentHp) {
		if (currentHp < 0)
			this.currentHp = 0;
		else if (currentHp > maxHp)
			this.currentHp = maxHp;
		else
			this.currentHp = currentHp;
	}

	public Character getTarget() {
		return target;
	}

	public void setTarget(Character target) {
		this.target = target;
	}

	public String getName() {
		return name;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getAttackDmg() {
		return attackDmg;
	}

}
