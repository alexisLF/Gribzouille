package controleur;

import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class EcouteurClavier {
	private Controleur ctrl;
	public EcouteurClavier(Controleur c) {
		ctrl = c;
	}
	public void onKeyPressed(KeyEvent evt) {
        if (evt.getText().equals("")) return;
		char choix = Character.toUpperCase(evt.getText().charAt(0));
		if (Character.isDigit(choix)) {
			int valeur = Character.digit(choix, 10);
			if (valeur != 0) ctrl.changeEpaisseur(valeur);
		} else
			switch(choix) {
				//les couleurs
				case 'R' : ctrl.changeCouleur(Color.RED,    "rouge"); break;
				case 'V' : ctrl.changeCouleur(Color.GREEN,   "vert"); break;
				case 'B' : ctrl.changeCouleur(Color.BLUE,    "bleu"); break;
				case 'J' : ctrl.changeCouleur(Color.YELLOW, "jaune"); break;
				case 'N' : ctrl.changeCouleur(Color.BLACK,   "noir"); break;
				//les outils de dessin
				case 'T' : ctrl.outilTrace(); break;
				case 'E' : ctrl.outilEtoile(); break;
			}
	}
}
