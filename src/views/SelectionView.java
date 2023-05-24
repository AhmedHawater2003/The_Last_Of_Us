package views;

import java.io.IOException;
import java.util.*;

import com.sun.corba.se.impl.orbutil.graph.Node;

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


public class SelectionView extends Application {
	 public static final double WIDTH = Screen.getPrimary().getBounds()
	            .getWidth();
	    public static final double HEIGHT = Screen.getPrimary().getBounds()
	            .getHeight();
	
	    
	    public static void main(String[] args) throws Exception{
	    	Game.loadHeroes("Heros.csv");
	    	launch(args);
	    }
	    public void start(Stage primaryStage) {
	    	GridPane heroesSelector= new GridPane();
	    	Button hero1=new Button();
	    	hero1.setText("Name: "+Game.availableHeroes.get(0).getName()+"\nType: Fighter" +"\n HP: "+Game.availableHeroes.get(0).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(0).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(0).getActionsAvailable());
	    	Button hero2=new Button();
	    	hero2.setText("Name: "+Game.availableHeroes.get(1).getName()+"\nType: Medic"+"\n HP: "+Game.availableHeroes.get(1).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(1).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(1).getActionsAvailable());
	    	Button hero3=new Button();
	    	hero3.setText("Name: "+Game.availableHeroes.get(2).getName()+"\nType Explorer"+"\n HP: "+Game.availableHeroes.get(2).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(2).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(2).getActionsAvailable());
	    	Button hero4=new Button();
	    	hero4.setText("Name: "+Game.availableHeroes.get(3).getName()+"\nType Explorer"+"\n HP: "+Game.availableHeroes.get(3).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(3).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(3).getActionsAvailable());
	    	Button hero5=new Button();
	    	hero5.setText("Name: "+Game.availableHeroes.get(4).getName()+"\nType Explorer"+"\n HP: "+Game.availableHeroes.get(4).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(4).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(4).getActionsAvailable());
	    	Button hero6=new Button();
	    	hero6.setText("Name: "+Game.availableHeroes.get(5).getName()+"\nType Medic"+"\n HP: "+Game.availableHeroes.get(5).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(5).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(5).getActionsAvailable());
	    	Button hero7=new Button();
	    	hero7.setText("Name: "+Game.availableHeroes.get(6).getName() + "\nType Fighter"+"\n HP: "+Game.availableHeroes.get(6).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(6).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(6).getActionsAvailable());
	    	Button hero8=new Button();
	    	hero8.setText("Name: "+Game.availableHeroes.get(7).getName()+"\nType Medic"+"\n HP: "+Game.availableHeroes.get(7).getMaxHp()+"\n AttackDmg: "+Game.availableHeroes.get(7).getAttackDmg()+"\n MaxActions: " +Game.availableHeroes.get(7).getActionsAvailable());
	    	heroesSelector.setConstraints(hero1, 0, 0);
	    	heroesSelector.getChildren().add(hero1);
	    	heroesSelector.setConstraints(hero2, 1, 0);
	    	heroesSelector.getChildren().add(hero2);
	    	heroesSelector.setConstraints(hero3, 2, 0);
	    	heroesSelector.getChildren().add(hero3);
	    	heroesSelector.setConstraints(hero4, 3, 0);
	    	heroesSelector.getChildren().add(hero4);
	    	heroesSelector.setConstraints(hero5, 0, 1);
	    	heroesSelector.getChildren().add(hero5);
	    	heroesSelector.setConstraints(hero6, 1, 1);
	    	heroesSelector.getChildren().add(hero6);
	    	heroesSelector.setConstraints(hero7, 2, 1);
	    	heroesSelector.getChildren().add(hero7);
	    	heroesSelector.setConstraints(hero8, 3, 1);
	    	heroesSelector.getChildren().add(hero8);
	    	heroesSelector.setAlignment(Pos.CENTER);
	    	hero1.setMinSize(200, 200);
	    	hero2.setMinSize(200, 200);	
	    	hero3.setMinSize(200, 200);
	    	hero4.setMinSize(200, 200);
	    	hero5.setMinSize(200, 200);
	    	hero6.setMinSize(200, 200);
	    	hero7.setMinSize(200, 200);
	    	hero8.setMinSize(200, 200);
	    	//zheroesSelector.getChildren().addAll(hero1,hero2,hero3,hero4,hero5,hero6,hero7,hero8);
	    	BorderPane bord= new BorderPane();
	    	 ImageView background = new ImageView(new Image("views\\BG1.jpg", 1980,
	                 1080, true, true));
	         bord.getChildren().add(background);
	         bord.setCenter(heroesSelector);
	    	Scene SelectingHero =new Scene(bord);
	    
	    	primaryStage.setScene(SelectingHero);
	    	primaryStage.show();
	    }
}
