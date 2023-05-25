package views;

import java.awt.Point;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.characters.Character;
import model.characters.Direction;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.world.Cell;
import model.world.CharacterCell;
import engine.Game;

public class MainView extends Application {
	public static final double WIDTH = Screen.getPrimary().getBounds()
			.getWidth();
	public static final double HEIGHT = Screen.getPrimary().getBounds()
			.getHeight();

	public static BorderPane root = new BorderPane();
	public static Scene MainScene = new Scene(root);
	public static GridPane mapGrid;
	public static VBox herosBar;
	public static HBox interactingStatusBar;
	public static VBox controllabeleHeros;

	public static void main(String[] args) throws Exception {
		Game.loadHeroes("Heros.csv");
		Game.startGame(new Medic("Bill", 10, 100, 1000));
		launch(args);
	}

	public static myButton selectedHeroButton;
	public static myButton selectedTargetButton;

	public static void fuckMap() {
		mapGrid = new GridPane();
		herosBar = new VBox();

		mapGrid.setPrefSize(WIDTH - WIDTH / 6, HEIGHT - HEIGHT / 15);
		mapGrid.setAlignment(Pos.CENTER);
		mapGrid.setHgap(5);
		mapGrid.setVgap(5);

		herosBar.setPadding(new Insets(5, 10, 5, 0));
		herosBar.setSpacing(20);

		interactingStatusBar = new HBox();
		interactingStatusBar.getChildren().add(new Label("Fuck Deadlins"));
		interactingStatusBar.getStyleClass().add("heros");
		interactingStatusBar.setAlignment(Pos.CENTER);
		interactingStatusBar.setPadding(new Insets(5, 5, 5, 5));

		controllabeleHeros = new VBox();
		controllabeleHeros.setSpacing(10);
		controllabeleHeros.setPadding(new Insets(5, 5, 5, 5));
		controllabeleHeros.setAlignment(Pos.CENTER);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setContent(controllabeleHeros);
		scrollPane.setFitToWidth(true);

		for (Hero hero : Game.heroes) {
			controllabeleHeros.getChildren().add(
					ViewHelpers.availableHeroPane(hero));
		}

		// each time we call fuckMap we clear the selected hero pane
		herosBar.getChildren().add(scrollPane);
		if (!Game.heroes.isEmpty())
			root.setLeft(herosBar);
		else {
			root.setLeft(null);
			// TODO : Game should be over
		}
		root.setPadding(new Insets(10));
		VBox tmpVbox = new VBox();
		tmpVbox.getChildren().addAll(mapGrid, interactingStatusBar);
		tmpVbox.setSpacing(10);
		root.setCenter(tmpVbox);

		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				Cell c = Game.map[i][j];
				myButton bttn = new myButton(c);

				if (selectedHeroButton != null
						&& selectedHeroButton.cell.equals(c)) {
					bttn.getStyleClass().add("selected-hero");
					bttn.setStyle("-fx-border-width: 2px;");
					selectedHeroButton = bttn;
				}

				if (selectedTargetButton != null
						&& selectedTargetButton.cell.equals(c)) {
					bttn.getStyleClass().add("target");
					selectedTargetButton = bttn;
				}

				bttn.setOnAction(e -> {

					if (c instanceof CharacterCell) {
						Character character = ((CharacterCell) c)
								.getCharacter();
						if (character instanceof Hero) {
							Hero hero = (Hero) ((CharacterCell) c)
									.getCharacter();
							if (selectedHeroButton != null) { // new hero
																// selected
								selectedHeroButton.getStyleClass().remove(
										"selected-hero");
								herosBar.getChildren().remove(0);
							}
							bttn.getStyleClass().add("selected-hero");
							bttn.setStyle("-fx-border-width: 2px;");
							herosBar.getChildren().add(0,
									ViewHelpers.selectedHeroPane(hero));
							selectedHeroButton = bttn;
						}
						// ! We need somehow to check if the target is valid,
						// maybe modyfing setTarget
						// ! function to through and exception
						else if (character instanceof Zombie
								&& selectedHeroButton != null && c.isVisible()) {
							if (selectedTargetButton != null) {
								selectedTargetButton.getStyleClass().remove(
										"target");
							}
							bttn.getStyleClass().add("target");
							selectedTargetButton = bttn;
							((CharacterCell) selectedHeroButton.cell)
									.getCharacter().setTarget(character);
							herosBar.getChildren().remove(0);
							loadSelected();
						}
					}
				});

				mapGrid.add(bttn, j, 14 - i);
			}
		}

		loadSelected();

	}

	public static void loadSelected() {
		if (selectedHeroButton != null) {
			System.out.println(selectedHeroButton.cell);
			Hero hero = (Hero) ((CharacterCell) selectedHeroButton.cell)
					.getCharacter();

			if (hero != null) {
				herosBar.getChildren().add(0,
						ViewHelpers.selectedHeroPane(hero));
			}
		}
	}

	public static int getGridIndex(Point p) {
		return p.y * 15 + p.x;
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("The Last of Us");
		primaryStage.setMaximized(true);
		primaryStage.setScene(MainScene);

		MainScene.getStylesheets().add(
				getClass().getResource("styles.css").toExternalForm());

		ImageView background = new ImageView(new Image("views\\BG1.jpg", 1980,
				1080, true, true));
		root.getChildren().add(background);
		MainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent key) {
				try {
					boolean wait = false;
					Hero selectedHero = (Hero) ((CharacterCell) selectedHeroButton.cell)
							.getCharacter();
					switch (key.getCode()) {
					case UP: {
						selectedHero.move(Direction.UP);
						break;
					}
					case RIGHT: {
						selectedHero.move(Direction.RIGHT);
						break;
					}
					case LEFT: {
						selectedHero.move(Direction.LEFT);
						break;
					}
					case DOWN: {
						selectedHero.move(Direction.DOWN);
						break;
					}
					case A: {
						selectedHero.attack();
						selectedTargetButton.getStyleClass().add("damged");
						wait = true;

						PauseTransition pauseTransition = new PauseTransition(
								Duration.seconds(1));
						pauseTransition.setOnFinished(event -> {
							fuckMap();
						});

						pauseTransition.play();
						break;
					}
					case C: {
						selectedHero.cure();
						selectedTargetButton = null;
						break;
					}

					case S: {
						selectedHero.useSpecial();
						break;
					}
					}

					Point p = selectedHero.getLocation();
					selectedHeroButton.cell = Game.map[p.x][p.y];
					selectedHero = (Hero) ((CharacterCell) selectedHeroButton.cell)
							.getCharacter();
					if (selectedHero == null) {
						selectedHeroButton = null;
						selectedTargetButton = null;
					}

					if (selectedHero != null) {
						Zombie selectedTarget = (Zombie) selectedHero
								.getTarget();
						if (selectedTarget == null) {
							selectedTargetButton = null;
						}

					}
					if (!wait) {
						fuckMap();
					}
				} catch (Exception e) {
					((Label) interactingStatusBar.getChildren().get(0))
							.setText("fucking Error " + e.getMessage());
				}
			}
		});
		fuckMap();

		primaryStage.show();

	}
}
