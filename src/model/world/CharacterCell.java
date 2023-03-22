package model.world;

import model.characters.Character;

public class CharacterCell extends Cell {
	private Character character;
	private boolean isSafe;

	public CharacterCell() {
		super();
	}

	public CharacterCell(Character character) {
		super(true);
		this.character = character;
	}

	// Defensive Procedures in case of testing multiple argument constructors
	public CharacterCell(Character character, boolean isSafe) {
		super(true);
		this.character = character;
		this.isSafe = isSafe;
	}

	public CharacterCell(Character character, boolean isSafe, boolean isVisible) {
		super(isVisible);
		this.character = character;
		this.isSafe = isSafe;

	}

	public Character getCharacter() {
		return this.character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public boolean isSafe() {
		return isSafe;
	}

	public void setSafe(boolean isSafe) {
		this.isSafe = isSafe;
	}
}
