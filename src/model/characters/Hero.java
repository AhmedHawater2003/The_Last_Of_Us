package model.characters;

import java.util.ArrayList;

import exceptions.*;
import model.collectibles.Supply;
import model.collectibles.Vaccine;

public abstract class Hero extends Character {
	private int actionsAvailable;
	private int maxActions; // Read Only
	private boolean specialAction;
	private ArrayList<Vaccine> vaccineInventory; // Read Only
	private ArrayList<Supply> supplyInventory; // Read Only

	public Hero(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg);
		this.maxActions = maxActions;
		this.actionsAvailable = this.maxActions;
		this.vaccineInventory = new ArrayList<>();
		this.supplyInventory = new ArrayList<>();
	}

	public void attack() throws InvalidTargetException, NotEnoughActionsException {
		if (this.actionsAvailable <= 0) {
			throw new NotEnoughActionsException("No actions available");
		}
		if (this.getTarget() instanceof Hero) {
			throw new InvalidTargetException("A hero cannot attack another hero");
		}
		super.attack();
	}

	public int getActionsAvailable() {
		return actionsAvailable;
	}

	public void setActionsAvailable(int actionsAvailable) {
		this.actionsAvailable = actionsAvailable;
	}

	public int getMaxActions() {
		return maxActions;
	}

	public boolean isSpecialAction() {
		return specialAction;
	}

	public void setSpecialAction(boolean specialAction) {
		this.specialAction = specialAction;
	}

	public ArrayList<Vaccine> getVaccineInventory() {
		return vaccineInventory;
	}

	public ArrayList<Supply> getSupplyInventory() {
		return supplyInventory;
	}

}
