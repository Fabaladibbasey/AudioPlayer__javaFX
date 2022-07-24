package com.example.practicefx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MusicPlayerApplication extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(MusicPlayerApplication.class.getResource("MusicPlayer-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load());
		stage.setTitle("Music player");
		stage.setScene(scene);
		stage.show();

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent windowEvent) {
				Platform.exit();
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		launch();
	}
}