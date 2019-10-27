import java.util.LinkedList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

/**
 * Vue principale du jeu.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class VueJeu implements Vue {
  @FXML
  private BorderPane borderPane;
  private Group noeud;
  private List<Dessinable> liste;
  private Camera camera;
  private Scale scale;
  private Translate trans;

  /**
   * Constructeur de la vue du jeu.
   */
  public VueJeu() {
    liste = new LinkedList<Dessinable>();
    camera = new Camera();
    scale = new Scale(0, 0, 0, 0);
    trans = new Translate(0, 0);
  }

  /**
   * Retourne le chemin vers le fichier FXML de la vue.
   *
   * @return Chemin vers FXML.
   */
  public String getFXML() {
    return "/res/Jeu.fxml";
  }

  /**
   * Ajoute tous les corps dessinables dans la vue.
   */
  public void initialiser(BorderPane pane) {
    borderPane = pane;
    Pane p = (Pane) borderPane.lookup("#pane");
    Rectangle rect = new Rectangle(p.getWidth(), p.getHeight());
    p.setClip(rect);
    camera.setGrandeurs(p.getWidth(), p.getHeight());
    initialiserCorps();
  }

  public void initialiserCorps() {
    Pane p = (Pane) borderPane.lookup("#pane");
    p.getChildren().remove(noeud);

    noeud = new Group();
    liste.clear();
    noeud.getTransforms().add(scale);
    noeud.getTransforms().add(trans);
    p.getChildren().add(noeud);

    List<Corps> listTemp = ContPrincipal.reqInstance().getCorps();
    if (listTemp.size() == 0) {
      return;
    }

    for (Corps c : listTemp) {
      if (c instanceof Dessinable) {
        liste.add((Dessinable) c);
      }
    }

    for (Dessinable c : liste) {
      Node n = c.reqNoeud();

      if (n != null) {
        Corps corps = (Corps) c;
        n.translateXProperty().bind(corps.reqPositionXProperty());
        n.translateYProperty().bind(corps.reqPositionYProperty());
        noeud.getChildren().add(n);
      }
    }
  }

  public void ajouterDessinable(Dessinable obj) {
    if (obj != null) {
      liste.add(obj);
      Node n = obj.reqNoeud();

      if (n != null) {
        noeud.getChildren().add(n);
        n.toBack();
      }
    }
  }

  /**
   * Met � jour la cam�ra.
   */
  public void dessiner(double dt) {
    camera.update(dt);

    Vecteur translation = camera.getTranslation();
    trans.setX(translation.getX());
    trans.setY(translation.getY());

    double facteur = camera.getFacteur();
    scale.setX(facteur);
    scale.setY(facteur);

    for (Dessinable d : liste) {
      d.miseAJourGraphique(dt);
    }

  }

  public Camera getCamera() {
    return camera;
  }
}
