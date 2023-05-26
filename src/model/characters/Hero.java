package model.characters;

import java.awt.Point;
import java.util.ArrayList;

import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

public abstract class Hero extends Character {
	private int actionsAvailable;
	private int maxActions; // Read Only
	private boolean specialAction;
	private ArrayList<Vaccine> vaccineInventory; // Read Only
	private ArrayList<Supply> supplyInventory; // Read Only
	public boolean isTrapped; // ! Should be private
	public int damgeTaken;

	public Hero(String name, int maxHp, int attackDmg, int maxActions) {
		super(name, maxHp, attackDmg);
		this.maxActions = maxActions;
		this.actionsAvailable = this.maxActions;
		this.vaccineInventory = new ArrayList<>();
		this.supplyInventory = new ArrayList<>();

	}

	public void attack() throws InvalidTargetException,
			NotEnoughActionsException {
		if (this.getTarget() == null) {
			throw new InvalidTargetException("No target to attack");
		}

		if (this.actionsAvailable <= 0) {
			throw new NotEnoughActionsException("No actions available");
		}
		if (this.getTarget() instanceof Hero) {
			throw new InvalidTargetException(
					"A hero cannot attack another hero");
		}
		super.attack();
		if (!(this instanceof Fighter && this.specialAction)) {
			this.actionsAvailable--;
		}
	}

	public void cure() throws NotEnoughActionsException,
			NoAvailableResourcesException, InvalidTargetException {
		if (this.getTarget() == null) {
			throw new InvalidTargetException("No target to cure");
		}
		if (this.actionsAvailable <= 0) {
			throw new NotEnoughActionsException("No actions available");
		}
		if (this.vaccineInventory.isEmpty()) {
			throw new NoAvailableResourcesException("No vaccines available");
		}
		if (!this.targetIsAdj()) {
			throw new InvalidTargetException("Target is not in range");
		}
		if (this.getTarget() instanceof Hero) {
			throw new InvalidTargetException("A hero cannot cure another hero");
		}

		this.vaccineInventory.get(0).use(this);
		this.setTarget(null); // M3
		this.actionsAvailable--;

	}

	public void resetVariables() {
		this.setTarget(null);
		this.setActionsAvailable(this.maxActions);
		this.specialAction = false;
	}

	public void setAdjCellsVisiblity(boolean visible) {
		for (Point p : this.getAdjLocations()) {

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

	public void applyDamgeTaken() {
		this.setCurrentHp(this.getCurrentHp() - damgeTaken);
		damgeTaken = 0;
		isTrapped = false;
		if (this.getCurrentHp() <= 0) {
			this.onCharacterDeath();
		} else
			this.setAdjCellsVisiblity(true);
	}

	public void move(Direction d) throws MovementException,
			NotEnoughActionsException {
		if (this.actionsAvailable <= 0) {
			throw new NotEnoughActionsException(
					"Fuuuuckkk youuuuu Mafeesh khra action point ya a3maaaa");
		}
		if (this.getCurrentHp() <= 0) {
			this.onCharacterDeath();
			return;
		}
		Point currpos = this.getLocation();
		int newposX = currpos.x;
		int newposY = currpos.y;

		switch (d) {
		case UP:
			newposX = currpos.x + 1;
			break;
		case DOWN:
			newposX = currpos.x - 1;
			break;
		case LEFT:
			newposY = currpos.y - 1;
			break;
		case RIGHT:
			newposY = currpos.y + 1;
			break;
		}

		// if the cell is out-of grid
		if (newposX < 0 || newposX >= 15 || newposY < 0 || newposY >= 15) {
			throw new MovementException("You Can Not Move Out Of The Grid");
		}

		if (Game.map[newposX][newposY] == null) {
			Game.map[newposX][newposY] = new CharacterCell(null);
		}

		// if the Cell is occupied
		if ((Game.map[newposX][newposY] instanceof CharacterCell)
				&& ((CharacterCell) Game.map[newposX][newposY]).getCharacter() != null) {
			throw new MovementException("You Can Not Move into Occupied Cell");
		}
		// if new Cell is Trap
		if (Game.map[newposX][newposY] instanceof TrapCell) {
			TrapCell trap = ((TrapCell) Game.map[newposX][newposY]);
			this.isTrapped = true;
			this.damgeTaken = trap.getTrapDamage();
			// ((TrapCell) Game.map[newposX][newposY]).applyDamage(this);
		}
		// if new Cell is collectible
		if (Game.map[newposX][newposY] instanceof CollectibleCell) {
			((CollectibleCell) Game.map[newposX][newposY]).getCollectible()
					.pickUp(this);
		}

		actionsAvailable--;
		((CharacterCell) Game.map[currpos.x][currpos.y]).setCharacter(null);
		Game.map[newposX][newposY] = new CharacterCell(this);
		this.setLocation(new Point(newposX, newposY));

		if (this.getCurrentHp() <= 0) {
			this.onCharacterDeath();
		} else
			this.setAdjCellsVisiblity(true);

	}

	public void useSpecial() throws NoAvailableResourcesException,
			InvalidTargetException {
		if (this.supplyInventory.isEmpty()) {
			throw new NoAvailableResourcesException(
					"NoAvailableResourcesException");
		}
		this.supplyInventory.remove(0);
		this.setSpecialAction(true);
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
