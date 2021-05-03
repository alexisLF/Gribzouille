package controleur;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import modele.Dessin;
import modele.Etoile;
import vue.PanneauDeDessin;

public class EcouteurEtoile extends EcouteurSouris {
	public EcouteurEtoile(Dessin d, PanneauDeDessin p) {
		super(d, p);
	}
	public String nom() {
		return "étoile";
	}
	public void onMousePressed(MouseEvent evt) {
		super.onMousePressed(evt);
		if (evt.getButton() != MouseButton.PRIMARY) return;
		memorisePosition(evt);
		f = new Etoile(panneau.couleur(), panneau.epaisseur(), evt.getX(), evt.getY());
		dessin.ajoute(f);
		panneau.miseAJourTitre();
	}
	public void onMouseDragged(MouseEvent evt) {
		super.onMouseDragged(evt);
		if (evt.getButton() != MouseButton.PRIMARY) return;
    	panneau.dessineTrait(x,  y, evt.getX(), evt.getY());
    	f.ajoute(evt.getX(), evt.getY());
	}
}
