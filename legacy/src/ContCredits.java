import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Contr�leur utilis� lors du lancement des cr�dits.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class ContCredits implements Controleur {
  private VueCredits vue;

  /**
   * Contr�leur de la classe ContCredits.
   */
  public ContCredits() {
    vue = new VueCredits();
  }

  /**
   * Affiche la vue des cr�dits.
   */
  public void initialiser() {
    ContPrincipal.reqInstance().afficherVue(vue, true);
    Platform.runLater(() -> {
      chargerCredits();
    });
  }

  /**
   * Charge les �l�ments visibles dans les cr�dits.
   */
  private void chargerCredits() {
    ContPrincipal.reqInstance().viderCorps();
    Tete diigs3b = new Tete(1, 100, new Vecteur(50, 50), new Vecteur(200, 175));
    diigs3b.setTexture(Tete.Texture.DIIGS3B);
    ContPrincipal.reqInstance().ajouterCorps(diigs3b);

    Tete lotus = new Tete(1, 100, new Vecteur(450, 50), new Vecteur(-175, 200));
    lotus.setTexture(Tete.Texture.LOTUS);
    ContPrincipal.reqInstance().ajouterCorps(lotus);

    Tete crepser = new Tete(1, 100, new Vecteur(50, 450), new Vecteur(175, -175));
    crepser.setTexture(Tete.Texture.CREPSER);
    ContPrincipal.reqInstance().ajouterCorps(crepser);

    Tete xehos = new Tete(1, 100, new Vecteur(450, 450), new Vecteur(-175, -200));
    xehos.setTexture(Tete.Texture.XEHOS);
    ContPrincipal.reqInstance().ajouterCorps(xehos);

    vue.initialiserCorps();
    ContPrincipal.reqInstance().demarrerHorloge();
  }

  @FXML
  public void keyPressed(KeyEvent e) {
    if (e.getCode() == KeyCode.ESCAPE) {
      ContPrincipal.reqInstance().viderCorps();
      ContPrincipal.reqInstance().selectionnerControleur(new ContMenu());
    }
  }

  public void update(double dt) {

  }
}
