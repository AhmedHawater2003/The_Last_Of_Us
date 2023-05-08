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

		// ? which vaccine to use?
		this.vaccineInventory.get(0).use(this);
		this.actionsAvailable--;

	}

	public void resetVariables() {
		this.setTarget(null);
		this.setActionsAvailable(this.maxActions);
		this.specialAction = false;
	}

	public void setAdjCellsVisiblity(boolean visible) {
		for (Point p : this.getAdjLocations()) {

			// ? Done to overcome testValidCureUpdate .. Is this Ok or I am missing up
			if (Game.map[p.x][p.y] == null) {
				Game.map[p.x][p.y] = new CharacterCell(null);
			}
			Game.map[p.x][p.y].setVisible(visible);
		}
	}

	public void addToControlable(Point p) {
		Game.availableHeroes.remove(this);
		this.setLocation(p);
		((CharacterCell) Game.map[p.x][p.y]).setCharacter(this);
		Game.heroes.add(this);
		this.setAdjCellsVisiblity(true);
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

	public void move(Direction d) throws MovementException, NotEnoughActionsException {
		if (this.actionsAvailable <= 0) {
			throw new NotEnoughActionsException();
		}
		if (this.getCurrentHp() <= 0) {
			this.onCharacterDeath();
			return;
		}
		Point currpos = this.getLocation();
		int currposX = currpos.x;
		int currposY = currpos.y;
		int newposX = currposX;
		int newposY = currposY;
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
		if (Game.map[newposX][newposY] == null) {
			Game.map[newposX][newposY] = new CharacterCell(null);
		}
		// if the cell is out-of grid
		if (newposX < 0 || newposX >= 15 || newposY < 0 || newposY >= 15) {
			throw new MovementException("You Can Not Move Out Of The Grid");
		}
		// if the Cell is occupied
		if ((Game.map[newposX][newposY] instanceof CharacterCell)
				&& ((CharacterCell) Game.map[newposX][newposY]).getCharacter() != null) {
			throw new MovementException("You Can Not Move into Occupied Cell");
		}
		// if new Cell is Trap
		if (Game.map[newposX][newposY] instanceof TrapCell) {
			((TrapCell) Game.map[newposX][newposY]).applyDamage(this);
			Game.map[newposX][newposY] = new CharacterCell(null);
		}
		// if new Cell is collectible
		if (Game.map[newposX][newposY] instanceof CollectibleCell) {
			((CollectibleCell) Game.map[newposX][newposY]).getCollectible().pickUp(this);
			Game.map[newposX][newposY] = new CharacterCell(null);

		}
		((CharacterCell) Game.map[currposX][currposY]).setCharacter(null);
		((CharacterCell) Game.map[newposX][newposY]).setCharacter(this);
		this.setLocation(new Point(newposX, newposY));
		this.setAdjCellsVisiblity(true);
		actionsAvailable--;
	}

	public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException {
		if (this.supplyInventory.isEmpty()) {
			throw new NoAvailableResourcesException("NoAvailableResourcesException");
		}
		this.supplyInventory.remove(0);
		this.setSpecialAction(true);
	}

}
