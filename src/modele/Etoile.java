package modele;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public class Etoile extends Figure {
    private Point2D centre;
    public Etoile(Color coul, double epaisseur, double x, double y) {
        super(coul, epaisseur);
        centre = new Point2D(x, y);
    }
    public Etoile() {
    	super();
    }
    public Point2D centre() {
        return centre;
    }
    public void centre(double x, double y) {
    	centre = new Point2D(x, y);
    }
    public void enregistreDans(DataOutputStream fichier) throws Exception {
        fichier.writeByte(2);
        fichier.writeDouble(epaisseur);
        fichier.writeDouble(couleur.getRed());
        fichier.writeDouble(couleur.getGreen());
        fichier.writeDouble(couleur.getBlue());
        fichier.writeDouble(centre.getX());
        fichier.writeDouble(centre.getY());
        fichier.writeInt(contenu.size());
        for(Point2D pt:contenu) {
            fichier.writeDouble(pt.getX());
            fichier.writeDouble(pt.getY());
        }
    }
    public void chargeDepuis(DataInputStream fichier) throws Exception {
        epaisseur = fichier.readDouble();
        double r, v, b;
        r = fichier.readDouble();
        v = fichier.readDouble();
        b = fichier.readDouble();
        couleur = Color.color(r, v, b);
        double x = fichier.readDouble();
        double y = fichier.readDouble();
        centre = new Point2D(x, y);
        int nbPts = fichier.readInt();
        for(int i=0; i<nbPts; i++) {
            x = fichier.readDouble();
            y = fichier.readDouble();
            ajoute(x, y);
        }
    }
}
