package engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.characters.Hero;
import model.characters.HeroType;
import model.characters.Zombie;
import model.world.Cell;

public class Game {

	public static Cell[][] map;
	public static ArrayList<Hero> availableHeroes = new ArrayList<Hero>();
	public static ArrayList<Hero> heroes = new ArrayList<Hero>();
	public static ArrayList<Zombie> zombies = new ArrayList<Zombie>();

	public static void loadHeroes(String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String row;
		while ((row = reader.readLine()) != null) {
			Hero newHero = heroDataParser(row.split(","));
			availableHeroes.add(newHero);
		}
		reader.close();

	}

	public static Hero heroDataParser(String[] row) throws EnumConstantNotPresentException {
		String name = row[0];
		HeroType type = HeroType.valueOf(row[1]);
		int maxHp = Integer.parseInt(row[2]);
		int maxActions = Integer.parseInt(row[3]);
		int attackDmg = Integer.parseInt(row[4]);

		return type.generateHero(name, maxHp, maxActions, attackDmg);

	}

}
