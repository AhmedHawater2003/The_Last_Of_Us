package views;

import java.io.IOException;
import java.util.*;

import com.sun.corba.se.impl.orbutil.graph.Node;
import model.characters.Hero;
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
	    Hero selected= Game.availableHeroes.get(0);
	    
	    public SelectionView(Stage s){
	    	try {
				Game.loadHeroes("Heros.csv");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	start(s);
	    }
	    
	    public static void main(String[] args) throws Exception{
	    	Game.loadHeroes("Heros.csv");
	    	//launch(args);
	    }
	    public void start(Stage primaryStage) {
	    	
	    	myButton selectedHero;
	    	GridPane heroesSelector= new GridPane();
	    	ToggleGroup g = new ToggleGroup();
	    	RadioButton hero1=new RadioButton();
	    	
	    	
	    	hero1.setToggleGroup(g);
	    	Image image1 = new Image("views\\joel.png"); // Replace with the actual path to your image file
	        ImageView imageView1 = new ImageView(image1);
	 		imageView1.setPreserveRatio(true);
	        imageView1.setFitHeight(200); 
	        hero1.setGraphic(imageView1);
	        hero1.graphicProperty();
	  	    hero1.autosize();
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
	    		selected =Game.availableHeroes.get(0);
	            hero1.getStyleClass().add("RadioButton-clicked");
	    	});
	    	hero1.setText(Game.availableHeroes.get(0).getName()+"\nType: Fighter" +"\n HP: "+Game.availableHeroes.get(0).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(0).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(0).getActionsAvailable());
	    	RadioButton hero2=new RadioButton();
	    	hero2.setToggleGroup(g);
	    	Image image2 = new Image("views\\ellie.png"); // Replace with the actual path to your image file
	        ImageView imageView2 = new ImageView(image2);
	 		imageView2.setPreserveRatio(true);
	        imageView2.setFitHeight(200); 
	        hero2.setGraphic(imageView2);
	        hero2.graphicProperty();
	  	    hero2.autosize();
	    	hero2.setOnAction(e -> {
	    		selected =Game.availableHeroes.get(1);
	            hero2.getStyleClass().add("RadioButton-clicked");
	    	});
	    	hero2.setText(Game.availableHeroes.get(1).getName()+"\nType: Medic"+"\n HP: "+Game.availableHeroes.get(1).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(1).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(1).getActionsAvailable());
	    	RadioButton hero3=new RadioButton();
	    	hero3.setToggleGroup(g);
	    	Image image3 = new Image("views\\tess.png"); // Replace with the actual path to your image file
	        ImageView imageView3 = new ImageView(image3);
	 		imageView3.setPreserveRatio(true);
	        imageView3.setFitHeight(200); 
	        hero3.setGraphic(imageView3);
	        hero3.graphicProperty();
	  	    hero3.autosize();
	    	hero3.setOnAction(e -> {
	    		selected =Game.availableHeroes.get(2);
	            hero3.getStyleClass().add("RadioButton-clicked");
	    	});
	    	hero3.setText(Game.availableHeroes.get(2).getName()+"\nType Explorer"+"\n HP: "+Game.availableHeroes.get(2).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(2).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(2).getActionsAvailable());
	    	RadioButton hero4=new RadioButton();
	    	hero4.setToggleGroup(g);
	    	Image image4 = new Image("views\\riley.png"); // Replace with the actual path to your image file
	        ImageView imageView4 = new ImageView(image4);
	 		imageView4.setPreserveRatio(true);
	        imageView4.setFitHeight(200); 
	        hero4.setGraphic(imageView4);
	        hero4.graphicProperty();
	  	    hero4.autosize();
	    	hero4.setOnAction(e -> {
	    		selected =Game.availableHeroes.get(3);
	            hero4.getStyleClass().add("RadioButton-clicked");
	    	});
	    	hero4.setText(Game.availableHeroes.get(3).getName()+"\nType Explorer"+"\n HP: "+Game.availableHeroes.get(3).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(3).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(3).getActionsAvailable());
	    	RadioButton hero5=new RadioButton();
	    	hero5.setToggleGroup(g);
	    	Image image5 = new Image("views\\tommy.png"); // Replace with the actual path to your image file
	        ImageView imageView5 = new ImageView(image5);
	 		imageView5.setPreserveRatio(true);
	        imageView5.setFitHeight(200); 
	        hero5.setGraphic(imageView5);
	        hero5.graphicProperty();
	  	    hero5.autosize();
	    	hero5.setOnAction(e -> {
	    		selected =Game.availableHeroes.get(4);
	            hero5.getStyleClass().add("RadioButton-clicked");
	    	});
	    	hero5.setText(Game.availableHeroes.get(4).getName()+"\nType Explorer"+"\n HP: "+Game.availableHeroes.get(4).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(4).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(4).getActionsAvailable());
	    	RadioButton hero6=new RadioButton();
	    	hero6.setToggleGroup(g);
	    	Image image6 = new Image("views\\bill.png"); // Replace with the actual path to your image file
	        ImageView imageView6 = new ImageView(image6);
	 		imageView6.setPreserveRatio(true);
	        imageView6.setFitHeight(200); 
	        hero6.setGraphic(imageView6);
	        hero6.graphicProperty();
	  	    hero6.autosize();
	    	hero6.setOnAction(e -> {
	    		selected =Game.availableHeroes.get(5);
	            hero6.getStyleClass().add("RadioButton-clicked");
	    	});
	    	hero6.setText(Game.availableHeroes.get(5).getName()+"\nType Medic"+"\n HP: "+Game.availableHeroes.get(5).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(5).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(5).getActionsAvailable());
	    	RadioButton hero7=new RadioButton();
	    	hero7.setToggleGroup(g);
	    	Image image7 = new Image("views\\david.png"); // Replace with the actual path to your image file
	        ImageView imageView7 = new ImageView(image7);
	 		imageView7.setPreserveRatio(true);
	        imageView7.setFitHeight(200); 
	        hero7.setGraphic(imageView7);
	        hero7.graphicProperty();
	  	    hero7.autosize();
	    	hero7.setOnAction(e -> {
	    		selected =Game.availableHeroes.get(6);
	            hero7.getStyleClass().add("RadioButton-clicked");
	    	});
	    	hero7.setText(Game.availableHeroes.get(6).getName() + "\nType Fighter"+"\n HP: "+Game.availableHeroes.get(6).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(6).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(6).getActionsAvailable());
	    	RadioButton hero8=new RadioButton();
	    	hero8.setToggleGroup(g);
	    	Image image8 = new Image("views\\henry.png"); // Replace with the actual path to your image file
	        ImageView imageView8 = new ImageView(image8);
	 		imageView8.setPreserveRatio(true);
	        imageView8.setFitHeight(200); 
	        hero8.setGraphic(imageView8);
	        hero8.graphicProperty();
	  	    hero8.autosize();
	    	hero8.setOnAction(e -> {
	    		selected =Game.availableHeroes.get(7);
	            hero8.getStyleClass().add("Radioutton-clicked");
	    	});
	    	hero8.setText(Game.availableHeroes.get(7).getName()+"\nType Medic"+"\n HP: "+Game.availableHeroes.get(7).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(7).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(7).getActionsAvailable());
	   	
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
	    	hero1.setMinSize(100, 100);
	    	hero2.setMinSize(100, 100);	
	    	hero3.setMinSize(100, 100);
	    	hero4.setMinSize(100, 100);
	    	hero5.setMinSize(100, 100);
	    	hero6.setMinSize(100, 100);
	    	hero7.setMinSize(100, 100);
	    	hero8.setMinSize(100, 100);
	    	
	    	BorderPane bord= new BorderPane();
	    	 ImageView background = new ImageView(new Image("views\\The-Last-of-Us-Part-2-Landscape-Header - Copy.png", 1980,
	                 1080, true, true));
	         bord.getChildren().add(background);
	         bord.setCenter(heroesSelector);
	         
	         VBox talking=new VBox();
	         
	         Button go= new Button();
	         go.setText("Go to the game");
	         go.getStyleClass().add("ipad-dark-grey");
	         go.setOnAction(e -> {
	 			try {
	 				MainView sv = new MainView(primaryStage, selected);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        	
		        });
	         
	           	         
	         Label choose=new Label(" Welcome to the apocalypse. The fate of the world rests in your hands. Who will you become in this desperate fight for survival?");
	         choose.setTextAlignment(TextAlignment.CENTER);
	         choose.setWrapText(true);
	         choose.setPrefWidth(590);
	         choose.setTextAlignment(TextAlignment.CENTER);
	         
	         
	         talking.getChildren().addAll(choose,go);
	         talking.setSpacing(20);
	         go.setAlignment(Pos.CENTER);
	         bord.setLeft(talking);
	         bord.setMinSize(900, 900);
	         talking.setAlignment(Pos.TOP_CENTER);
	         Scene SelectingHero =new Scene(bord);
	         SelectingHero.getStylesheets().add(
	                  getClass().getResource("selection.css").toExternalForm());
	         	primaryStage.setTitle("The Last of Us");
		 		primaryStage.setMaximized(true);
		    	primaryStage.setScene(SelectingHero);		    	  		    	  
	    	primaryStage.show();
	    	
	    }
}