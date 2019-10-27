import java.io.File;
import java.io.FileInputStream;
import java.net.URISyntaxException;
import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

/**
 * Contr�leur pour le choix des niveaux.
 * 
 * @author Jonathan Samson
 * @version 1.0
 */
public class ContSelectionNiveau implements Controleur {
  @FXML
  Label label;
  @FXML
  Button play;
  @FXML
  Button b1;
  @FXML
  Button b2;
  @FXML
  Button b3;
  @FXML
  Button b4;
  @FXML
  Button b5;
  @FXML
  Button b6;
  @FXML
  Button b7;
  @FXML
  Button b8;
  @FXML
  Button b9;
  @FXML
  Button b10;

  private int niveauSelectionne;

  private VueSelectionNiveau vue;

  /**
   * Constructeur du contr�leur.
   */
  public ContSelectionNiveau() {
    vue = new VueSelectionNiveau();
  }

  /**
   * Affiche la vue du jeu
   */
  @FXML
  public void play() {
    ContPrincipal.reqInstance().selectionnerControleur(new ContJeu(niveauSelectionne));
  }

  /**
   * Charge un niveau ne faisant pas partie de la campagne.
   */
  @FXML
  public void autreNiveau() {
    Platform.runLater(() -> {
      try {
        File file = (new FileChooser()).showOpenDialog(null);

        while (!file.canRead()) {
          JOptionPane.showMessageDialog(null,
                                        "L'emplacement choisi ne peut pas �tre lu!",
                                        "Erreur",
                                        JOptionPane.ERROR_MESSAGE);
          file = (new FileChooser()).showOpenDialog(null);
        }

        Niveau niveau = Niveau.chargerNiveau(new FileInputStream(file));
        ContPrincipal.reqInstance().selectionnerControleur(new ContJeu(niveau, false));
      } catch (Exception e) {
      }
    });

  }

  /**
   * Affiche la vue du constructeur.
   */
  public void initialiser() {
    ContPrincipal.reqInstance().afficherVue(vue, true);
    niveauSelectionne = 1;
  }

  /**
   * Retourne au controleur principal, et � sa vue.
   *
   */
  @FXML
  public void retour() {
    ContPrincipal.reqInstance().selectionnerControleur(new ContMenu());

  }

  /**
   * G�re les diff�rentes descriptions de niveaux.
   */
  @FXML
  public void levels(ActionEvent event) throws URISyntaxException {
    Niveau n = null;

    if (event.getSource() == b1) {
      n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_1.txt"));
      niveauSelectionne = 1;

    } else if (event.getSource() == b2) {
      n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_2.txt"));
      niveauSelectionne = 2;
    }

    else if (event.getSource() == b3) {
      n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_3.txt"));
      niveauSelectionne = 3;
    }

    else if (event.getSource() == b4) {
      n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_4.txt"));
      niveauSelectionne = 4;
    }

    else if (event.getSource() == b5) {
      n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_5.txt"));
      niveauSelectionne = 5;
    }

    else if (event.getSource() == b6) {
      n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_6.txt"));
      niveauSelectionne = 6;
    }

    else if (event.getSource() == b7) {
      n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_7.txt"));
      niveauSelectionne = 7;
    }

    else if (event.getSource() == b8) {
      n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_8.txt"));
      niveauSelectionne = 8;
    }

    else if (event.getSource() == b9) {
      n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_9.txt"));
      niveauSelectionne = 9;
    }

    else if (event.getSource() == b10) {
      n = Niveau.chargerNiveau(this.getClass().getResourceAsStream("/levels/level_10.txt"));
      niveauSelectionne = 10;
    }

    if (n != null) {
      label.setText("Titre :  " + n.reqTitreNiveau() + "\n" + "Description :  "
                    + n.reqDescriptionNiveau());
    }
  }

  public void update(double dt) {

  }

}
