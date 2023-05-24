package views;

import views.ViewHelpers.*;
import java.util.*;
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

public class MainView extends Application {
    public static final double WIDTH = Screen.getPrimary().getBounds()
            .getWidth();
    public static final double HEIGHT = Screen.getPrimary().getBounds()
            .getHeight();

    public static BorderPane root = new BorderPane();
    public static Scene MainScene = new Scene(root);

    public static void main(String[] args) {
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

        double cellWidth = WIDTH - WIDTH / 6, cellHeight = HEIGHT - HEIGHT / 15;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                myButton cell = new myButton(i, j);
                cell.setPrefSize(cellWidth / 15, cellHeight / 15);
                mapGrid.add(cell, i, j);
                if(!(j == 14 && i == 0)){
                    cell.setEffect(new GaussianBlur(10.5));
                    cell.setId("blured");
                }
                cell.setOnAction(e -> {
                    myButton b = (myButton) e.getSource();
                    System.out.println(b.x + " " + b.y);
                });
            }
        }

        // Showing how the icons look like
        myButton b = (myButton) mapGrid.getChildren().get(14);
        ImageView view = new ImageView("icons\\doctor.png");
        b.getStyleClass().add("doctor");
        view.setFitHeight(cellHeight / 22);
        view.setFitWidth(cellWidth / 22);
        b.setGraphic(view);

        myButton b2 = (myButton) mapGrid.getChildren().get(224 - 1);
        ImageView view2 = new ImageView("views\\explorer.png");

        b2.getStyleClass().add("explorer");
        view2.setFitHeight(cellHeight / 22);
        view2.setFitWidth(cellWidth / 22);
        b2.setGraphic(view2);

        myButton b3 = (myButton) mapGrid.getChildren().get(224 - 2);
        ImageView view3 = new ImageView("views\\assasin.png");
        b3.getStyleClass().add("fighter");
        view3.setFitHeight(cellHeight / 22);
        view3.setFitWidth(cellWidth / 22);
        b3.setGraphic(view3);

        myButton b4 = (myButton) mapGrid.getChildren().get(224 - 3);
        ImageView view4 = new ImageView("icons\\zombie.png");
        b4.getStyleClass().add("zombie");
        view4.setFitHeight(cellHeight / 22);
        view4.setFitWidth(cellWidth / 22);
        b4.setGraphic(view4);

        myButton b5 = (myButton) mapGrid.getChildren().get(224 - 4);
        ImageView view5 = new ImageView("icons\\supply.png");
        b5.getStyleClass().add("collectible");
        view5.setFitHeight(cellHeight / 22);
        view5.setFitWidth(cellWidth / 22);
        b5.setGraphic(view5);

        myButton b6 = (myButton) mapGrid.getChildren().get(224 - 5);
        ImageView view6 = new ImageView("icons\\vaccine.png");
        b6.getStyleClass().add("collectible");
        view6.setFitHeight(cellHeight / 22);
        view6.setFitWidth(cellWidth / 22);
        b6.setGraphic(view6);

        primaryStage.show();

    }

}
