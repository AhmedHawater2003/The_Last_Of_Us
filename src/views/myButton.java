package views;

import javafx.scene.control.Button;

public class myButton extends Button {
    public int x; int y;
    public myButton(int x, int y) {
        this.x = 14 - x; this.y = y;
    }
}
