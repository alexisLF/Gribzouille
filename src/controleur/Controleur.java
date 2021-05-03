package controleur;

import java.io.File;

import javafx.scene.Cursor;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import modele.Dessin;
import vue.APropos;
import static vue.Dialogues.*;
import vue.PanneauDeDessin;

public class Controleur {
    private Stage fenetre;
    private PanneauDeDessin panneau;
    private EcouteurSouris outil, ecouteurTrace, ecouteurEtoile;
    private EcouteurClavier ecouteurClavier;
    private Dessin dessin;
    public Controleur(Dessin d, PanneauDeDessin p, Stage s) {
        fenetre = s;
        dessin = d;
        panneau = p;
        ecouteurTrace = new EcouteurTrace(dessin, panneau);
        ecouteurEtoile = new EcouteurEtoile(dessin, panneau);
        ecouteurClavier = new EcouteurClavier(this);
        panneau.controleur(this);
        panneau.zoneDeDessin().setOnMouseMoved((MouseEvent evt) -> outil.onMouseMoved(evt));
        panneau.zoneDeDessin().setOnMousePressed((MouseEvent evt) -> outil.onMousePressed(evt));
        panneau.zoneDeDessin().setOnMouseDragged((MouseEvent evt) -> outil.onMouseDragged(evt));
        panneau.zoneDeDessin().setOnMouseEntered((MouseEvent evt) -> fenetre.getScene().setCursor(Cursor.HAND));
        panneau.zoneDeDessin().setOnMouseExited((MouseEvent evt) -> fenetre.getScene().setCursor(Cursor.DEFAULT));
        fenetre.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent evt) -> onKeyPressed(evt));
        fenetre.setOnCloseRequest((WindowEvent evt) -> onClose(evt));
        panneau.afficheCoordonnees(0, 0);
        panneau.changeCouleur(Color.BLACK, "noir");
        panneau.changeEpaisseur(1);
        panneau.miseAJourTitre();
        outilTrace();
    }
    public void onKeyPressed(KeyEvent evt) {
        ecouteurClavier.onKeyPressed(evt);
    }
    public void aPropos() {
        APropos ap = new APropos();
        ap.show();
    }
    public void outilTrace() {
        outil = ecouteurTrace;
        panneau.outilTrace(ecouteurTrace.nom());
    }
    public void outilEtoile() {
        outil = ecouteurEtoile;
        panneau.outilEtoile(ecouteurEtoile.nom());
    }
    public void changeCouleur(Color couleur, String nomCouleur) {
        panneau.changeCouleur(couleur, nomCouleur);
    }
    public void changeEpaisseur(int e) {
        panneau.changeEpaisseur(e);
    }
    public void effacer() {
    	if (confirmationAvecEnregistrementEventuel("Effacer le dessin")) {
	        dessin.vider();
	        panneau.zoneDeDessin().dessineContenu();
	        panneau.miseAJourTitre();
    	}
    }
    public void quitter() {
		if (confirmationAvecEnregistrementEventuel("Quitter Gribouille")) {
			System.exit(0);
		}
    }
	public void onClose(WindowEvent e) {
		if (confirmationAvecEnregistrementEventuel("Fermer Gribouille")) {
			System.exit(0);
		} else {
			e.consume();
		}
	}
    public void ouvrir() {
		if (confirmationAvecEnregistrementEventuel("Ouverture d'un dessin")) {
			FileChooser dialogue = new FileChooser();
			dialogue.getExtensionFilters().addAll(new ExtensionFilter("Fichiers dessin de Gribouille", "*.grb"));
			File fichierChoisi = dialogue.showOpenDialog(null);
			if (fichierChoisi != null) {
				fenetre.getScene().setCursor(Cursor.WAIT);
			    dessin.vider();
			    boolean ok = dessin.charge(fichierChoisi);
				fenetre.getScene().setCursor(Cursor.DEFAULT);
			    if (!ok) {
			        erreur("Ouverture de dessin", "Une erreur s'est produite pendant l'ouverture du dessin.\nLe dessin n'a pas été chargé !");
			        dessin.vider();
			    }
			    panneau.activeEnregistrer();
			    panneau.zoneDeDessin().dessineContenu();
			    panneau.miseAJourTitre();
			}
		}
    }
    public boolean enregistrerSous() {
        FileChooser dialogue = new FileChooser();
        dialogue.getExtensionFilters().addAll(new ExtensionFilter("Fichiers dessin de Gribouille", "*.grb"));
        File fichierChoisi = dialogue.showSaveDialog(null);
        boolean ok;
        if (fichierChoisi != null) {
			fenetre.getScene().setCursor(Cursor.WAIT);
        	ok = dessin.enregistreSous(fichierChoisi);
			fenetre.getScene().setCursor(Cursor.DEFAULT);
            if (ok) {
                panneau.activeEnregistrer();
                panneau.miseAJourTitre();
                information("Enregistrement de dessin", "Le dessin a bien été enregistré dans le fichier\n" + dessin.nomDeFichier() + ".");
            } else {
                erreur("Enregistrement de dessin", "Une erreur s'est produite pendant l'enregistrement du dessin.\nLe dessin n'a pas été enregistré !");
            }
        } else {
        	ok = false;
        }
        return ok;
    }
    public boolean enregistrer() {
		fenetre.getScene().setCursor(Cursor.WAIT);
        boolean ok = dessin.enregistre();
		fenetre.getScene().setCursor(Cursor.DEFAULT);
        if (ok) {
            panneau.miseAJourTitre();
            information("Enregistrement de dessin", "Le dessin a bien été enregistré dans le fichier\n" + dessin.nomDeFichier() + ".");
        } else {
            erreur("Enregistrement de dessin", "Une erreur s'est produite pendant l'enregistrement du dessin.\nLe dessin n'a pas été enregistré !");
        }
        return ok;
    }
    private boolean confirmationAvecEnregistrementEventuel(String nomOp) {
        if (dessin.modifié()) {
            switch (confirmation(nomOp)) {
	            case CONFIRMATION_AVEC_ENREGISTREMENT :
	            	if (dessin.enFichier()) {
            			if (!enregistrer()) return false;
	            	} else {
	            		if (!enregistrerSous()) return false;
	            	}
	            case CONFIRMATION_SANS_ENREGISTREMENT :
	            	return true;
	            case ABANDON :
	            	return false;
            }
        }
        return true;
    }
}
