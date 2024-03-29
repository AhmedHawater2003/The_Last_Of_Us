package model.world;

import model.characters.Character;

public class CharacterCell extends Cell {
	private Character character;
	private boolean isSafe;

	public CharacterCell(Character character) {
		this.character = character;
	}

	public CharacterCell(Character character, boolean isSafe) {
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

	@Override
	public String toString() {
		return "CharacterCell [character=" + character + "]";
	}

	public void setSafe(boolean isSafe) {
		this.isSafe = isSafe;
	}
}
