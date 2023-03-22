package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import model.characters.Hero;
import model.characters.HeroType;
import model.characters.Zombie;
import model.world.Cell;

public class Game {

	public static ArrayList<Hero> availableHeroes = new ArrayList<Hero>();
	public static ArrayList<Hero> heroes = new ArrayList<Hero>();
	public static ArrayList<Zombie> zombies = new ArrayList<Zombie>();

	public static Cell[][] map;

	public Game() {
	}

	public static void loadHeroes(String filePath) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String row;
		while ((row = reader.readLine()) != null) {
			Hero newHero = heroDataParser(row.split(","));
			availableHeroes.add(newHero);
		}
		reader.close();
		// ! If a hero instance is null -> Incorrect hero type was given

	}

	public static Hero heroDataParser(String[] row) {
		String name = row[0];
		int maxHp = Integer.parseInt(row[2]);
		int maxActions = Integer.parseInt(row[3]);
		int attackDmg = Integer.parseInt(row[4]);

		HeroType type = HeroType.valueOf(row[1]);

		return type.generateHero(name, maxHp, maxActions, attackDmg);

	}

}
