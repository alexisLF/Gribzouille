package application;

import controleur.Controleur;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modele.Dessin;
import vue.PanneauDeDessin;

public class Gribouille extends Application {
	public static final String nomApplication = "Gribouille";
	public static void main(String[] args) {
	    Application.launch(args);
	}
	public void start(Stage stage) {
		Dessin dessin = new Dessin();
		PanneauDeDessin panneau = new PanneauDeDessin(dessin, stage);
        stage.setScene(new Scene(panneau));
		new Controleur(dessin, panneau, stage);
        stage.setWidth(600);
        stage.setHeight(400);
        stage.setMinWidth(450);
        stage.setMinHeight(300);
        stage.show();
	}
}
