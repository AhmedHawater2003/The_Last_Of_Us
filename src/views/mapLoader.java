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
import java.awt.Point;
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
import model.characters.Character;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Zombie;
import model.world.*;
import model.world.CharacterCell;

public class mapLoader {

    public static final double WIDTH = Screen.getPrimary().getBounds()
            .getWidth();
    public static final double HEIGHT = Screen.getPrimary().getBounds()
            .getHeight();

    public static double mapWidth = WIDTH - WIDTH / 6, mapHeight = HEIGHT - HEIGHT / 15;

    public static int getGridIndex(Point p) {
        return p.y * 15 + p.x;
    }

    public static myButton ahmed(Cell cell) {
        myButton bttn = new myButton(cell);
        bttn.setPrefSize(mapWidth / 15, mapHeight / 15);
        if (!(cell instanceof CharacterCell && ((CharacterCell) cell).getCharacter() == null)) {
            
        } else {
            bttn.setEffect(new GaussianBlur(10.5));
            bttn.setId("blured");
        }
        return bttn;
    }
}
