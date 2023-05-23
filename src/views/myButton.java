package views;

import javafx.scene.control.Button;

public class myButton extends Button {
    public int x; int y;
    public myButton(int x, int y) {
        this.x = 15 - x; this.y = y;
    }
}
