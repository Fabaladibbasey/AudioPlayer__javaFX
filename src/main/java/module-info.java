module com.example.practicefx {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.media;
	requires org.controlsfx.controls;
	requires com.dlsc.formsfx;
	requires org.kordamp.ikonli.javafx;
	requires org.kordamp.bootstrapfx.core;

	opens com.example.practicefx to javafx.fxml;
	exports com.example.practicefx;
}