package controleur;

import javafx.scene.input.MouseEvent;
import modele.Dessin;
import modele.Figure;
import vue.PanneauDeDessin;

public abstract class EcouteurSouris {
	protected Dessin dessin;
	protected PanneauDeDessin panneau;
	protected Figure f;
	protected double x, y;
	public EcouteurSouris(Dessin d, PanneauDeDessin p) {
		dessin = d;
		panneau = p;
	}
	public abstract String nom();
	public void memorisePosition(MouseEvent evt) {
		x = evt.getX();
		y = evt.getY();
	}
	public void onMouseMoved(MouseEvent evt) {
		panneau.afficheCoordonnees(evt.getX(), evt.getY());
	}
	public void onMousePressed(MouseEvent evt) {
		panneau.afficheCoordonnees(evt.getX(), evt.getY());
	}
	public void onMouseDragged(MouseEvent evt) {
		panneau.afficheCoordonnees(evt.getX(), evt.getY());
	}
}
