package model.characters;

public enum HeroType {
	FIGH, EXP, MED;

	public Hero generateHero(String name, int maxHp, int maxActions, int attackDmg) {
		switch (this) {
		case FIGH:
			return new Fighter(name, maxHp, attackDmg, maxActions);
		case MED:
			return new Medic(name, maxHp, attackDmg, maxActions);
		case EXP:
			return new Explorer(name, maxHp, attackDmg, maxActions);
		default:
			return null;
		}
	}
}
