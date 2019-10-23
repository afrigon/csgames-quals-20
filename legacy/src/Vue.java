import javafx.scene.layout.BorderPane;

/**
 * Interface pour toutes les vues.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public interface Vue {
  /**
   * Retourne le chemin vers le fichier FXML de la vue. LE ROOT DU FICHIER
   * FXML DOIT �TRE UN BorderPane.
   * 
   * @return Chemin vers FXML.
   */
  public String getFXML();

  /**
   * Initialise la vue. Cette m�thode est appel�e une seule fois.
   * 
   * @param pane BorderPane de la vue.
   */
  public void initialiser(BorderPane pane);

  /**
   * Cette m�thode est appel�e � toutes les frames. Permet de mettre � jour la
   * vue.
   * 
   * @param dt Temps �coul� depuis le dernier frame.
   */
  void dessiner(double dt);
}
