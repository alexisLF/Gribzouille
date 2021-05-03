package modele;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

public abstract class Figure implements Iterable<Point2D> {
    protected Color couleur;
    protected double epaisseur;
    protected List<Point2D> contenu;
    public Figure() {
        couleur = Color.BLACK;
    	contenu = new LinkedList<Point2D>();
    	epaisseur = 1.0;
    }
    public Figure(Color couleur, double epaisseur) {
        this.couleur = couleur;
        contenu = new LinkedList<Point2D>();
        this.epaisseur = epaisseur;
    }
    public Color couleur() {
        return couleur;
    }
    public void couleur(Color c) {
    	couleur = c;
    }
    public double epaisseur(){
    	return epaisseur;
    }
    public void epaisseur(double epaisseur){
    	this.epaisseur = epaisseur;
    }
    public int nombreDElements() {
        return contenu.size();
    }
    public void ajoute(double x, double y) {
        contenu.add(new Point2D(x, y));
    }
    public Point2D element(int index) {
        return contenu.get(index);
    }
    public Iterator<Point2D> iterator() {
        return contenu.iterator();
    }
    public abstract void enregistreDans(DataOutputStream fichier) throws Exception;
    public abstract void chargeDepuis(DataInputStream fichier) throws Exception;
}
