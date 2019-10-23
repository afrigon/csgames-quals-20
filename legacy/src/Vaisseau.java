import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

/**
 * Classe repr�sentant un vaisseau spatial.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class Vaisseau extends ObjetSpatial {
  public final static double MASSE_DEFAUT = 16e3;
  protected boolean premierGetNoeud;

  protected Node noeud;
  protected Rotate noeudRotate;

  /**
   * Constructeur de vaisseau, prend un vecteur pour la position
   * 
   * @param pMasse la masse du vaisseau
   * @param pPosition la position du vaisseau
   * @param pVitesse la vitesse du vaisseau
   */
  public Vaisseau(double pMasse, Vecteur pPosition, Vecteur pVitesse) {
    super(pMasse, pPosition, false, pVitesse);
    init();
  }

  /**
   * Constructeur de vaisseau, prend des doubles pour la position
   * 
   * @param pMasse la masse du vaisseau
   * @param pPositionX la positionX de la plan�te
   * @param pPositionY la positionY de la plan�te
   * @param pVitesse la vitesse du vaisseau
   */
  public Vaisseau(double pMasse, double pPositionX, double pPositionY, Vecteur pVitesse) {
    super(pMasse, pPositionX, pPositionY, false, pVitesse);
    init();
  }

  /**
   * Initialise le vaisseau avec des valeurs par d�faut.
   */
  private void init() {
    premierGetNoeud = true;
  }

  /**
   * Ne pas mettre True sinon le vaisseau reste immobile
   */
  public void asgStatique(boolean pStatique) {
    statique = pStatique;
  }

  /**
   * Si le vecteur est null, met la vitesse � 0.
   */
  public void asgVitesse(Vecteur pVitesse) {
    if (pVitesse == null) {
      vitesse = new Vecteur();
    } else {
      vitesse = pVitesse;
    }
  }

  /**
   * Cr�e le noeud JavaFX du vaisseau.
   */
  public void creeNoeud() {
    Group group = new Group(); // TODO

    Image textureVaisseau = new Image("/res/vaisseau.png");
    ;

    ImageView image = new ImageView(textureVaisseau);
    image.setFitWidth(40);
    image.setFitHeight(40);
    image.setTranslateX(-20);
    image.setTranslateY(-20);
    group.getChildren().add(image);

    noeudRotate = new Rotate(0, 0, 0);
    group.getTransforms().add(noeudRotate);

    noeud = group;
  }

  /**
   * Retourne le noeud JavaFX repr�sentant le vaisseau. Le noeud ne change pas
   * entre chaque appel de la m�thode.
   * 
   * @return Noeud JavaFX du vaisseau.
   */
  public Node reqNoeud() {
    if (premierGetNoeud) {
      creeNoeud();
      premierGetNoeud = false;
    }
    return noeud;
  }

  /**
   * Retourne le rayon de collision du vaisseau.
   * 
   * @return Rayon de collision (en m).
   */
  public double reqRayon() {
    return 20.0;
  }

  /**
   * Retourne la force ext�rieure appliqu�e sur le corps. Par exemple, la
   * force peut �tre cr��e par un r�acteur.
   * 
   * @return Vecteur repr�sentant la force (en Newton)
   */
  public Vecteur reqForceExt() {
    return new Vecteur();
  }

  /**
   * Met � jour le noeud repr�sentant l'objet.
   * 
   * @param dt Temps �coul� depuis le dernier frame (en secondes).
   */
  public void miseAJourGraphique(double dt) {
    noeudRotate.setAngle(vitesse.getAngle() / 2 / Math.PI * 360 + 90);
  }

  /**
   * Callback lorsqu'une collision a lieu.
   * 
   * @param c Autre corps en collision.
   */
  public void surCollision(Corps c) {
  }

  /**
   * Met � jour les attributs
   * 
   * @param dt Temps �coul� depuis le dernier frame (en secondes).
   */
  public void miseAJourPhysique(double dt) {
  }
}
