package model.world;

public abstract class Cell {
	private boolean isVisible;

	public Cell() {
		// TODO Auto-generated constructor stub
	}

	public Cell(boolean isVisible) {
		super();
		this.isVisible = isVisible;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	

}
