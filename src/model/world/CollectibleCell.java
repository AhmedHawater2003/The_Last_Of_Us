package model.world;
import model.collections.Collectible;

public class CollectibleCell extends Cell {
	private Collectible collectible; //Read Only

	public CollectibleCell() {
		// TODO Auto-generated constructor stub
	}

	public CollectibleCell(Collectible collectible) {
		super();
		this.collectible = collectible;
	}

	public Collectible getCollectible() {
		return collectible;
	}
}
