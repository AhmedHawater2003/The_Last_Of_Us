package views;

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
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainView extends Application {
	public static final double WIDTH = Screen.getPrimary().getBounds()
			.getWidth();
	public static final double HEIGHT = Screen.getPrimary().getBounds()
			.getHeight();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Hello World!");
		primaryStage.setMaximized(true);

		BorderPane root = new BorderPane();
		Scene MainScene = new Scene(root);
		MainScene.getStylesheets().add(
				getClass().getResource("styles.css").toExternalForm());

		ImageView background = new ImageView(new Image("views\\Five.jpg", 1980,
				1080, true, true));
		root.getChildren().add(background);
		primaryStage.setScene(MainScene);

		GridPane grid = new GridPane();
		grid.setPrefSize(WIDTH - WIDTH / 6, HEIGHT - HEIGHT / 15);
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(5);
		grid.setVgap(5);

		VBox StausBar = new VBox();
		StausBar.setPadding(new Insets(5, 10, 5, 0));
		StausBar.setSpacing(20);
		// StausBar.setId("one");

		VBox selectedHero = new VBox();
		selectedHero.getChildren().add(0, new Label("Fuck Deadlines"));
		selectedHero.getStyleClass().add("contrable-heros");
		selectedHero.setAlignment(Pos.CENTER);

		VBox controllabeleHeros = new VBox();
		controllabeleHeros.setSpacing(10);
		controllabeleHeros.setPadding(new Insets(5, 15, 5, 5));
		controllabeleHeros.setId("one");

		double recWidth = (WIDTH / 5.5), recHeight = (HEIGHT / 15);
		for (int i = 0; i < 6; i++) {
			VBox l = new VBox();
			l.setAlignment(Pos.CENTER);
			l.setPrefSize(recWidth, HEIGHT / 4);
			l.getStyleClass().add("contrable-heros");
			Label leb = new Label("Fuck Deadlines");
			l.getChildren().add(leb);
			controllabeleHeros.getChildren().add(l);
		}

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setId("one");
		scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setContent(controllabeleHeros);

		StausBar.getChildren().addAll(selectedHero, scrollPane);
		root.setLeft(StausBar);
		root.setPadding(new Insets(10));
		root.setCenter(grid);

		double cellWidth = WIDTH - WIDTH / 6, cellHeight = HEIGHT - HEIGHT / 15;
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				myButton cell = new myButton(i, j);
				cell.setPrefSize(cellWidth / 15, cellHeight / 15);
				grid.add(cell, i, j);
				cell.setOnAction(e -> {
					myButton b = (myButton) e.getSource();
					System.out.println(b.x + " " + b.y);
				});
			}
		}
		primaryStage.show();

	}

	public void zoom(Pane pane) {
		pane.setOnScroll(new EventHandler<ScrollEvent>() {

			@Override
			public void handle(ScrollEvent event) {
				double zoomFactor = 1.05;
				double deltaY = event.getDeltaY();

				if (deltaY < 0) {
					zoomFactor = 0.95;
				}
				pane.setScaleX(pane.getScaleX() * zoomFactor);
				pane.setScaleY(pane.getScaleY() * zoomFactor);
				event.consume();
			}
		});
	}

}
