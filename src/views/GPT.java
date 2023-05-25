package views;

import java.util.concurrent.CountDownLatch;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GPT extends Application {

	@Override
	public void start(Stage primaryStage) {
		Label label = new Label("Hello, JavaFX!");

		StackPane root = new StackPane(label);
		Scene scene = new Scene(root, 300, 200);

		primaryStage.setScene(scene);
		primaryStage.show();

		CountDownLatch latch = new CountDownLatch(1);

		// Apply CSS style for 3 seconds
		label.setStyle("-fx-background-color: yellow;");

		Timeline timeline = new Timeline();
		KeyFrame keyFrame = new KeyFrame(Duration.seconds(3), event -> {
			// Reset CSS style after 3 seconds
				label.setStyle("");
				latch.countDown();
			});
		timeline.getKeyFrames().add(keyFrame);
		timeline.play();

		try {
			latch.await(); // Wait until the latch count reaches zero
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Continue with the rest of the program after the time ends
		System.out.println("Time is up!");
	}

	public static void main(String[] args) {
		launch(args);
	}
}
