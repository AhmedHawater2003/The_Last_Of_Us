package model.characters;
import java.util.*;

import model.collectibles.Supply;
import model.collectibles.Vaccine;

public class Hero extends Character {
	private int actionsAvailable;
	private int maxActions; //Read Only
	private boolean specialAction;
	private ArrayList <Vaccine> vaccineInventory; //Read Only
	private ArrayList <Supply>  supplyInventory;  //Read Only
	public Hero() {
		// TODO Auto-generated constructor stub
	}
	public Hero(String name, int maxHp, int attackDmg,int maxActions) {
		super(name, maxHp, attackDmg);
		this.maxActions = maxActions; 
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
	public ArrayList<Supply>getSupplyInventory() {
		return supplyInventory;
	}

}
