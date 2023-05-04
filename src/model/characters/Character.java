package model.characters;

import java.awt.Point;
import java.util.HashSet;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;

public class Character { // Abstract class Again After testing
	private String name; // Read Only
	private Point location;
	private int maxHp, currentHp, attackDmg; // maxHp & attackDmg are READ only
	private HashSet<Point> adjLocations = new HashSet<Point>();
	private Character target;

	public static void main(String[] args) {
		// Hero h = new Hero("h", 100, 10, 2);
		// Zombie z = new Zombie();
		// h.setTarget(z);
		// try{
		// h.attack();
		// System.out.println(z.getCurrentHp());
		// }
		// catch(Exception e){
		// System.out.println(e.getMessage());
		// }
		Character c = new Character();
		c.setLocation(new Point(3, 3));
		c.generateAdjLocations();

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
		if (this.target.getCurrentHp() == 0)
			this.target.onCharacrerDeath();

		this.target = null;
	}

	public void defend(Character attacker) {
		// TODO Auto-generated method stub

	}

	public void onCharacrerDeath() {
		// TODO Auto-generated method stub

	}

	public HashSet<Point> getAdjLocations() {
		return adjLocations;
	}

	public void setAdjLocations(HashSet<Point> adjLocations) {
		this.adjLocations = adjLocations;
	}

	public void generateAdjLocations() {
		this.adjLocations.clear();
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
