import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * Contr�leur pour le createur de niveaux
 * 
 * @author Jonathan Samson
 * @author J�r�mie Bolduc
 * @version 1.0
 */
public class ContEditeur implements Controleur {
  public static final double VITESSE_ZOOM = 0.005;

  @FXML
  private Pane pane;
  @FXML
  private Button retour;
  @FXML
  private Button sauve;
  @FXML
  private Button erase;
  @FXML
  private ComboBox<String> comboBoxCorps;
  @FXML
  private ComboBox<Planete.Texture> comboBoxTexture;
  @FXML
  private ColorPicker colorPickerCouleurAtmosphere;
  @FXML
  private TextField textFieldMasse;
  @FXML
  private TextField textFieldPositionX;
  @FXML
  private TextField textFieldPositionY;
  @FXML
  private TextField textFieldRayon;
  @FXML
  private TextField textFieldRayonObjectif;
  @FXML
  private TextField textFieldCarburantMax;
  @FXML
  private TextField textFieldCarburantDepart;
  @FXML
  private TextField textFieldPuissance;
  @FXML
  private TextField textFieldVitesseDepart;
  @FXML
  private TextField textFieldOrientation;
  @FXML
  private TextField textFieldRayonAtmosphere;
  @FXML
  private VBox vBoxMenu;
  @FXML
  private VBox vBoxMenuCorps;
  @FXML
  private VBox vBoxMenuPlanete;
  @FXML
  private VBox vBoxMenuVaisseau;
  @FXML
  private VBox vBoxMenuVaisseauJoueur;
  @FXML
  private VBox vBoxMenuVaisseauNonJoueur;
  @FXML
  private VBox vBoxMenuObjectif;

  private VueEditeur vue;
  private Corps corpsSelect;
  private Objectif objectifSelect;
  private VaisseauJoueur vaisseauJoueur;
  private Objectif objectif;
  private Niveau niveau;

  /**
   * Constructeur du contr�leur.
   */
  public ContEditeur() {
    this(null);
  }

  /**
   * Constructeur du contr�leur avec un niveau en param�tre.
   */
  public ContEditeur(Niveau niv) {
    vue = new VueEditeur();
    setNiveau(niv);
  }

  /**
   * Initialise et affiche la vue de l'�diteur.
   */
  public void initialiser() {
    // Affiche la vue et supprime tous les corps actuels.
    ContPrincipal.reqInstance().afficherVue(vue, true);
    ContPrincipal.reqInstance().arreterHorloge();

    // Initialisation des combo box.
    comboBoxCorps.getItems().addAll("Vaisseau", "Plan�te", "Portail", "Vaisseau Joueur");
    initialiserComboBoxTexture();
    vBoxMenu.setVisible(false);

    // Initialisation du niveau.
    vaisseauJoueur = null;
    objectif = null;
    if (niveau == null) {
      niveau = new Niveau();
    }
    Platform.runLater(() -> {
      chargerNiveau();
      vue.getCamera().deplacer(vaisseauJoueur.reqPositionX(), vaisseauJoueur.reqPositionY());
      vue.getCamera().zoomer(1.0);
    });
  }

  // ===============================================
  // �couteurs des diff�rents text field et boutons.
  // ===============================================
  @FXML
  public void onRayon(ActionEvent e) {
    if (corpsSelect instanceof Planete) {
      try {
        ((Planete) corpsSelect).asgRayon((Double.valueOf(textFieldRayon.getText())));
        ((Planete) corpsSelect).reqNoeud();
      } catch (NumberFormatException ex) {
        textFieldRayon.setText("" + corpsSelect.reqRayon());
      }
    }
    if (corpsSelect instanceof Dessinable)
      ((Dessinable) corpsSelect).creeNoeud();
  }

  @FXML
  public void onRayonAtmosphere(ActionEvent e) {
    if (corpsSelect instanceof Planete) {
      Planete p = (Planete) corpsSelect;
      try {
        p.asgRayonAtmosphere(Double.valueOf(textFieldRayonAtmosphere.getText()));
        p.reqNoeud();
      } catch (NumberFormatException ex) {
        textFieldRayonAtmosphere.setText("" + p.reqRayonAtmosphere());
      }
    }
    if (corpsSelect instanceof Dessinable)
      ((Dessinable) corpsSelect).creeNoeud();
  }

  @FXML
  public void onCouleurAtmosphere(ActionEvent e) {
    if (corpsSelect instanceof Planete) {
      Planete p = (Planete) corpsSelect;
      p.asgCouleurAtmosphere(colorPickerCouleurAtmosphere.getValue());
      p.reqNoeud();
    }
    if (corpsSelect instanceof Dessinable)
      ((Dessinable) corpsSelect).creeNoeud();
  }

  @FXML
  public void onRayonObjectif(ActionEvent e) {
    if (objectifSelect instanceof ObjectifRayon) {
      ObjectifRayon or = (ObjectifRayon) objectifSelect;
      try {
        or.asgRayon((Double.valueOf(textFieldRayonObjectif.getText())));
        or.reqNoeud();
      } catch (NumberFormatException ex) {
        textFieldRayonObjectif.setText("" + or.reqRayon());
      }
    }
    if (objectifSelect instanceof Dessinable)
      ((Dessinable) objectifSelect).creeNoeud();
  }

  @FXML
  public void onMasse(ActionEvent e) {
    try {
      corpsSelect.asgMasse((Double.valueOf(textFieldMasse.getText())));
    } catch (NumberFormatException ex) {
      textFieldMasse.setText("" + corpsSelect.reqMasse());
    }
  }

  @FXML
  public void onPositionX(ActionEvent e) {
    if (corpsSelect instanceof ObjetSpatial) {
      ObjetSpatial obj = (ObjetSpatial) corpsSelect;
      try {
        obj.qsgPositionX((Double.valueOf(textFieldPositionX.getText())), true);

        if (obj instanceof VaisseauJoueur) {
          niveau.asgPointDepart(new Vecteur(Double.valueOf(textFieldPositionX.getText()),
                                            niveau.reqPointDepart().getY()));
        }
      } catch (NumberFormatException ex) {
        textFieldPositionX.setText("" + obj.reqPositionX());
      }
    } else if (objectifSelect instanceof ObjectifRayon) {
      ObjectifRayon or = (ObjectifRayon) objectifSelect;
      try {
        or.reqPosRayon().setX((Double.valueOf(textFieldPositionX.getText())));
      } catch (NumberFormatException ex) {
        textFieldPositionX.setText("" + or.reqPosRayon().getX());
      }
    }
    if (corpsSelect instanceof Dessinable)
      ((Dessinable) corpsSelect).creeNoeud();
    if (objectifSelect instanceof Dessinable)
      ((Dessinable) objectifSelect).creeNoeud();
  }

  @FXML
  public void onPositionY(ActionEvent e) {
    if (corpsSelect instanceof ObjetSpatial) {
      ObjetSpatial obj = (ObjetSpatial) corpsSelect;

      try {
        obj.asgPositionY((Double.valueOf(textFieldPositionY.getText())), true);

        if (obj instanceof VaisseauJoueur) {
          niveau.asgPointDepart(new Vecteur(niveau.reqPointDepart().getX(),
                                            Double.valueOf(textFieldPositionY.getText())));
        }
      } catch (NumberFormatException ex) {
        textFieldPositionY.setText("" + obj.reqPositionY());
      }
    } else if (objectifSelect instanceof ObjectifRayon) {
      ObjectifRayon or = (ObjectifRayon) objectifSelect;
      try {
        or.reqPosRayon().setY((Double.valueOf(textFieldPositionY.getText())));
      } catch (NumberFormatException ex) {
        textFieldPositionY.setText("" + or.reqPosRayon().getY());
      }
    }
    if (corpsSelect instanceof Dessinable)
      ((Dessinable) corpsSelect).creeNoeud();
    if (objectifSelect instanceof Dessinable)
      ((Dessinable) objectifSelect).creeNoeud();
  }

  @FXML
  public void onSupprimer() {
    niveau.supprimerCorps(corpsSelect);
    corpsSelect = null;
    vBoxMenu.setVisible(false);
    chargerNiveau();
  }

  @FXML
  public void onTexture(ActionEvent e) {
    if (corpsSelect != null) {
      ((Planete) corpsSelect).asgTexture(comboBoxTexture.getValue());
    }
    if (corpsSelect instanceof Dessinable)
      ((Dessinable) corpsSelect).creeNoeud();
  }

  @FXML
  public void onCarburantMax(ActionEvent e) {
    try {
      VaisseauJoueur corpsSelect = (VaisseauJoueur) this.corpsSelect;
      corpsSelect.asgCarburantMax((Double.valueOf(textFieldCarburantMax.getText())));
      textFieldCarburantDepart.setText("" + corpsSelect.reqCarburantDepart());
    } catch (NumberFormatException ex) {
      textFieldCarburantMax.setText("" + ((VaisseauJoueur) corpsSelect).reqCarburantMax());
    }
  }

  @FXML
  public void onCarburantDepart(ActionEvent e) {
    try {
      VaisseauJoueur corpsSelect = (VaisseauJoueur) this.corpsSelect;
      corpsSelect.reqCarburantDepart((Double.valueOf(textFieldCarburantDepart.getText())));
      textFieldCarburantMax.setText("" + corpsSelect.reqCarburantMax());
    } catch (NumberFormatException ex) {
      textFieldCarburantDepart.setText("" + ((VaisseauJoueur) corpsSelect).reqCarburantDepart());
    }
  }

  @FXML
  public void onPuissance(ActionEvent e) {
    try {
      VaisseauJoueur corpsSelect = (VaisseauJoueur) this.corpsSelect;
      corpsSelect.asgPuissance((Double.valueOf(textFieldPuissance.getText())));
    } catch (NumberFormatException ex) {
      textFieldPuissance.setText("" + ((VaisseauJoueur) corpsSelect).reqPuissance());
    }
  }

  @FXML
  public void onVitesseDepart(ActionEvent e) {
    if (corpsSelect instanceof VaisseauJoueur)
      try {
        niveau.asgVitesseDepart((Double.valueOf(textFieldVitesseDepart.getText())));
      } catch (NumberFormatException ex) {
        textFieldVitesseDepart.setText("" + niveau.reqVitesseDepart());
      }
    else
      try {
        Vaisseau corpsSelect = (Vaisseau) this.corpsSelect;
        corpsSelect.reqVitesse().setGrandeur((Double.valueOf(textFieldVitesseDepart.getText())));
        corpsSelect.asgVitesse(corpsSelect.reqVitesse(), true);
      } catch (NumberFormatException ex) {
        textFieldVitesseDepart.setText("" + corpsSelect.reqVitesse().getNorme());
      }
  }

  @FXML
  public void onOrientation(ActionEvent e) {
    try {
      Vaisseau corpsSelect = (Vaisseau) this.corpsSelect;
      corpsSelect.reqVitesse()
                 .setAngle((Double.valueOf(textFieldOrientation.getText())) / 360 * 2 * Math.PI);
      corpsSelect.asgVitesse(corpsSelect.reqVitesse(), true);
    } catch (NumberFormatException ex) {
      textFieldOrientation.setText("" + corpsSelect.reqVitesse().getAngle() / 2 / Math.PI * 360);
    }
  }

  @FXML
  public void retour() {
    ContPrincipal.reqInstance().selectionnerControleur(new ContMenu());
  }

  @FXML
  public void onEssayer() {
    ContPrincipal.reqInstance().selectionnerControleur(new ContJeu(niveau, true));
  }

  @FXML
  public void sauve() {
    try {
      File file = (new FileChooser()).showSaveDialog(null);

      if (!file.exists()) {
        file.createNewFile();
      }

      while (!file.canWrite()) {
        JOptionPane.showMessageDialog(null,
                                      "L'emplacement choisi ne peut pas �tre modifi�!",
                                      "Erreur",
                                      JOptionPane.ERROR_MESSAGE);
        file = (new FileChooser()).showSaveDialog(null);
      }

      niveau.sauvegarderNiveau(file);
    } catch (Exception e) {
    }
  }

  /**
   * M�thode pour le bouton charger.
   */
  @FXML
  public void charge() {
    try {
      File file = (new FileChooser()).showOpenDialog(null);

      while (!file.canRead()) {
        JOptionPane.showMessageDialog(null,
                                      "L'emplacement choisi ne peut pas �tre lu!",
                                      "Erreur",
                                      JOptionPane.ERROR_MESSAGE);
        file = (new FileChooser()).showOpenDialog(null);
      }

      niveau = Niveau.chargerNiveau(new FileInputStream(file));

      chargerNiveau();
      vBoxMenu.setVisible(false);
      vue.getCamera().deplacer(vaisseauJoueur.reqPositionX(), vaisseauJoueur.reqPositionY());
      vue.getCamera().zoomer(1.0);
    } catch (Exception e) {
    }
  }

  /**
   * M�thode pour le bouton effacer
   */
  @FXML
  public void erase() {
    List<Corps> c = ContPrincipal.reqInstance().getCorps();
    c.clear();
    vue.initialiserCorps();
    niveau.reqCorps().clear();
    niveau.ajouterCorps(vaisseauJoueur);
    chargerNiveau();
  }

  /**
   * Met � jour le contr�leur. Inutile pour l'instant.
   */
  public void update(double dt) {

  }

  /**
   * Initialise le combo box de texture. Obligatoire, sinon les textures
   * disparaissent si on ne fait que les d�finir dans le FXML.
   */
  private void initialiserComboBoxTexture() {
    comboBoxTexture.getItems()
                   .addAll(Planete.Texture.BLEUE,
                           Planete.Texture.JAUNE,
                           Planete.Texture.MAGENTA,
                           Planete.Texture.ORANGE,
                           Planete.Texture.ROUGE,
                           Planete.Texture.VERTE);

    comboBoxTexture.setCellFactory(new Callback<ListView<Planete.Texture>, ListCell<Planete.Texture>>() {
      public ListCell<Planete.Texture> call(ListView<Planete.Texture> p) {
        return new ListCell<Planete.Texture>() {
          private final ImageView imageView;
          {
            setContentDisplay(ContentDisplay.LEFT);
            imageView = new ImageView();
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(32);
          }

          @Override
          protected void updateItem(Planete.Texture item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
              setGraphic(null);
            } else {
              imageView.setImage(new Image(item.reqTexture()));
              setGraphic(imageView);
              setText(item.toString());
            }
          }
        };
      }
    });
  }

  /**
   * G�re les clicks de la souris.
   */
  @FXML
  public void mouseClicked(MouseEvent event) {
    Point2D point = pane.sceneToLocal(event.getSceneX(), event.getSceneY());
    Camera cam = vue.getCamera();
    Vecteur pos = cam.localToGlobal(new Vecteur(point.getX(), point.getY()));

    switch (event.getButton()) {
    case PRIMARY:
      mouseClickedPrimary(event, pos);
      break;
    case SECONDARY:
      mouseClickedSecondary(event, pos);
      break;
    default:
      break;
    }
  }

  /**
   * G�re le clique gauche.
   */
  private void mouseClickedPrimary(MouseEvent event, Vecteur pos) {
    boolean toucheCorps = false;
    corpsSelect = null;

    // V�rifie si un corps est touch�.
    for (Corps c : ContPrincipal.reqInstance().getCorps()) {
      if (Math.abs(c.reqPosition().getX() - pos.getX()) < c.reqRayon()
          && Math.abs(c.reqPosition().getY() - pos.getY()) < c.reqRayon())
      {
        toucheCorps = true;
        corpsSelect = c;
        break;
      }
    }

    if (!toucheCorps) {
      if (objectif instanceof ObjectifRayon) {
        ObjectifRayon or = (ObjectifRayon) objectif;
        if (Math.abs(or.reqPosRayon().getX() - pos.getX()) < or.reqRayon()
            && Math.abs(or.reqPosRayon().getY() - pos.getY()) < (or.reqRayon()))
        {
          toucheCorps = true;
          objectifSelect = objectif;
        }

      }
    }

    // Ajoute le corps voulu.
    if (!toucheCorps && comboBoxCorps.getValue() != null) {
      switch (comboBoxCorps.getValue()) {
      case "Plan�te":
        creePlanete(pos);
        niveau.ajouterCorps(corpsSelect);
        objectifSelect = null;
        break;
      case "Vaisseau":
        creeVaisseau(pos);
        niveau.ajouterCorps(corpsSelect);
        objectifSelect = null;
        break;
      case "Portail":
        corpsSelect = null;
        objectifSelect = niveau.reqObjectif();
        ((ObjectifRayon) objectifSelect).asgPosRayon(pos);
        ((Dessinable) objectifSelect).creeNoeud();
        break;
      case "Vaisseau Joueur":
        corpsSelect = vaisseauJoueur;
        objectifSelect = null;
        vaisseauJoueur.asgPosition(pos);
        niveau.asgPointDepart(pos);
      }
      chargerNiveau();
    }
    afficherMenuParametre();
  }

  /**
   * G�re la cr�ation de plan�tes dans l'�diteur de niveaux.
   */
  private void creePlanete(Vecteur pos) {
    Planete p = new Planete(Planete.PLANETE_MASSE_DEFAUT,
                            pos,
                            Planete.RAYON_DEFAUT,
                            Planete.RAYON_ATMOSPHERE_DEFAUT,
                            Planete.COULEUR_ATMOSHPERE_DEFAUT);
    p.asgTexture(Planete.TEXTURE_DEFAUT);
    corpsSelect = p;
  }

  /**
   * G�re la cr�ation de vaisseau dans l'�diteur de niveaux.
   */
  private void creeVaisseau(Vecteur pos) {
    Vaisseau v = new Vaisseau(Vaisseau.MASSE_DEFAUT, pos, new Vecteur());
    corpsSelect = v;
  }

  /**
   * Affiche le menu pour modifier les param�tres de l'objet s�lectionn�.
   */
  private void afficherMenuParametre() {
    if (corpsSelect == null && objectifSelect == null) {
      return;
    }
    vBoxMenu.setVisible(true);
    if (corpsSelect instanceof Planete) {
      Planete corpsSelect = (Planete) this.corpsSelect;
      vBoxMenuCorps.setVisible(true);
      vBoxMenuObjectif.setVisible(false);
      vBoxMenuPlanete.setVisible(true);
      vBoxMenuVaisseau.setVisible(false);
      textFieldRayon.setText("" + corpsSelect.reqRayon());
      textFieldMasse.setText("" + corpsSelect.reqMasse());
      textFieldPositionX.setText("" + corpsSelect.reqPositionX());
      textFieldPositionY.setText("" + corpsSelect.reqPositionY());
      textFieldRayonAtmosphere.setText("" + corpsSelect.reqRayonAtmosphere());
      colorPickerCouleurAtmosphere.setValue(corpsSelect.reqCouleurAtmosphere());
      comboBoxTexture.getSelectionModel().select(((Planete) corpsSelect).reqTexture());
      comboBoxCorps.setValue("Plan�te");
    }

    else if (corpsSelect instanceof Vaisseau) {
      Vaisseau corpsSelect = (Vaisseau) this.corpsSelect;
      vBoxMenuCorps.setVisible(true);
      vBoxMenuObjectif.setVisible(false);
      vBoxMenuPlanete.setVisible(false);
      vBoxMenuVaisseau.setVisible(true);
      textFieldMasse.setText("" + corpsSelect.reqMasse());
      textFieldPositionX.setText("" + corpsSelect.reqPositionX());
      textFieldPositionY.setText("" + corpsSelect.reqPositionY());
      if (corpsSelect instanceof VaisseauJoueur) {
        VaisseauJoueur vj = (VaisseauJoueur) corpsSelect;
        vBoxMenuVaisseauJoueur.setVisible(true);
        vBoxMenuVaisseauNonJoueur.setVisible(false);
        textFieldCarburantMax.setText("" + vj.reqCarburantMax());
        textFieldCarburantDepart.setText("" + vj.reqCarburantDepart());
        textFieldPuissance.setText("" + vj.reqPuissance());
        textFieldVitesseDepart.setText("" + niveau.reqVitesseDepart());
        comboBoxCorps.setValue("Vaisseau Joueur");
      } else {
        vBoxMenuVaisseauJoueur.setVisible(false);
        vBoxMenuVaisseauNonJoueur.setVisible(true);
        textFieldVitesseDepart.setText("" + corpsSelect.reqVitesse().getNorme());
        textFieldOrientation.setText("" + corpsSelect.reqVitesse().getAngle() / 2 / Math.PI * 360);
        comboBoxCorps.setValue("Vaisseau");
      }
    }

    else if (objectifSelect instanceof ObjectifRayon) {
      ObjectifRayon or = (ObjectifRayon) objectif;
      vBoxMenuCorps.setVisible(false);
      vBoxMenuObjectif.setVisible(true);
      textFieldPositionX.setText("" + or.reqPosRayon().getX());
      textFieldPositionY.setText("" + or.reqPosRayon().getY());
      textFieldRayonObjectif.setText("" + or.reqRayon());
      comboBoxCorps.setValue("Portail");
    }
  }

  /**
   * G�re le click droit de la souris.
   */
  private void mouseClickedSecondary(MouseEvent event, Vecteur pos) {
    vue.getCamera().deplacer(pos.getX(), pos.getY());
  }

  /**
   * Retourne le niveau de l'�diteur.
   *
   * @return Niveau de l'�diteur.
   */
  public Niveau getNiveau() {
    return niveau;
  }

  /**
   * Modifie le niveau de l'�diteur
   *
   * @param nouvNiveau Nouveau niveau de l'�diteur.
   */
  public void setNiveau(Niveau nouvNiveau) {
    if (nouvNiveau != null) {
      niveau = nouvNiveau;
    }
  }

  /**
   * Ajoute tous les corps d'un niveau donn� dans la vue.
   */
  private void chargerNiveau() {
    // Vide tous les corps.
    ContPrincipal.reqInstance().viderCorps();

    // Ajoute les corps un � un.
    for (Corps c : niveau.reqCorps()) {
      ContPrincipal.reqInstance().ajouterCorps(c);
      if (c instanceof VaisseauJoueur) {
        vaisseauJoueur = (VaisseauJoueur) c;
      }
    }

    // Si aucun vaisseau joueur n'est d�fini, on en ajoute un.
    if (vaisseauJoueur == null) {
      vaisseauJoueur = new VaisseauJoueur(VaisseauJoueur.PUISSANCE_DEFAUT,
                                          VaisseauJoueur.MASSE_DEFAUT,
                                          VaisseauJoueur.CARBURANT_DEFAUT,
                                          VaisseauJoueur.CARBURANT_DEFAUT);
      niveau.ajouterCorps(vaisseauJoueur);
      ContPrincipal.reqInstance().ajouterCorps(vaisseauJoueur);
    }
    vaisseauJoueur.asgPosition(niveau.reqPointDepart());

    // S'il n'y a pas d'objectif, on en ajoute un.
    if (niveau.reqObjectif() == null) {
      niveau.asgObjectif(new ObjectifRayon(new Vecteur(0, 10), ObjectifRayon.RAYON_DEFAUT));
    }

    // On ajoute les corps dans la sc�ne.
    vue.initialiserCorps();

    // On ajoute l'objectif.
    objectif = niveau.reqObjectif();
    if (niveau.reqObjectif() != null && niveau.reqObjectif() instanceof Dessinable) {
      vue.ajouterDessinable((Dessinable) niveau.reqObjectif());
    }
  }

  /**
   * M�thode pour le zoom.
   */
  @FXML
  public void zoom(ScrollEvent e) {
    Camera cam = vue.getCamera();

    double delta = e.getDeltaY();

    if (delta > 0) {
      cam.zoomer(cam.getFacteur() + delta * VITESSE_ZOOM);
    }

    else {
      cam.zoomer(cam.getFacteur() + delta * VITESSE_ZOOM);
    }
  }
}
