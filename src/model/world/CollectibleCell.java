package model.world;

import model.collectibles.Collectible;

public class CollectibleCell extends Cell {
	private Collectible collectible; // Read Only

	public CollectibleCell() {
	}

	public CollectibleCell(Collectible collectible) {
		super();
		this.collectible = collectible;
	}

	// Defensive Procedures in case of testing multiple argument constructors
	public CollectibleCell(Collectible collectible, boolean isVisible) {
		super(isVisible);
		this.collectible = collectible;
	}

	public Collectible getCollectible() {
		return collectible;
	}
}
