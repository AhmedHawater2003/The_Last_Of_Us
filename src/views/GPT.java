package views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GPT extends Application {
    public void start(Stage primaryStage) {
        ScrollPane scrollPane = new ScrollPane();
        VBox content = new VBox();
        Label label1 = new Label("Label 1");
        Label label2 = new Label("Label 2");
        content.getChildren().addAll(label1, label2);
        scrollPane.setContent(content);

        Scene scene = new Scene(scrollPane, 400, 300);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}