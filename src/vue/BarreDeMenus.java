package vue;

import controleur.Controleur;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.ToggleGroup;
import modele.Dessin;

public class BarreDeMenus extends MenuBar {
	private RadioMenuItem choixTrace, choixEtoile;
	private Controleur controleur;
	private MenuItem optionEnregistrer;
	public void controleur(Controleur c) {
		controleur = c;
	}
	public BarreDeMenus(Dessin d, PanneauDeDessin p) {
		super();
		Menu m;
		MenuItem mi;
		m = ajouteMenu("Gribouille");
		mi = ajouteElement("A propos", m);
		mi.setOnAction(evt -> controleur.aPropos());
		ajouteSeparateur(m);
		mi = ajouteElement("Quitter", m);
		mi.setOnAction(evt -> controleur.quitter());
		m = ajouteMenu("Dessin");
		mi = ajouteElement("Ouvrir...", m);
		mi.setOnAction(evt -> controleur.ouvrir());
		mi = ajouteElement("Enregistrer", m);
		mi.setOnAction(evt -> controleur.enregistrer());
		optionEnregistrer = mi;
		optionEnregistrer.setDisable(true);
		mi = ajouteElement("Enregistrer sous...", m);
		mi.setOnAction(evt -> controleur.enregistrerSous());
		ajouteSeparateur(m);
		mi = ajouteElement("Effacer", m);
		mi.setOnAction(evt -> controleur.effacer());
		m = ajouteMenu("Outils");
		ToggleGroup groupeOutils = new ToggleGroup();
		choixTrace = ajouteRadioElement("Trace", m, groupeOutils);
		choixTrace.setOnAction(evt -> controleur.outilTrace());
		choixEtoile = ajouteRadioElement("Etoile", m, groupeOutils);
		choixEtoile.setOnAction(evt -> controleur.outilEtoile());
		choixTrace.setSelected(true);
		setUseSystemMenuBar(true); //pour MacOS où la barre de menus n'apparait pas en haut de la fenêtre mais de l'écran
	}
	private Menu ajouteMenu(String nom) {
		Menu m = new Menu(nom);
		getMenus().add(m);
		return m;
	}
	private MenuItem ajouteElement(String nom, Menu m){
		MenuItem mi;
		mi = new MenuItem(nom);
		m.getItems().add(mi);
		return mi;
	}
	private RadioMenuItem ajouteRadioElement(String nom, Menu m, ToggleGroup g) {
		RadioMenuItem mi;
		mi = new RadioMenuItem(nom);
		mi.setToggleGroup(g);
		m.getItems().add(mi);
		return mi;
	}
	private void ajouteSeparateur(Menu m){
		m.getItems().add(new SeparatorMenuItem());
	}
	public void selectionneTrace() {
		choixTrace.setSelected(true);
	}
	public void selectionneEtoile() {
		choixEtoile.setSelected(true);
	}
	public void activeEnregistrer() {
		optionEnregistrer.setDisable(false);
	}
	public void desactiveEnregistrer() {
		optionEnregistrer.setDisable(true);
	}
}
