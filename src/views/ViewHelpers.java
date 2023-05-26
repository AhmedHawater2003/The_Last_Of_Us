package views;

import java.util.HashMap;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import model.characters.Character;
import model.characters.Hero;
import model.characters.Zombie;

public class ViewHelpers {

	public static final double WIDTH = Screen.getPrimary().getBounds()
			.getWidth();
	public static final double HEIGHT = Screen.getPrimary().getBounds()
			.getHeight();

	public static HashMap<String, Image> icons = new HashMap<String, Image>();

	public static void loadingIconsDict() {
		icons.put("Ellie Williams", new Image("views\\ellie.png"));
		icons.put("Joel Miller", new Image("views\\joel.png"));
		icons.put("Tess", new Image("views\\tess.png"));
		icons.put("Riley Abel", new Image("views\\riley.png"));
		icons.put("Tommy Miller", new Image("views\\tommy.png"));
		icons.put("Bill", new Image("views\\bill.png"));
		icons.put("David", new Image("views\\david.png"));
		icons.put("Henry Burell", new Image("views\\henry.png"));
		icons.put("heart", new Image("icons\\heart.png"));
		// icons.put("supply", new Image("icons\\supply.png"));
		// icons.put("vaccine", new Image("icons\\vaccine.png"));
		// icons.put("trap", new Image("views\\assasin.png"));
	}

	public static Pane labelWithIconPane(String txt, Image iconImage) {
		HBox pane = new HBox();
		Label icon = new Label();
		Label text = new Label(txt);
		ImageView iconView = new ImageView(iconImage);
		iconView.setFitHeight(15);
		iconView.setFitWidth(15);
		icon.setGraphic(iconView);
		pane.getChildren().addAll(icon, text);
		pane.setPadding(new Insets(2, 5, 2, 5));
		pane.setSpacing(10);
		return pane;

	}

	public static Pane selctedHeroInfo(Hero hero) {
		VBox pane = (VBox) availableHeroInfo(hero);
		pane.getChildren().add(
				labelWithIconPane(hero.getSupplyInventory().toString(),
						icons.get("heart")));
		pane.getChildren().add(
				labelWithIconPane(hero.getVaccineInventory().toString(),
						icons.get("heart")));
		Character target = hero.getTarget();
		if (target != null && target instanceof Zombie) {
			pane.getChildren().add(
					labelWithIconPane(
							target.getCurrentHp() + " / " + target.getMaxHp(),
							icons.get("heart")));
		}
		return pane;
	}

	public static Pane availableHeroInfo(Hero hero) {
		VBox pane = new VBox();
		String type = hero.getClass().toString().split("\\.")[2];
		pane.getChildren().add(
				labelWithIconPane(hero.getName(), icons.get("heart")));
		pane.getChildren().add(labelWithIconPane(type, icons.get("heart")));
		pane.getChildren().add(
				labelWithIconPane(
						hero.getCurrentHp() + " / " + hero.getMaxHp(),
						icons.get("heart")));
		pane.getChildren()
				.add(labelWithIconPane(hero.getAttackDmg() + "",
						icons.get("heart")));
		pane.getChildren().add(
				labelWithIconPane(
						hero.getActionsAvailable() + " / "
								+ hero.getMaxActions(), icons.get("heart")));
		return pane;
	}

	public static Node borderedImage(String imagePath, double imageHigh) {
		Label label = new Label();
		ImageView imageView = new ImageView(icons.get(imagePath));
		imageView.setFitHeight(imageHigh);
		imageView.setPreserveRatio(true);
		label.setGraphic(imageView);
		return label;
	}

	public static Pane heroPane(Node heroImage, Node heroInfo) {
		HBox pane = new HBox();
		pane.getChildren().addAll(heroImage, heroInfo);
		pane.setPadding(new Insets(5, 0, 5, 5));
		pane.setSpacing(10);
		pane.getStyleClass().add("heros");
		pane.setAlignment(Pos.CENTER);
		return pane;
	}

	public static Pane availableHeroPane(Hero hero) {
		Node heroImage = borderedImage(hero.getName(), HEIGHT / 7);
		Node heroInfo = availableHeroInfo(hero);
		Pane pane = heroPane(heroImage, heroInfo);
		pane.getStyleClass().add("available-hero");
		return pane;
	}

	public static Pane selectedHeroPane(Hero hero) {
		Node heroImage = borderedImage(hero.getName(), HEIGHT / 6);
		Node heroInfo = selctedHeroInfo(hero);
		Pane pane = heroPane(heroImage, heroInfo);
		pane.getStyleClass().add("selected-hero");
		return pane;
	}
}
