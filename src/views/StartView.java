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


public class StartView extends Application {
	 public static final double WIDTH = Screen.getPrimary().getBounds()
	            .getWidth();
	    public static final double HEIGHT = Screen.getPrimary().getBounds()
	            .getHeight();
	@Override
	public void start(Stage primaryStage) {
		 primaryStage.setTitle("The Last of Us");
	        primaryStage.setMaximized(true);
		
	}
	

}
