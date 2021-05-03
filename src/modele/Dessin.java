package modele;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Dessin implements Iterable<Figure> {
	private List<Figure> contenu;
	private boolean modifi�;
	private File fichier;
	public Dessin() {
		contenu = new LinkedList<Figure>();
        fichier = null;
        modifi� = false;
	}
	public void ajoute(Figure f) {
		contenu.add(f);
        modifi� = true;
	}
	public int nombreDElements() {
		return contenu.size();
	}
	public Figure element(int index) {
		return contenu.get(index);
	}
	public void retire(int index) {
		contenu.remove(index);
	}
	public void vider() {
		contenu.clear();
        modifi� = false;
        fichier = null;
	}
	public Iterator<Figure> iterator() {
		return contenu.iterator();
	}
    public boolean enregistre() {
        return enregistreSous(fichier);
    }
    public boolean enregistreSous(File f) {
    // renvoie vrai en cas de succ�s, faux en cas d'�chec
        fichier = f;
        try {
            DataOutputStream dos;
            dos = new DataOutputStream(new FileOutputStream(f));
            enregistreDans(dos);
            dos.close();
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    public boolean charge(File f) {
    // renvoie vrai en cas de succ�s, faux en cas d'�chec
        try {
            DataInputStream dis;
            dis = new DataInputStream(new FileInputStream(f));
            vider();
            chargeDepuis(dis);
            dis.close();
            fichier = f;
            return true;
        } catch(Exception e) {
            return false;
        }
    }
    private void enregistreDans(DataOutputStream dos) throws Exception {
        dos.writeInt(contenu.size());
        for(Figure fig:contenu) {
            fig.enregistreDans(dos);
        }
        modifi� = false;
    }
    private void chargeDepuis(DataInputStream dis) throws Exception {
        int nbFig = dis.readInt();
        for(int i=0; i<nbFig; i++) {
            byte type = dis.readByte();
            Figure fig;
            switch(type) {
                case  1: fig = new Trace(); break;
                case  2: fig = new Etoile(); break;
                default: fig = null;
            }
            fig.chargeDepuis(dis);
            contenu.add(fig);
        }
        modifi� = false;
    }
    public boolean enFichier() {
        return fichier != null;
    }
    public String nomDeFichier() {
        String nom = (modifi�) ? "*" : "";
        if (fichier == null) nom += "Sans nom";
        else nom += fichier.getName();
        return nom;
    }
    public boolean modifi�() {
        return modifi�;
    }
}
