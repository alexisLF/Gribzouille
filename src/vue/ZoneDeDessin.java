package vue;

import java.util.Iterator;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import modele.Dessin;
import modele.Etoile;
import modele.Figure;
import modele.Trace;

public class ZoneDeDessin extends StackPane {
	private Canvas provisoire, definitif;
	private Dessin dessin;
	private Color couleur;
	private int epaisseur;
	public ZoneDeDessin(Dessin d) {
		super();
		setStyle("-fx-background-color: white");
		definitif = new Canvas();
		provisoire = new Canvas();
		getChildren().add(definitif);
		getChildren().add(provisoire);
		dessin = d;
		couleur = Color.BLACK;
		epaisseur = 1;
	}
	public void dimensionnePour(Pane p) {
        definitif.widthProperty().bind(p.widthProperty());
		provisoire.widthProperty().bind(p.widthProperty());
        definitif.heightProperty().bind(p.heightProperty());
		provisoire.heightProperty().bind(p.heightProperty());
        definitif.widthProperty().addListener(evt -> dessineContenu());
        definitif.heightProperty().addListener(evt -> dessineContenu());
	}
	public void effaceCalque() {
		provisoire.getGraphicsContext2D().clearRect(0,  0,  getWidth(), getHeight());
	}
	public void changeCouleur(Color nouvelleCouleur) {
		couleur = nouvelleCouleur;
	}
	public Color couleur() {
		return couleur;
	}
	public void changeEpaisseur(int nouvelleEpaisseur) {
		epaisseur = nouvelleEpaisseur;
	}
	public int epaisseur() {
		return epaisseur;
	}
	public void dessineTraitCalque(double x1, double y1, double x2, double y2) {
		GraphicsContext gc = provisoire.getGraphicsContext2D();
		gc.setLineWidth(epaisseur);
		gc.setStroke(couleur);
		gc.strokeLine(x1, y1, x2, y2);
	}
	public void dessineTrait(double x1, double y1, double x2, double y2) {
		GraphicsContext gc = definitif.getGraphicsContext2D();
		gc.setLineWidth(epaisseur);
		gc.setStroke(couleur);
		gc.strokeLine(x1, y1, x2, y2);
	}
	private void dessine(Dessin d, GraphicsContext g) {
        for (Figure f : d) {
        	g.setStroke(f.couleur());
        	g.setLineWidth(f.epaisseur());
        	dessine(f, g);
        }
	}
	private void dessine(Figure f, GraphicsContext g) {
        if (f instanceof Trace)   dessine((Trace)f, g);
        if (f instanceof Etoile)  dessine((Etoile)f, g);
	}
	private void dessine(Trace t, GraphicsContext g) {
    	Point2D pt;
    	Iterator<Point2D> it = t.iterator();
    	pt = it.next();
    	double x = pt.getX(), y = pt.getY();
    	while(it.hasNext()) {
    		pt = it.next();
    		g.strokeLine(x, y, pt.getX(), pt.getY());
    		x = pt.getX();
    		y = pt.getY();
    	}
	}
	private void dessine(Etoile e, GraphicsContext g) {
		double xc = e.centre().getX();
		double yc = e.centre().getY();
    	for(Point2D pt : e) {
    		g.strokeLine(xc, yc, pt.getX(), pt.getY());
    	}
	}
	public void dessineContenu() {
        GraphicsContext gc = definitif.getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());
        dessine(dessin, gc);
	}
}
