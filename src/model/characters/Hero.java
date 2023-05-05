package model.characters;

import java.awt.Point;
import java.util.ArrayList;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.*;

public class Hero extends Character { // Should be abstract after testing
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
		if (this.getTarget() == null) {
			throw new InvalidTargetException("No target to attack");
		}

		if (this.actionsAvailable <= 0) {
			throw new NotEnoughActionsException("No actions available");
		}
		if (this.getTarget() instanceof Hero) {
			throw new InvalidTargetException("A hero cannot attack another hero");
		}
		super.attack();
		if (!(this instanceof Fighter && this.specialAction)) {
			this.actionsAvailable--;
		}
	}

	public void cure() throws NotEnoughActionsException, NoAvailableResourcesException, InvalidTargetException {
		if (this.getTarget() == null) {
			throw new InvalidTargetException("No target to cure");
		}
		if (this.actionsAvailable <= 0) {
			throw new NotEnoughActionsException("No actions available");
		}
		if (this.vaccineInventory.size() == 0) {
			throw new NoAvailableResourcesException("No vaccines available");
		}
		if (!(this.getAdjLocations().contains(this.getTarget().getLocation()))) {
			throw new InvalidTargetException("Target is not in range");
		}
		if (this.getTarget() instanceof Hero) {
			throw new InvalidTargetException("A hero cannot cure another hero");
		}
		
		// which vaccine to use?
		this.vaccineInventory.get(0).use(this);
		this.actionsAvailable--;

	}

	public void lightenAdjCells() {
		for (Point p : this.getAdjLocations()) {
			Game.map[p.x][p.y].setVisible(true);
		}
	}

	public void addToControlable(Point p) {
		Game.availableHeroes.remove(this);
		this.setLocation(p);
		((CharacterCell) Game.map[p.x][p.y]).setCharacter(this);
		Game.heroes.add(this);
		this.lightenAdjCells();
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

	public void move(Direction d) {
		// int currposX;
		// int currposY;
		int newposX = 0;
		int newposY = 0;
		// if the cell isn't occupied or out-of grid
		if (Game.map[newposX][newposY] != null && newposX < 0 || newposX >= 15 || newposY < 0 || newposY >= 15) {
			return;
		} else {
			switch (d) {
				case UP:
					newposY--;
					break;
				case DOWN:
					newposY++;
					break;
				case LEFT:
					newposX--;
					break;
				case RIGHT:
					newposX++;
					break;
			}
		}
		Game.map[newposX][newposY].setVisible(true);

		actionsAvailable--;
	}

	public void useSpecial() throws NoAvailableResourcesException {
		if (this.supplyInventory.isEmpty()) {
			throw new NoAvailableResourcesException("NoAvailableResourcesException");
		}
		this.supplyInventory.remove(0);
		this.setSpecialAction(true);
	}

}
