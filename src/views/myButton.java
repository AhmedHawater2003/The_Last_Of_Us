package views;

import java.util.HashMap;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import model.characters.Hero;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;

public class myButton extends Button {
	Cell cell;
	static HashMap<String, Image> icons = new HashMap<String, Image>();
	// iconsDict();
	public static final double WIDTH = Screen.getPrimary().getBounds()
			.getWidth();
	public static final double HEIGHT = Screen.getPrimary().getBounds()
			.getHeight();

	public static double mapWidth = WIDTH - WIDTH / 6, mapHeight = HEIGHT
			- HEIGHT / 15;

	public static void loadingIconsDict() {
		icons.put("Ellie Williams", new Image("views\\ellie.png"));
		icons.put("Joel Miller", new Image("views\\joel.png"));
		icons.put("Tess", new Image("views\\tess.png"));
		icons.put("Riley Abel", new Image("views\\riley.png"));
		icons.put("Tommy Miller", new Image("views\\tommy.png"));
		icons.put("Bill", new Image("views\\bill.png"));
		icons.put("David", new Image("views\\david.png"));
		icons.put("Henry Burell", new Image("views\\henry.png"));
		icons.put("zombie", new Image("views\\zombie.png"));
		icons.put("supply", new Image("icons\\supply.png"));
		icons.put("vaccine", new Image("icons\\vaccine.png"));
		icons.put("trap", new Image("views\\assasin.png"));
	}

	public myButton(Cell cell) {
		this.cell = cell;
		this.setPrefSize(mapWidth / 15, mapHeight / 15);
		this.updateButtonView();
	}

	public void updateButtonView() {
		// if (!cell.isVisible()) {
		// this.setEffect(new javafx.scene.effect.GaussianBlur(10.5));
		// this.setId("blured");
		// } else {

		if (cell instanceof CharacterCell) {
			if (((CharacterCell) cell).getCharacter() instanceof Hero) {
				asHero();
			} else if (((CharacterCell) cell).getCharacter() instanceof Zombie) {
				asZombie();
			} else {
				asEmpty();
			}

		} else if (cell instanceof CollectibleCell) {
			if (((CollectibleCell) cell).getCollectible() instanceof Supply) {
				asSupply();
			} else {
				asVaccine();
			}
		} else {
			asTrap();
		}
	}

	// }

	public ImageView getImageView(Image path) {
		ImageView view = new ImageView(path);
		view.setFitHeight(mapHeight / 25);
		view.setFitWidth(mapWidth / 25);
		return view;
	}

	public void asHero() {
		String name = (((CharacterCell) cell).getCharacter()).getName();
		ImageView view = getImageView(icons.get(name));
		this.setGraphic(view);
	}

	public void asZombie() {
		ImageView view = getImageView(icons.get("zombie"));
		this.setGraphic(view);
	}

	public void asEmpty() {
		if (!cell.isVisible()) {
			this.setEffect(new javafx.scene.effect.GaussianBlur(10.5));
			this.setId("blured");
		} else {
			this.setEffect(null);
			this.setId(null);
		}
	}

	public void asSupply() {
		ImageView view = getImageView(icons.get("supply"));
		this.setGraphic(view);
	}

	public void asVaccine() {
		ImageView view = getImageView(icons.get("vaccine"));
		this.setGraphic(view);
	}

	public void asTrap() {
		ImageView view = getImageView(icons.get("trap"));
		this.setGraphic(view);
	}
}
