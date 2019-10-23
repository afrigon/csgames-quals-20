import java.util.LinkedList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * Vue des cr�dits.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class VueCredits implements Vue {
  private BorderPane borderPane;
  private List<Dessinable> liste;

  /**
   * Constructeur de la vue des cr�dits.
   */
  public VueCredits() {
    liste = new LinkedList<Dessinable>();
  }

  /**
   * Retourne le chemin vers le fichier FXML de la vue.
   *
   * @return Chemin vers FXML.
   */
  public String getFXML() {
    return "/res/Credits.fxml";
  }

  /**
   * Ajoute tous les corps dessinables dans la vue.
   */
  public void initialiser(BorderPane pane) {
    borderPane = pane;
    Pane p = (Pane) borderPane.lookup("#pane");
    initialiserCorps();
    ContPrincipal.reqInstance().getMoteurPhysique().setTailleEcranX(p.getWidth());
    ContPrincipal.reqInstance().getMoteurPhysique().setTailleEcranY(p.getHeight());
  }

  public void initialiserCorps() {
    Pane p = (Pane) borderPane.lookup("#pane");
    p.getChildren().clear();

    liste.clear();

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
        p.getChildren().add(n);
      }
    }
  }

  /**
   * Met � jour la cam�ra.
   */
  public void dessiner(double dt) {

  }
}
