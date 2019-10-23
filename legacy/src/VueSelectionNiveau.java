import javafx.scene.layout.BorderPane;

public class VueSelectionNiveau extends VueJeu {

  /**
   * Retourne le chemin vers le fichier FXML de la vue. LE ROOT DU FICHIER
   * FXML DOIT �TRE UN BorderPane.
   * 
   * @return Chemin vers FXML.
   */
  @Override
  public String getFXML() {
    return "/res/SelectionNiveau.fxml";
  }

  /**
   * Initialise la vue. Cette m�thode est appel�e une seule fois.
   * 
   * @param pane BorderPane de la vue.
   */
  public void initialiser(BorderPane pane) {

  }

  /**
   * Cette m�thode est appel�e � toutes les frames. Permet de mettre � jour la
   * vue.
   * 
   * @param dt Temps �coul� depuis le dernier frame.
   */
  public void dessiner(double dt) {

  }

}
