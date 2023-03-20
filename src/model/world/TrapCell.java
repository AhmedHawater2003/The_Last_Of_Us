package model.world;
import java.util.Random;
public class TrapCell extends Cell {
	public TrapCell() {
		// TODO Auto-generated constructor stub
	}

	public TrapCell(boolean isVisible) {
		super(isVisible);
		this.trapDamage = trapDamage;
	}

	public int getTrapDamage() {
		return trapDamage;
	}

	public void setTrapDamage(int trapDamage) {
		this.trapDamage = trapDamage;
	}
	 public int getRandomDamage() {
		int PossibleDamage [] = {10,20,30};
		Random random = new Random();
		int randomIndex = random.nextInt(PossibleDamage.length);
		return PossibleDamage[randomIndex];
	 }
	 
}
