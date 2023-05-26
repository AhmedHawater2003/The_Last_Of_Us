package views;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertWin {
public static void displayWin(String title , String message) {
		
		Stage winGame = new Stage();
		winGame.initModality(Modality.APPLICATION_MODAL);
		winGame.setTitle("Yayy,,You Just Won!!");
		winGame.setMinWidth(500);
		Label over = new Label();
		over.setText("Victory Achieved! Against all odds,"
				+ "\n you have emerged triumphant in the face of the relentless zombie horde. "
				+ "\n Your unwavering determination and strategic prowess have paid off, "
				+ "\n as you fought tooth and nail to reclaim the remnants of civilization. "
				+ "\n The once desolate streets now echo with the sounds of hope and renewal. "
				+ "\n Your courage has inspired others, and the survivors rally behind your leadership. "
				+ "\n However, be ever vigilant, for the world remains a treacherous place. "
				+ "\n Celebrate this hard-earned victory, but remember that the struggle for survival continues. "
				+ "\n Embrace your triumph, hero, and prepare for the challenges that lie ahead.");
	    Button close = new Button();
	    close.setText("Close");
	    close.setOnAction(e -> winGame.close());
	    VBox layout = new VBox();
	    layout.getChildren().addAll(over,close);
	    layout.setAlignment(Pos.TOP_CENTER);
	    Scene scene = new Scene(layout);
	    winGame.setScene(scene);
	    winGame.showAndWait();
	    
	}

}
