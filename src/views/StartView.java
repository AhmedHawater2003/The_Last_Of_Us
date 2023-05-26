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
import javafx.scene.control.Button;
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


public class StartView extends Application {
	 public static final double WIDTH = Screen.getPrimary().getBounds()
	            .getWidth();
	    public static final double HEIGHT = Screen.getPrimary().getBounds()
	            .getHeight();
	    public static void main(String[] args) throws Exception{
	    	Game.loadHeroes("Heros.csv");
	    	launch(args);
	    }
	@Override
	public void start(Stage primaryStage) {
		 
		BorderPane first= new BorderPane();
   	 ImageView background = new ImageView(new Image("views\\winn.jpg", 1980,
             1080, true, true));
        	first.getChildren().add(background);
		    primaryStage.setTitle("The Last of Us");
	        primaryStage.setMaximized(true);
	        Button start= new Button();
	        start.setOnAction(e -> {
	        	SelectionView sv = new SelectionView(primaryStage);
	        });
	        start.setText("Start");
	        start.getStyleClass().add("ipad-dark-grey");
	         Button About= new Button();
	         About.setText("About Game");
	         About.getStyleClass().add("ipad-dark-grey");
	         Button Exit= new Button();
	         Exit.setText("Exit");
	         Exit.getStyleClass().add("ipad-dark-grey");
	         VBox menu = new VBox();
	         start.setMinSize(250, 100);
	         About.setMinSize(250, 100);
	         Exit.setMinSize(250, 100);
	         menu.getChildren().addAll(start,About,Exit);
	         first.getChildren().add(menu);
	         menu.setLayoutX(700);
	         menu.setLayoutY(350);

	         Scene StartMenu =new Scene(first);
	     	primaryStage.setScene(StartMenu);
	     	  menu.getStylesheets().add(getClass().getResource("selection.css").toExternalForm());
	     	    	primaryStage.show();	      
	}
	
}