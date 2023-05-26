package views;

import java.awt.Point;
import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.characters.Character;
import model.characters.Direction;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Zombie;
import model.world.Cell;
import model.world.CharacterCell;
import engine.Game;

public class MainView  { // TODO : Remove Later
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

	public static boolean isInteractable = true;
	public static boolean zombieWillAttack = false;
	public static myButton selectedHeroButton;
	public static myButton selectedTargetButton;
	public static ArrayList<Point> zombieLocations = new ArrayList<Point>();

	public static void loadMap() {
		if (!isInteractable) {
			return;
		}
		mapGrid = new GridPane();
		herosBar = new VBox();

		mapGrid.setPrefSize(WIDTH - WIDTH / 6, HEIGHT - HEIGHT / 15);
		mapGrid.setAlignment(Pos.CENTER);
		mapGrid.setHgap(5);
		mapGrid.setVgap(5);

		herosBar.setPadding(new Insets(5, 10, 5, 0));
		herosBar.setSpacing(20);

		interactingStatusBar = new HBox();
		Label txt = new Label("");
		txt.getStyleClass().add("bar");
		interactingStatusBar.getChildren().add(txt);
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

				

				bttn.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						if (c instanceof CharacterCell
								&& ((CharacterCell) c).getCharacter() != null) {
							Character character = ((CharacterCell) c)
									.getCharacter();
							if (mouseEvent.getButton().equals(
									MouseButton.SECONDARY)) {

								if (character instanceof Hero) {
									Hero hero = (Hero) ((CharacterCell) c)
											.getCharacter();
									if (selectedHeroButton != null) { // new
																		// hero
										// selected
										selectedHeroButton.getStyleClass()
												.remove("selected-hero");
										herosBar.getChildren().remove(0);
									}
									bttn.getStyleClass().add("selected-hero");
									bttn.setStyle("-fx-border-width: 2px;");
									herosBar.getChildren().add(0,
											ViewHelpers.selectedHeroPane(hero));
									selectedHeroButton = bttn;
									if (selectedTargetButton != null) {
										selectedTargetButton.getStyleClass()
												.remove("target");
										selectedTargetButton = null;
									}
								}
							}

							else {
								if (c instanceof CharacterCell
										&& selectedHeroButton != null
										&& c.isVisible()) {
									if (selectedTargetButton != null) {
										selectedTargetButton.getStyleClass()
												.remove("target");
									}
									bttn.getStyleClass().add("target");
									selectedTargetButton = bttn;
									((CharacterCell) selectedHeroButton.cell)
											.getCharacter()
											.setTarget(character);
									herosBar.getChildren().remove(0);
									loadSelected();
								}
							}
						}
					}
				});

				mapGrid.add(bttn, j, 14 - i);
			}
		}

		loadSelected();
		if(Game.checkGameOver()){
			Alert.displayGameOver("OoOoPs,,Game Over!","Game Over! The relentless horde of zombies has overrun your defenses, "
					+ "\n and the last flicker of hope fades away. The once bustling city now stands silent, "
					+ "\n consumed by darkness. Despite your valiant efforts to survive, "
					+ "\n the unyielding forces of the undead proved too formidable. "
					+ "\n Your courage and resourcefulness will be remembered, "
					+ "\n but the world now falls deeper into the clutches of the zombie apocalypse. "
					+ "\n Gather your strength, for a new dawn may bring another chance to reclaim what has been lost. "
					+ "\n Prepare yourself, survivor, for the fight is not yet over.");
		}
		if(Game.checkWin()){
			AlertWin.displayWin("Yayy,,You Just Won!!","  Victory Achieved! Against all odds,"
					+ "\n you have emerged triumphant in the face of the relentless zombie horde. "
					+ "\n Your unwavering determination and strategic prowess have paid off, "
					+ "\n as you fought tooth and nail to reclaim the remnants of civilization. "
					+ "\n The once desolate streets now echo with the sounds of hope and renewal. "
					+ "\n Your courage has inspired others, and the survivors rally behind your leadership. "
					+ "\n However, be ever vigilant, for the world remains a treacherous place. "
					+ "\n Celebrate this hard-earned victory, but remember that the struggle for survival continues. "
					+ "\n Embrace your triumph, hero, and prepare for the challenges that lie ahead.");
		}

	}

	public static void loadSelected() {
		if (selectedHeroButton != null) {
			
			Hero hero = (Hero) ((CharacterCell) selectedHeroButton.cell)
					.getCharacter();

			if (hero != null) {
				herosBar.getChildren().add(0,
						ViewHelpers.selectedHeroPane(hero));
			}
		}
	}

	public static int getGridIndex(Point p) {
		return p.x * 15 + p.y;
	}

	public static void zombieAttacking() {
		if (Game.zombiesTmpList.isEmpty()) {
			Game.tail();
			isInteractable = true;
			loadMap();
			return;
		}
		Zombie onTurn = Game.zombiesTmpList.remove(0);
		onTurn.setZombieTarget();
		Node zombieButton = mapGrid.getChildren().get(
				getGridIndex(onTurn.getLocation()));
		if (onTurn.getTarget() != null) {
			Node targetButton = mapGrid.getChildren().get(
					getGridIndex(onTurn.getTarget().getLocation()));
			zombieButton.getStyleClass().add("attackingZombie");
			targetButton.getStyleClass().add("damged");
			PauseTransition pauseTransition = new PauseTransition(
					Duration.seconds(1));
			pauseTransition.setOnFinished(event -> {
				try {
					zombieButton.getStyleClass().remove("attackingZombie");
					targetButton.getStyleClass().remove("damged");
					onTurn.attack();
					loadMap();
					zombieAttacking();
				} catch (Exception e) {
					((Label) interactingStatusBar.getChildren().get(0))
							.setText("WARNING :  " + e.getMessage() + " !!");
				}

			});
			pauseTransition.play();
		} else
			zombieAttacking();

	}

	public static Scene start() {

		ImageView background = new ImageView(new Image("views\\BG1.jpg", 1980,
				1080, true, true));
		root.getChildren().add(background);
		MainScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent key) {
				if (!isInteractable) {
					return;
				}
				try {
					boolean wait = false;
					Hero selectedHero = null;
					if (selectedHeroButton == null)
						System.out
								.println("selectedHeroisNull and key is pressed");
					else {
						selectedHero = (Hero) ((CharacterCell) selectedHeroButton.cell)
								.getCharacter();
					}

					switch (key.getCode()) {
					case UP: {
						selectedHero.move(Direction.UP);
						break;
					}
					case RIGHT: {
						if (selectedHero == null) {
							System.out.println("Please Select Hero First");
						}
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
						isInteractable = false;

						PauseTransition pauseTransition = new PauseTransition(
								Duration.seconds(0.5));
						pauseTransition.setOnFinished(event -> {
							isInteractable = true;
							loadMap();
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
					case E: {
						Game.head();
						isInteractable = false;
						zombieAttacking();

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
						Character selectedTarget = selectedHero.getTarget();
						if (selectedTarget == null) {
							selectedTargetButton = null;
						}

					}
					if (!wait) {
						loadMap();
						if (selectedHero.isTrapped) {
							selectedHero.applyDamgeTaken();

							selectedHeroButton.getStyleClass().add("damged");
							isInteractable = false;
							PauseTransition pauseTransition = new PauseTransition(
									Duration.seconds(1));
							pauseTransition.setOnFinished(event -> {
								selectedHeroButton.getStyleClass().remove(
										"damged");
								isInteractable = true;
								loadMap();
							});
							pauseTransition.play();

						}

					}
				} catch (Exception e) {
					((Label) interactingStatusBar.getChildren().get(0))
							.setText("WARNING : " + e.getMessage() + " !!");
				}
			}
		});

		loadMap();
		
		MainScene.getStylesheets().add("views/styles.css");
		return MainScene;


	}
}
