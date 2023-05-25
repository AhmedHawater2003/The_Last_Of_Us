package views;

import java.io.IOException;
import java.util.*;

import engine.Game;
import model.characters.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import views.MainView;


public class SelectionView {
	 public static final double WIDTH = Screen.getPrimary().getBounds()
	            .getWidth();
	    public static final double HEIGHT = Screen.getPrimary().getBounds()
	            .getHeight();
	
	    
	    public SelectionView(Stage s){
	    	start(s);
	    }
	    
//	    public static void main(String[] args) throws Exception{
//	    	Game.loadHeroes("Heros.csv");
//	    	launch(args);
//	    }
	    public void start(Stage primaryStage) {
	    	
	    	myButton selectedHero;
	    	GridPane heroesSelector= new GridPane();
	    	ToggleGroup g = new ToggleGroup();
	    	RadioButton hero1=new RadioButton();
	    	hero1.setToggleGroup(g);
	    	// Image image = new Image("views\\joel.png"); // Replace with the actual path to your image file
	         //ImageView imageView = new ImageView(image);
	         //imageView.setFitWidth(150); // Set the desired width of the image
	         //imageView.setFitHeight(150); 
	         //hero1.setGraphic(imageView);
	         //hero1.graphicProperty();
	  	    //hero1.autosize();
	    	
	    	// if (selectedHero != null) {
              //   hero1.getStyleClass().add("selected-hero");
                // hero1.setStyle("-fx-border-width: 2px;");
               //  selectedHero = hero1;
             //}
	    	hero1.setOnAction(e -> {
	            hero1.getStyleClass().add("RadioButton-clicked");
	    	});
	    	hero1.setText("Name: "+Game.availableHeroes.get(0).getName()+"\nType: Fighter" +"\n HP: "+Game.availableHeroes.get(0).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(0).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(0).getActionsAvailable());
	    	RadioButton hero2=new RadioButton();
	    	hero2.setToggleGroup(g);
	    	hero2.setOnAction(e -> {
	            hero2.getStyleClass().add("RadioButton-clicked");
	    	});
	    	hero2.setText("Name: "+Game.availableHeroes.get(1).getName()+"\nType: Medic"+"\n HP: "+Game.availableHeroes.get(1).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(1).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(1).getActionsAvailable());
	    	RadioButton hero3=new RadioButton();
	    	hero3.setToggleGroup(g);
	    	hero3.setOnAction(e -> {
	            hero3.getStyleClass().add("RadioButton-clicked");
	    	});
	    	hero3.setText("Name: "+Game.availableHeroes.get(2).getName()+"\nType Explorer"+"\n HP: "+Game.availableHeroes.get(2).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(2).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(2).getActionsAvailable());
	    	RadioButton hero4=new RadioButton();
	    	hero4.setToggleGroup(g);
	    	hero4.setOnAction(e -> {
	            hero4.getStyleClass().add("RadioButton-clicked");
	    	});
	    	hero4.setText("Name: "+Game.availableHeroes.get(3).getName()+"\nType Explorer"+"\n HP: "+Game.availableHeroes.get(3).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(3).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(3).getActionsAvailable());
	    	RadioButton hero5=new RadioButton();
	    	hero5.setToggleGroup(g);
	    	hero5.setOnAction(e -> {
	            hero5.getStyleClass().add("RadioButton-clicked");
	    	});
	    	hero5.setText("Name: "+Game.availableHeroes.get(4).getName()+"\nType Explorer"+"\n HP: "+Game.availableHeroes.get(4).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(4).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(4).getActionsAvailable());
	    	RadioButton hero6=new RadioButton();
	    	hero6.setToggleGroup(g);
	    	hero6.setOnAction(e -> {
	            hero6.getStyleClass().add("RadioButton-clicked");
	    	});
	    	hero6.setText("Name: "+Game.availableHeroes.get(5).getName()+"\nType Medic"+"\n HP: "+Game.availableHeroes.get(5).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(5).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(5).getActionsAvailable());
	    	RadioButton hero7=new RadioButton();
	    	hero7.setToggleGroup(g);
	    	hero7.setOnAction(e -> {
	            hero7.getStyleClass().add("RadioButton-clicked");
	    	});
	    	hero7.setText("Name: "+Game.availableHeroes.get(6).getName() + "\nType Fighter"+"\n HP: "+Game.availableHeroes.get(6).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(6).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(6).getActionsAvailable());
	    	RadioButton hero8=new RadioButton();
	    	hero8.setToggleGroup(g);
	    	hero8.setOnAction(e -> {
	            hero8.getStyleClass().add("Radioutton-clicked");
	    	});
	    	hero8.setText("Name: "+Game.availableHeroes.get(7).getName()+"\nType Medic"+"\n HP: "+Game.availableHeroes.get(7).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(7).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(7).getActionsAvailable());
	    	GridPane.setConstraints(hero1, 0, 0);
	    	heroesSelector.getChildren().add(hero1);
	    	GridPane.setConstraints(hero2, 1, 0);
	    	heroesSelector.getChildren().add(hero2);
	    	GridPane.setConstraints(hero3, 2, 0);
	    	heroesSelector.getChildren().add(hero3);
	    	GridPane.setConstraints(hero4, 0, 1);
	    	heroesSelector.getChildren().add(hero4);
	    	GridPane.setConstraints(hero5, 1, 1);
	    	heroesSelector.getChildren().add(hero5);
	    	GridPane.setConstraints(hero6, 2,1 );
	    	heroesSelector.getChildren().add(hero6);
	    	GridPane.setConstraints(hero7, 0, 2);
	    	heroesSelector.getChildren().add(hero7);
	    	GridPane.setConstraints(hero8, 1, 2);
	    	heroesSelector.getChildren().add(hero8);
	    	heroesSelector.setAlignment(Pos.CENTER_RIGHT);
	    	hero1.setMinSize(300, 300);
	    	hero2.setMinSize(300, 300);	
	    	hero3.setMinSize(300, 300);
	    	hero4.setMinSize(300, 300);
	    	hero5.setMinSize(300, 300);
	    	hero6.setMinSize(300, 300);
	    	hero7.setMinSize(300, 300);
	    	hero8.setMinSize(300, 300);
	    	
	    	BorderPane bord= new BorderPane();
	    	 ImageView background = new ImageView(new Image("views\\The-Last-of-Us-Part-2-Landscape-Header - Copy.png", 1980,
	                 1080, true, true));
	         bord.getChildren().add(background);
	         bord.setCenter(heroesSelector);
	         
	         VBox talking=new VBox();
	         Label choose=new Label("  			 In the wake of a devastating zombie outbreak, "
	         		+ "\n			 darkness engulfs the world,"
	         		+ "\n			 teeming with hordes of the undead. Amidst this harrowing chaos, "
	         		+ "\n			 courageous heroes emerge from the shadows,"
	         		+ "\n			 ready to wage a desperate "
	         		+ "\n			 battle for humanity's survival."
	         		+ "\n			 As a hero of unparalleled prowess, "
	         		+ "\n 			 you step forward,"
	         		+ "\n			 armed with unwavering determination and an arsenal of "
	         		+ "\n			 skills. The fate of the remnants of civilization rests in your hands."	         		
	         		+ "\n 			 Will you rise as the savior,"
	         		+ "\n			 leading a band of survivors with your tactical "
	         		+ "\n			 brilliance? Or shall you unleash relentless fury as a lone warrior,"
	         		+ "\n			 striking "
	         		+ "\n			 fear into the hearts of the undead? The time has come to confront the "
	         		+ "\n			 abominations that haunt this post-apocalyptic realm. "
	         		+ "\n			 Brace yourself, hero, "
	         		+ "\n			 for an epic journey awaits, where your choices will shape the destiny of "
	         		+ "\n			 both the living and the undead.");
	         
	         Button go= new Button();
	         go.setText("Go to the game");
	         go.setOnAction(e -> {
	 			try {
					Game.startGame(new Fighter("Bill", 2, 100, 1000));
		        	 Game.loadHeroes("Heros.csv");

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        	MainView sv = new MainView(primaryStage);
		        });
	         
	           
	         
	         talking.getChildren().addAll(choose,go);
	         talking.setAlignment(Pos.CENTER);
	         bord.setLeft(talking);
	         
	         Scene SelectingHero =new Scene(bord);
		    	primaryStage.setScene(SelectingHero);
		    	  SelectingHero.getStylesheets().add(
		                  getClass().getResource("selection.css").toExternalForm());
		    	   
	    	primaryStage.show();
	    	
	    }
}