package vue;

import application.Gribouille;
import controleur.Controleur;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.Dessin;

public class PanneauDeDessin extends BorderPane {
	private BarreDeMenus bM;
	private	ZoneDeDessin zD;
	private BarreDEtat bE;
	private Dessin dessin;
	private Stage fenetre;
	public PanneauDeDessin(Dessin d, Stage s) {
		super();
		dessin = d;
		fenetre = s;
		zD = new ZoneDeDessin(d);
		Pane p = new Pane(zD);
		setCenter(p);
		zD.dimensionnePour(p);
		bM = new BarreDeMenus(d, this);
		setTop(bM);
		bE = new BarreDEtat();
		setBottom(bE);
	}
	public void controleur(Controleur c) {
		bM.controleur(c);
	}
	public ZoneDeDessin zoneDeDessin() {
		return zD;
	}
	public int epaisseur() {
		return zD.epaisseur();
	}
	public Color couleur() {
		return zD.couleur();
	}
	public void outilTrace(String nomOutil) {
		bM.selectionneTrace();
		bE.afficheOutil(nomOutil);
	}
	public void outilEtoile(String nomOutil) {
		bM.selectionneEtoile();
		bE.afficheOutil(nomOutil);
	}
	public void changeCouleur(Color nouvelleCouleur, String nomCouleur) {
		zD.changeCouleur(nouvelleCouleur);
		bE.afficheCouleur(nomCouleur);
	}
	public void changeEpaisseur(int nouvelleEpaisseur) {
		zD.changeEpaisseur(nouvelleEpaisseur);
		bE.afficheEpaisseur(nouvelleEpaisseur);
	}
	public void dessineTrait(double x1, double y1, double x2, double y2) {
		zD.dessineTrait(x1, y1, x2, y2);
	}
	public void dessineTraitCalque(double x1, double y1, double x2, double y2) {
		zD.dessineTraitCalque(x1, y1, x2, y2);
	}
	public void effaceCalque() {
		zD.effaceCalque();
	}
	public void afficheCoordonnees(double x, double y) {
		bE.afficheCoordonnees(x, y);
	}
	public void activeEnregistrer() {
		bM.activeEnregistrer();
	}
	public void desactiveEnregistrer() {
		bM.desactiveEnregistrer();
	}
    public void miseAJourTitre() {
        fenetre.setTitle(dessin.nomDeFichier() + " - " + Gribouille.nomApplication);
    }
}
