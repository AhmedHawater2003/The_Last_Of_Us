package views;

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
import model.characters.Hero;
import model.characters.Zombie;

public class ViewHelpers {

    public static final double WIDTH = Screen.getPrimary().getBounds()
            .getWidth();
    public static final double HEIGHT = Screen.getPrimary().getBounds()
            .getHeight();

    public static Pane labelWithIconPane(String txt, String iconPath) {
        HBox pane = new HBox();
        Label icon = new Label();
        Label text = new Label(txt);
        Image iconImage = new Image(iconPath);
        ImageView iconView = new ImageView(iconImage);
        iconView.setFitHeight(15);
        iconView.setFitWidth(15);
        icon.setGraphic(iconView);
        pane.getChildren().addAll(icon, text);
        pane.setPadding(new Insets(2, 5, 2, 5));
        pane.setSpacing(10);
        return pane;

    }

    public static Pane selctedHeroInfo(Hero hero) {
        VBox pane = (VBox) availableHeroInfo(hero);
        pane.getChildren().add(labelWithIconPane(hero.getSupplyInventory().toString(), "icons\\heart.png"));
        pane.getChildren().add(labelWithIconPane(hero.getVaccineInventory().toString(), "icons\\heart.png"));
        Zombie target = (Zombie) hero.getTarget();
        pane.getChildren()
                .add(labelWithIconPane(target.getCurrentHp() + " / " + target.getMaxHp(), "icons\\heart.png"));
        return pane;
    }

    public static Pane availableHeroInfo(Hero hero) {
        VBox pane = new VBox();
        String type = hero.getClass().toString().split("\\.")[2];
        pane.getChildren().add(labelWithIconPane(hero.getName(), "icons\\heart.png"));
        pane.getChildren().add(labelWithIconPane(type, "icons\\heart.png"));
        pane.getChildren().add(labelWithIconPane(hero.getCurrentHp() + " / " + hero.getMaxHp(), "icons\\heart.png"));
        pane.getChildren().add(labelWithIconPane(hero.getAttackDmg() + "", "icons\\heart.png"));
        pane.getChildren()
                .add(labelWithIconPane(hero.getActionsAvailable() + " / " + hero.getMaxActions(), "icons\\heart.png"));
        return pane;
    }

    public static Node borderedImage(String imagePath, double imageHigh) {
        Label label = new Label();
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(imageHigh);
        imageView.setPreserveRatio(true);
        label.setGraphic(imageView);
        // label.getStyleClass().add("bordered-image");
        return label;
    }

    public static Pane heroPane(Node heroImage, Node heroInfo) {
        HBox pane = new HBox();
        pane.getChildren().addAll(heroImage, heroInfo);
        pane.setPadding(new Insets(5, 5, 5, 5));
        pane.setSpacing(10);
        pane.getStyleClass().add("heros");
        pane.setAlignment(Pos.CENTER);
        return pane;
    }

    public static Pane availableHeroPane(Hero hero){
        Node heroImage = borderedImage("views\\assasin.png", HEIGHT/7 );
        Node heroInfo = availableHeroInfo(hero);
        Pane pane = heroPane(heroImage, heroInfo);
        pane.getStyleClass().add("available-hero");
        return pane;
    }

    public static Pane selectedHeroPane(Hero hero){
        Node heroImage = borderedImage("views\\assasin.png", HEIGHT/6 );
        Node heroInfo = selctedHeroInfo(hero);
        Pane pane = heroPane(heroImage, heroInfo);
        pane.getStyleClass().add("selected-hero");
        return pane;
    }
}
