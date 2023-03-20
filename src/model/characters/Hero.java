package model.characters;
import java.awt.*; // Includes Point class
import java.util.*;
import model.collections.Supply;
import model.collections.Vaccine;

public class Hero extends Character {
	private int actionsAvailable;
	private int maxActions; //Read Only
	private boolean specialAction;
	private ArrayList <Vaccine>vaccineInventory[]; //Read Only
	private ArrayList <Supply>supplyInventory[];  //Read Only
	public Hero() {
		// TODO Auto-generated constructor stub
	}
	public Hero(String name, int maxHp, int attackDmg) {
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

	public void setMaxActions(int maxActions) {
		this.maxActions = maxActions;
	}

	public boolean isSpecialAction() {
		return specialAction;
	}

	public void setSpecialAction(boolean specialAction) {
		this.specialAction = specialAction;
	}

	public ArrayList<Vaccine>[] getVaccineInventory() {
		return vaccineInventory;
	}

	public void setVaccineInventory(ArrayList<Vaccine>[]vaccineInventory ) {
		this.vaccineInventory = vaccineInventory;
	}

	public ArrayList<Supply>[] getSupplyInventory() {
		return supplyInventory;
	}

	public void setSupplyInventory(ArrayList<Supply>[] supplyInventory) {
		this.supplyInventory = supplyInventory;
	}

}
