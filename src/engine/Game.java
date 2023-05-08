package engine;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import model.characters.Hero;
import model.characters.HeroType;
import model.characters.Zombie;
import model.collectibles.Collectible;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

public class Game {

	public static Random GameRandom = new Random();
	public static Cell[][] map = new Cell[15][15];
	public static ArrayList<Hero> availableHeroes = new ArrayList<Hero>();
	public static ArrayList<Point> freeCellsLocations = new ArrayList<Point>();
	public static ArrayList<Point> deadZombieLocations = new ArrayList<Point>();
	public static ArrayList<Hero> heroes = new ArrayList<Hero>();
	public static ArrayList<Zombie> zombies = new ArrayList<Zombie>();

	public static void main(String[] args) throws IOException {
		loadHeroes("Heros.csv");
		startGame(availableHeroes.get(0));

		/*
		 * Test 1 Character z = ((CharacterCell)map[6][2]).getCharacter(); Character h =
		 * ((CharacterCell)map[1][2]).getCharacter(); Character h2 =
		 * ((CharacterCell)map[2][1]).getCharacter(); try {
		 * System.out.println(z.getCurrentHp()); h.setTarget(z); h2.setTarget(z);
		 * h.attack(); h2.attack(); System.out.println(z.getCurrentHp()); } catch
		 * (Exception e) { System.out.println(e.getMessage()); }
		 */

		/*
		 * Test 2 System.out.println(availableHeroes);
		 * System.out.println(heroes.get(0).getLocation());
		 * System.out.println(((CharacterCell) map[0][0]).getCharacter()); for (int i =
		 * 0; i < map.length; i++) { for (int j = 0; j < map.length; j++) {
		 * System.out.println("( " + i + ", " + j + " )" + " -> " + map[i][j]); } }
		 */

	}

	public static void endTurn() {

	}

	public static boolean checkGameOver() {
		return true;
	}

	public static void startGame(Hero firstHero) throws IOException {
		preInitialization();
		randomSpawning();
		// Zombie z = new Zombie();
		// z.setLocation(new Point(6, 2));
		// ((CharacterCell) map[6][2]).setCharacter(z);
		// Hero h = new Hero("h", 100, 10, 10);
		// h.setLocation(new Point(1, 2));
		// ((CharacterCell) map[1][2]).setCharacter(h);
		// Hero h2 = new Hero("h2", 100, 10, 10);
		// h2.setLocation(new Point(2, 1));
		// ((CharacterCell) map[2][1]).setCharacter(h2);
		firstHero.addToControlable(new Point(0, 0));
	}

	public static void randomSpawning() {
		spwaningRandomZombies();
		spawningRandomSupplies();
		spawningRandomVaccines();
		spawningRandomTraps();
	}

	public static void spwaningRandomZombies() {
		for (int i = 0; i < 10; i++) {
			Point randomPoint = getAFreeCellLocation(); // handles removing the point from the list
			Zombie newZombie = new Zombie();
			newZombie.setLocation(randomPoint);
			((CharacterCell) map[randomPoint.x][randomPoint.y]).setCharacter(newZombie);
			zombies.add(newZombie);
		}
	}

	public static void spawningRandomVaccines() {
		for (int i = 0; i < 5; i++) {
			Point randomPoint = getAFreeCellLocation();
			map[randomPoint.x][randomPoint.y] = new CollectibleCell(new Vaccine());
		}
	}

	public static void spawningRandomSupplies() {
		for (int i = 0; i < 5; i++) {
			Point randomPoint = getAFreeCellLocation();
			map[randomPoint.x][randomPoint.y] = new CollectibleCell(new Supply());
		}
	}

	public static void spawningRandomTraps() {
		for (int i = 0; i < 5; i++) {
			Point randomPoint = getAFreeCellLocation();
			map[randomPoint.x][randomPoint.y] = new TrapCell();
		}
	}

	// assign each cell to a new CharacterCell object and populate
	// freeCellsLocations
	public static void preInitialization() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				map[i][j] = new CharacterCell(null);
				if (!(i == 0 && j == 0))
					freeCellsLocations.add(new Point(i, j));
			}
		}
	}

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

	public static Point getAFreeCellLocation() {
		if (freeCellsLocations.isEmpty())
			return null;
		int randomIndex = GameRandom.nextInt(freeCellsLocations.size());
		Point location = freeCellsLocations.remove(randomIndex);
		return location;
	}

	public static Hero getAnAvailaveHero() {
		if (availableHeroes.isEmpty())
			return null;
		int randomIndex = GameRandom.nextInt(availableHeroes.size());
		Hero hero = availableHeroes.remove(randomIndex);
		return hero;
	}

	// public static boolean checkWin() {
	// if (heroes.size() >= 5 && AllVaccineUsed()) {
	// return true;
	// }
	// return false;
	// }

	public static boolean checkWin() {
		if (heroes.size() >= 5 && AllVaccinesCollected() && HeroUsedAllVaccines(heroes)) {
			return true;
		}
		return false;
	}

	public static boolean AllVaccinesCollected() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (Game.map[i][j] == null) {
					Game.map[i][j] = new CharacterCell(null);
				}
				if (map[i][j] instanceof Collectible) {
					if ((Collectible) Game.map[i][j] instanceof Vaccine)
						return false;
					return true;
				}
			}
		}
		return true;
	}

	public static boolean HeroUsedAllVaccines(ArrayList<Hero> heroes) {
		for (Hero hero : heroes) {
			if (!hero.getVaccineInventory().isEmpty())
				return false;
		}
		return true;
	}
}
