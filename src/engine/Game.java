package engine;

import java.util.*;
import java.io.*;
import java.awt.*;
import exceptions.*;
import model.characters.*;
import model.collectibles.*;
import model.world.*;

public class Game {

	public static ArrayList<Hero> availableHeroes = new ArrayList<Hero>();
	public static ArrayList<Hero> heroes = new ArrayList<Hero>();
	public static ArrayList<Hero> zombies = new ArrayList<Hero>();

	public static Cell[][] map;

	public Game() {
	}

	public static void loadHeroes(String filePath) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String row;
		while ((row = reader.readLine()) != null) {
			availableHeroes.add(heroDataParser(row.split(",")));
		}
		reader.close();
		// ! If a hero instance is null -> Incorrect hero type was given
	}

	public static Hero heroDataParser(String[] row) {
		String name = row[0];
		HeroType type = HeroType.valueOf(row[1]);
		int maxHp = Integer.parseInt(row[2]);
		int maxActions = Integer.parseInt(row[3]);
		int attackDmg = Integer.parseInt(row[4]);
		return heroTypeDetector(type, name, maxHp, maxActions, attackDmg);
	}

	public static Hero heroTypeDetector(HeroType type, String name, int maxHp, int maxActions, int attackDmg) {
		Hero hero;
		switch (type) {
		case FIGH:
			hero = new Fighter(name, maxHp, attackDmg, maxActions);
			break;
		case MED:
			hero = new Medic(name, maxHp, attackDmg, maxActions);
			break;
		case EXP:
			hero = new Explorer(name, maxHp, attackDmg, maxActions);
			break;
		default :
			hero = null;
		}
		return hero;
	}

}
