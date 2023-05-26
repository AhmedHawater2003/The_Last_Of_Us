package views;
import java.awt.Point;


import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import model.characters.Character;
import model.characters.Direction;
import model.characters.Fighter;
import model.characters.Hero;
import model.world.Cell;
import model.world.CharacterCell;
import engine.Game;

public class Alert {
	public static void displayGameOver(String title , String message) {
		
		Stage gameOver = new Stage();
		gameOver.initModality(Modality.APPLICATION_MODAL);
		gameOver.setTitle("OoOoPs,,Game Over!");
		gameOver.setMinWidth(250);
		Label over = new Label();
		over.setText("Game Over! The relentless horde of zombies has overrun your defenses, "
				+ "\n and the last flicker of hope fades away. The once bustling city now stands silent, "
				+ "\n consumed by darkness. Despite your valiant efforts to survive, "
				+ "\n the unyielding forces of the undead proved too formidable. "
				+ "\n Your courage and resourcefulness will be remembered, "
				+ "\n but the world now falls deeper into the clutches of the zombie apocalypse. "
				+ "\n Gather your strength, for a new dawn may bring another chance to reclaim what has been lost. "
				+ "\n Prepare yourself, survivor, for the fight is not yet over.");
	    Button close = new Button();
	    close.setText("Close");
	    close.setOnAction(e -> {
	    	gameOver.close();
	    	});
	    VBox layout = new VBox();
	    layout.getChildren().addAll(over,close);
	    layout.setAlignment(Pos.TOP_CENTER);
	    Scene scene = new Scene(layout);
	    gameOver.setScene(scene);
	    gameOver.showAndWait();
	    
	}
}







	
	


