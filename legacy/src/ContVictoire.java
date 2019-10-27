import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Contr�leur pour l'�cran de r�ussite du jeu !
 * 
 * @author Jonathan Samson
 * @version 1.0
 */
public class ContVictoire implements Controleur {
  @FXML
  private Button jouer;
  @FXML
  private Button quitter;

  private VueVictoire vue;

  /**
   * Constructeur du contr�leur.
   */
  public ContVictoire() {
    vue = new VueVictoire();
  }

  /**
   * Affiche la vue du menu.
   */
  public void initialiser() {
    ContPrincipal.reqInstance().afficherVue(vue, true);
  }

  /**
   * Inutile pour l'instant.
   */
  public void update(double dt) {

  }

  /**
   * Callback lorsque le joueur veut quitter.
   */
  @FXML
  public void quitter() {
    System.exit(0);
  }

  /**
   * Callback lorsque le joueur veut jouer.
   */
  @FXML
  public void menu() {
    ContPrincipal.reqInstance().selectionnerControleur(new ContMenu());
  }
}
