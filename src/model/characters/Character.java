package model.characters;

import java.awt.Point;
import java.util.HashSet;

import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;

public  class Character { // Abstract class Again After testing
	private String name; // Read Only
	private Point location;
	private int maxHp, currentHp, attackDmg; // maxHp & attackDmg are READ only
	private HashSet<Point> adjCells = new HashSet<Point>();
	private Character target;

	public static void main(String[] args) {
		Character c = new Character();
		c.setLocation(new Point(9, 8));
		c.generateAdjCell();
		System.out.println(c.adjCells);
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
		if (this.target == null) {
			throw new InvalidTargetException("No target to attack");
		}
		if (!this.getAdjCells().contains(this.getTarget().getLocation())) {
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

	public HashSet<Point> getAdjCells() {
		return adjCells;
	}

	public void setAdjCells(HashSet<Point> adjCells) {
		this.adjCells = adjCells;
	}

	public void generateAdjCell() {
		this.adjCells.clear();
		this.addMainAdjCells();
		this.addDiagonaAdjlCells();
	}

	public void addMainAdjCells() {
		int x = location.x, y = location.y;
		for (int i = -1; i <= 1; i += 2) {
			if (x + i >= 0 && x + i < 15)
				this.adjCells.add(new Point(location.x + i, location.y));
			if (y + i >= 0 && y + i < 15)
				this.adjCells.add(new Point(location.x, location.y + i));

		}
	}

	public void addDiagonaAdjlCells() {
		int x = location.x, y = location.y;
		for (int i = -1; i <= 1; i += 2) {
			for (int j = -1; j <= 1; j += 2) {
				if (x + i >= 0 && x + i < 15 && y + j >= 0 && y + j < 15)
					this.adjCells.add(new Point(location.x + i, location.y + j));
			}
		}
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
		this.generateAdjCell();
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
