package model.characters;

import java.awt.Point;
import java.util.ArrayList;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

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

	public void lightenAdjCells() {
		for (Point p : this.getAdjLocations()) {
			Game.map[p.x][p.y].setVisible(true);
		}
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

	public void move(Direction d) throws MovementException {
		Point currpos = this.getLocation();
		int currposX = currpos.x;
		int currposY = currpos.y;
		int newposX = 0;
		int newposY = 0;
		// if the cell isn't out-of grid
		if (newposX < 0 || newposX >= 15 || newposY < 0 || newposY >= 15) {
			throw new MovementException("You Can Not Move Out Of The Grid");
		}
		// if the Cell isn't occupied
		if ((Game.map[newposX][newposY] instanceof CharacterCell)
				&& ((CharacterCell) Game.map[newposX][newposY]).getCharacter() != null) {
			throw new MovementException("You Can Not Move into Occupied Cell");
		} else {
			switch (d) {
			case UP:
				newposX = currposX + 1;
				break;
			case DOWN:
				newposX = currposX - 1;
				break;
			case LEFT:
				newposY = currposY - 1;
				break;
			case RIGHT:
				newposY = currposY + 1;
				break;
			}
		}
		// if new Cell is Trap
		if (Game.map[newposX][newposY] instanceof TrapCell) {
			((TrapCell) Game.map[newposX][newposY]).applyDamage(this);
		}
		// if new Cell is collectible ( Vaccine or Supply)
		if (Game.map[newposX][newposY] instanceof CollectibleCell) {
			//if the Collectible is Vaccine
			if() {

				this.vaccineInventory.add();
			}
			//if the Collectible is Supply
			if() {
				this.supplyInventory.add();
			}

		}

		((CharacterCell) Game.map[currposX][currposY]).setCharacter(null);
		((CharacterCell) Game.map[newposX][newposY]).setCharacter(this);
		this.lightenAdjCells();
		// Game.map[newposX][newposY].setVisible(true);
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
