package engine;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import model.characters.Character;
import model.characters.Hero;
import model.characters.HeroType;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;

public class Game {

	public static Random GameRandom = new Random();
	public static Cell[][] map = new Cell[15][15];
	public static ArrayList<Hero> availableHeroes = new ArrayList<Hero>();
	public static ArrayList<Point> freeCellsLocations = new ArrayList<Point>();
	public static ArrayList<Point> deadCharactersLocations = new ArrayList<Point>();
	public static ArrayList<Hero> heroes = new ArrayList<Hero>();
	public static ArrayList<Zombie> zombies = new ArrayList<Zombie>();
	public static ArrayList<Zombie> zombiesTmpList = new ArrayList<Zombie>();

	public static void main(String[] args) throws IOException {
		loadHeroes("Heros.csv");
		startGame(availableHeroes.get(0));
	}

	public static int zombieCount() {
		int c = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] instanceof CharacterCell) {
					if (((CharacterCell) map[i][j]).getCharacter() instanceof Zombie) {
						c++;
					}
				}
			}
		}
		return c;
	}

	public static void endTurn() throws InvalidTargetException,
			NotEnoughActionsException {

		setAllCellsVisibility(false);
		for (Hero h : heroes) {
			h.resetVariables();
			h.generateAdjLocations();
			h.setAdjCellsVisiblity(true);
		}

		// ! Because the zombie might be killed and removed
		// ! from the list while traversing the list, which might
		// ! lead to an error

		//
		// for (Zombie z : zombiesTmpList) {
		// z.attack();
		// z.setTarget(null);
		// }

		Character.spawnZombie();

		freeCellsLocations.addAll(deadCharactersLocations);
		deadCharactersLocations.clear();

	}

	public static void head() {
		setAllCellsVisibility(false);
		for (Hero h : heroes) {
			h.resetVariables();
			h.generateAdjLocations();
			h.setAdjCellsVisiblity(true);
		}
		for (Zombie z : zombies)
			zombiesTmpList.add(z);
	}

	public static void tail() {
		Character.spawnZombie();
		freeCellsLocations.addAll(deadCharactersLocations);
		deadCharactersLocations.clear();
		zombiesTmpList.clear();
	}

	public static void setAllCellsVisibility(boolean isVisible) {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (map[i][j] == null) {
					map[i][j] = new CharacterCell(null);
				}
				map[i][j].setVisible(isVisible);
			}
		}
	}

	public static void startGame(Hero firstHero) throws IOException {
		clearingGameLists();
		preInitialization();
		randomSpawning();
		firstHero.addToControlable(new Point(0, 0));
	}

	public static void updateFreeCellsLocations() {
		freeCellsLocations.clear();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (map[i][j] == null) {
					map[i][j] = new CharacterCell(null);
				}
				boolean checkCollectible = map[i][j] instanceof CollectibleCell;
				boolean checkTrap = map[i][j] instanceof TrapCell;
				boolean checkCharacter = map[i][j] instanceof CharacterCell
						&& ((CharacterCell) map[i][j]).getCharacter() != null;
				if (checkCollectible || checkTrap || checkCharacter)
					freeCellsLocations.remove(new Point(i, j));
				else if (!deadCharactersLocations.contains(new Point(i, j))) {
					freeCellsLocations.add(new Point(i, j));
				}
			}
		}
	}

	public static void clearingGameLists() {
		// heroes.clear();
		// zombies.clear();
		freeCellsLocations.clear();
		deadCharactersLocations.clear();
	}

	public static void randomSpawning() {
		spwaningRandomZombies();
		spawningRandomSupplies();
		spawningRandomVaccines();
		spawningRandomTraps();
	}

	public static void spwaningRandomZombies() {
		for (int i = 0; i < 10; i++) {
			Point randomPoint = getAFreeCellLocation(); // handles removing the
														// point from the list
			Zombie newZombie = new Zombie();
			newZombie.setLocation(randomPoint);
			((CharacterCell) map[randomPoint.x][randomPoint.y])
					.setCharacter(newZombie);
			zombies.add(newZombie);
		}
	}

	public static void spawningRandomVaccines() {
		for (int i = 0; i < 5; i++) {
			Point randomPoint = getAFreeCellLocation();
			map[randomPoint.x][randomPoint.y] = new CollectibleCell(
					new Vaccine());
		}
	}

	public static void spawningRandomSupplies() {
		for (int i = 0; i < 5; i++) {
			Point randomPoint = getAFreeCellLocation();
			map[randomPoint.x][randomPoint.y] = new CollectibleCell(
					new Supply());
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

	public static Hero heroDataParser(String[] row)
			throws EnumConstantNotPresentException {
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

	public static boolean checkWin() {
		if (!AllVaccinesCollected())
			return false;
		if (CountControllableHeroes() >= 5 && AllVaccinesCollected()
				&& HeroUsedAllVaccines(heroes)) {
			return true;
		}
		return false;
	}

	public static boolean AllVaccinesCollected() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (Game.map[i][j] == null) {
					Game.map[i][j] = new CharacterCell(null);
				}
				if (map[i][j] instanceof CollectibleCell) {
					if (((CollectibleCell) Game.map[i][j]).getCollectible() instanceof Vaccine)
						return false;
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

	public static int CountControllableHeroes() {
		int c = 0;
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (map[i][j] instanceof CharacterCell
						&& (((CharacterCell) map[i][j]).getCharacter() instanceof Hero))
					c++;
			}
		}
		return c;
	}

	public static boolean checkGameOver() {
		if (CountControllableHeroes() == 0)
			return true;
		if (CountControllableHeroes() < 5 && AllVaccinesCollected()
				&& HeroUsedAllVaccines(heroes))
			return true;
		return false;
	}
}
