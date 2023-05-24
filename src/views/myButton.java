package views;

import java.util.HashMap;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import model.characters.Hero;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;

public class myButton extends Button {
    Cell cell;
    static HashMap<String, String> herosIcons = iconsDict();
    public static final double WIDTH = Screen.getPrimary().getBounds()
            .getWidth();
    public static final double HEIGHT = Screen.getPrimary().getBounds()
            .getHeight();

    public static double mapWidth = WIDTH - WIDTH / 6, mapHeight = HEIGHT - HEIGHT / 15;

    public static HashMap<String, String> iconsDict() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Ellie Williams", "views\\ellie.png");
        map.put("Joel Miller", "views\\joel.png");
        map.put("Tess", "views\\tess.png");
        map.put("Riley Abel", "views\\riley.png");
        map.put("Tommy Miller", "views\\tommy.png");
        map.put("Bill", "views\\bill.png");
        map.put("David", "views\\david.png");
        map.put("Henry Burell", "views\\henry.png");
        return map;

    }

    public myButton(Cell cell) {
        this.cell = cell;
        this.setPrefSize(mapWidth / 15, mapHeight / 15);
        if(cell instanceof CharacterCell) {
            if(((CharacterCell) cell).getCharacter() instanceof Hero) {
                this.asHero();
            } else if (((CharacterCell) cell).getCharacter() instanceof Zombie){
                this.asZombie();
            }
            else {
                this.asEmpty();
            }
        } else if(cell instanceof CollectibleCell){
            if(((CollectibleCell)cell).getCollectible() instanceof Supply){
                this.asSupply();
            }
            else{
                this.asVaccine();
            }
        }
        else{
            this.asTrap();
        }
    }

    public ImageView getImageView(String path) {
        ImageView view = new ImageView(path);
        view.setFitHeight(mapHeight / 23);
        view.setFitWidth(mapWidth / 23);
        return view;
    }

    public void asHero() {
        String name = (((CharacterCell) cell).getCharacter()).getName();
        ImageView view = getImageView(herosIcons.get(name));
        this.setGraphic(view);
    }

    public void asZombie() {
        ImageView view = getImageView("views\\zombie.png");
        this.setGraphic(view);
    }

    public void asEmpty() {
        if (!cell.isVisible()) {
            this.setEffect(new javafx.scene.effect.GaussianBlur(10.5));
            this.setId("blured");
        }
    }

    public void asSupply() {
        ImageView view = getImageView("icons\\supply.png");
        this.setGraphic(view);
    }

    public void asVaccine() {
        ImageView view = getImageView("icons\\vaccine.png");
        this.setGraphic(view);
    }

    public void asTrap() {
        ImageView view = getImageView("views\\assasin.png");
        this.setGraphic(view);
    }
}
