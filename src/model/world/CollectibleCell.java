package model.world;

import model.collectibles.Collectible;

public class CollectibleCell extends Cell {
	@Override
	public String toString() {
		return "CollectibleCell [collectible=" + collectible + "]";
	}

	private Collectible collectible; // Read Only

	public CollectibleCell() {
	}

	public CollectibleCell(Collectible collectible) {
		super();
		this.collectible = collectible;
	}

	public Collectible getCollectible() {
		return collectible;
	}
}
