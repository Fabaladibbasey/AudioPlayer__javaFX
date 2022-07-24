package com.example.practicefx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class MusicPlayerController implements Initializable {

	@FXML
	private Button buttonPLay, buttonPause, buttonPrev, buttonNext;

	@FXML
	private ComboBox<String> speedOption ;

	@FXML
	private int[] speedOptions = {25, 50, 75, 100, 125, 150, 175, 200};

	@FXML
	private Label musicTitle;

	@FXML
	private Slider volume;

	@FXML
	private ProgressBar progressBar;

	@FXML
	private File directory;
	@FXML
	private File[] files;

	@FXML
	private ArrayList<File> audios;

	@FXML
	private Media media;

	@FXML
	private MediaPlayer mediaPlayer;

	@FXML
	private int audioIndex;

	@FXML
	private Timer timer;
	private TimerTask timerTask;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		audioIndex = 0;
		audios = new ArrayList<>();
		directory = new File("Music");
		files = directory.listFiles();
		if (files == null) return;
		for (File file : files) {
			audios.add(file);
		}

		media = new Media(audios.get(audioIndex).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		musicTitle.setText(audios.get(audioIndex).getName());

		for (Integer opt : speedOptions) {
			speedOption.getItems().add(opt + "%");
		}
		speedOption.setOnAction(this::speed);

		volume.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
				mediaPlayer.setVolume(volume.getValue() * 0.01);
			}
		});
	}

	@FXML
	private void speed(ActionEvent actionEvent) {
		if (speedOption.getValue() == null) speedOption.setValue("100%");
		mediaPlayer.setRate(Integer.parseInt(speedOption.getValue().substring(0, speedOption.getValue().length() - 1)) * 0.01);
	}



	public void play() {
		speed(null);
		mediaPlayer.play();
		beginTimer();
	}

	public void pause() {
		mediaPlayer.pause();
	}

	public void previous() {
		mediaPlayer.pause();
		if (audioIndex <= 0) {
			audioIndex = audios.size();
		}
		audioIndex--;

		media = new Media(audios.get(audioIndex).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		musicTitle.setText(audios.get(audioIndex).getName());
		play();
	}

	public void next() {
		mediaPlayer.pause();
		if (audioIndex >= audios.size() - 1) {
			audioIndex = -1;
		}
			audioIndex++;
		media = new Media(audios.get(audioIndex).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		musicTitle.setText(audios.get(audioIndex).getName());
		play();
	}

	public void beginTimer(){
		timer = new Timer();
		timerTask = new TimerTask() {
			@Override
			public void run() {
				double currentTime = mediaPlayer.getCurrentTime().toSeconds();
				double endTime = media.getDuration().toSeconds();
				progressBar.setProgress(currentTime / endTime);
			}
		};

		timer.scheduleAtFixedRate(timerTask, 100, 500);
	}

	public void cancelTimer(){

	}
}