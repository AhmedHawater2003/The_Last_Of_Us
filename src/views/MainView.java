package views;

import views.ViewHelpers.*;
import java.util.*;

import engine.Game;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Zombie;
import model.world.Cell;

public class MainView extends Application {
    public static final double WIDTH = Screen.getPrimary().getBounds()
            .getWidth();
    public static final double HEIGHT = Screen.getPrimary().getBounds()
            .getHeight();

    public static BorderPane root = new BorderPane();
    public static Scene MainScene = new Scene(root);

    public static void main(String[] args) throws Exception {
        Game.loadHeroes("Heros.csv");
        Game.startGame(new Fighter("Bill", 100, 100, 100));
        launch(args);
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

        GridPane mapGrid = new GridPane();
        mapGrid.setPrefSize(WIDTH - WIDTH / 6, HEIGHT - HEIGHT / 15);
        mapGrid.setAlignment(Pos.CENTER);
        mapGrid.setHgap(5);
        mapGrid.setVgap(5);

        VBox herosBar = new VBox();
        herosBar.setPadding(new Insets(5, 10, 5, 0));
        herosBar.setSpacing(20);

        HBox interactingStatusBar = new HBox();
        interactingStatusBar.getChildren().addAll(new Label("Fuck Deadlins"));
        interactingStatusBar.getStyleClass().add("heros");
        interactingStatusBar.setAlignment(Pos.CENTER);
        interactingStatusBar.setPadding(new Insets(5, 5, 5, 5));

        VBox controllabeleHeros = new VBox();
        controllabeleHeros.setSpacing(10);
        controllabeleHeros.setPadding(new Insets(5,0, 5, 5));
        controllabeleHeros.setAlignment(Pos.CENTER);


        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(controllabeleHeros);


        Hero hawater = new Fighter("Ahmed Hawater", 100, 100, 100);
        Zombie ahd = new Zombie();
        hawater.setTarget(ahd);

        for (int i = 0; i < 6; i++) {
            controllabeleHeros.getChildren().add(ViewHelpers.availableHeroPane(hawater));
        }

        herosBar.getChildren().addAll(ViewHelpers.selectedHeroPane(hawater), scrollPane);
        root.setLeft(herosBar);
        root.setPadding(new Insets(10));
        VBox tmpVbox = new VBox();
        tmpVbox.getChildren().addAll(mapGrid, interactingStatusBar);
        tmpVbox.setSpacing(5);
        root.setCenter(tmpVbox);

        
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Cell c = Game.map[i][j];
                mapGrid.add(new myButton(c), i,14 -j);
            }
        }

        // Showing how the icons look like
        int row = 3, col = 14;
        myButton b = (myButton) mapGrid.getChildren().get(col * 15 + row);


        primaryStage.show();

    }

}
