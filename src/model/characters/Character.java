package model.characters;

import java.awt.*; // Includes Point class

public abstract class Character {
	private String name; // Read Only
	private Point location;
	private int maxHp, currHp, attackDmg; // maxHp & attackDmg are READ only
	private Character target;

	public Character() {
		// TODO Auto-generated constructor stub
	}
	
	public Character(String name, int maxHp, int attackDmg) {
		this.name = name;
		this.maxHp = maxHp;
		this.attackDmg = attackDmg;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getCurrHp() {
		return currHp;
	}

	public void setCurrHp(int currHp) {
		this.currHp = currHp;
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