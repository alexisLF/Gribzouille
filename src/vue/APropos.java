package vue;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class APropos extends Stage {
	public APropos() {
		super();
		setTitle("A propos de Gribouille");
        Text txt1 = new Text(30, 30, "Mini application de dessin !");
        txt1.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
        Text txt2 = new Text(0, 70, "par Philippe, Jean-Christophe et Miryem");
        txt2.setFont(Font.font("Times", FontWeight.NORMAL, FontPosture.REGULAR, 18));
        Text txt3 = new Text(70, 100, "Version du 15 mai 2017");
        txt3.setFont(Font.font("Courier New", FontWeight.NORMAL, FontPosture.ITALIC, 12));
        Group contenu = new Group();
        contenu.getChildren().addAll(txt1, txt2, txt3);
        setScene(new Scene(contenu));
        sizeToScene();
        setResizable(false);
        initModality(Modality.APPLICATION_MODAL);
	}
}
