import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Classe de base pour les objets spatiaux.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public abstract class ObjetSpatial implements Corps, Dessinable {
  /**
   * Masse par d�faut des corps.
   */
  public final static double MASSE_DEFAUT = 1.0;

  protected double masse;
  protected DoubleProperty positionX;
  protected DoubleProperty positionY;
  protected boolean statique;
  protected Vecteur vitesse;

  protected double positionXDepart;
  protected double positionYDepart;
  protected Vecteur vitesseDepart;

  /**
   * Constructeur d'objet spatial, prend un vecteur pour la positon
   * 
   * @param pMasse la masse de l'objet (1 par d�faut)
   * @param pPosition le vecteur position de l'objet
   * @param pStatique la staticit� de l'objet
   * @param pVitesse le vecteur vitesse de l'objet
   */
  public ObjetSpatial(double pMasse, Vecteur pPosition, boolean pStatique, Vecteur pVitesse) {
    if (pPosition == null) {
      init(pMasse, 0, 0, pStatique, pVitesse);
    } else {
      init(pMasse, pPosition.getX(), pPosition.getY(), pStatique, pVitesse);
    }
  }

  /**
   * Constructeur d'objet spatial, prend des doubles pour la position
   * 
   * @param pMasse la masse de l'objet (1 par d�faut)
   * @param pPositionX la position en X de l'objet
   * @param pPositionY la position en Y de l'objet
   * @param pStatique la staticit� de l'objet
   * @param pVitesse le vecteur vitesse de l'objet
   */
  public ObjetSpatial(double pMasse,
                      double pPositionX,
                      double pPositionY,
                      boolean pStatique,
                      Vecteur pVitesse)
  {
    init(pMasse, pPositionX, pPositionY, pStatique, pVitesse);
  }

  /**
   * Initialise les attibuts de la classe.
   */
  private void init(double pMasse,
                    double pPositionX,
                    double pPositionY,
                    boolean pStatique,
                    Vecteur pVitesse)
  {
    asgMasse(pMasse);
    positionX = new SimpleDoubleProperty(pPositionX);
    positionXDepart = pPositionX;
    positionY = new SimpleDoubleProperty(pPositionY);
    positionYDepart = pPositionY;
    asgStatique(pStatique);
    asgVitesse(pVitesse, true);
  }

  public double reqMasse() {
    return masse;
  }

  /**
   * Modifie la masse de l'objet, masse minimum : 1.0 .
   */
  public void asgMasse(double pMasse) {
    if (pMasse < 1) {
      if (this instanceof Vaisseau)
        masse = Vaisseau.MASSE_DEFAUT;
      else if (this instanceof Planete)
        masse = Planete.MASSE_DEFAUT;
      else
        masse = MASSE_DEFAUT;
    } else
      masse = pMasse;
  }

  public double reqPositionX() {
    return positionX.get();
  }

  public double reqPositionY() {
    return positionY.get();
  }

  public void asgPositionX(double pPositionX) {
    positionX.set(pPositionX);
  }

  /**
   * Si pDepart est vrai, modifie aussi la position de d�part.
   */
  public void qsgPositionX(double pPositionX, boolean pDepart) {
    if (pDepart) {
      positionXDepart = pPositionX;
    }
    asgPositionX(pPositionX);
  }

  public void asgPositionY(double pPositionY) {
    positionY.set(pPositionY);
  }

  /**
   * Si pDepart est vrai, modifie aussi la position de d�part.
   */
  public void asgPositionY(double pPositionY, boolean pDepart) {
    if (pDepart) {
      positionYDepart = pPositionY;
    }
    asgPositionY(pPositionY);
  }

  public DoubleProperty reqPositionXProperty() {
    return positionX;
  }

  public DoubleProperty reqPositionYProperty() {
    return positionY;
  }

  public Vecteur reqPosition() {
    return new Vecteur(positionX.get(), positionY.get());
  }

  /**
   * Si le vecteur est null, met la position � 0.
   */
  public void asgPosition(Vecteur pPosition) {
    if (pPosition == null) {
      positionX.set(0);
      positionY.set(0);
    } else {
      positionX.set(pPosition.getX());
      positionY.set(pPosition.getY());
    }
  }

  /**
   * Si pDepart est vrai, modifie aussi la position de d�part.
   */
  public void asgPosition(Vecteur pPosition, boolean pDepart) {
    if (pDepart)
      if (pPosition == null) {
        positionXDepart = 0;
        positionYDepart = 0;
      } else {
        positionXDepart = pPosition.getX();
        positionYDepart = pPosition.getY();
      }
    asgPosition(pPosition);
  }

  public boolean estStatique() {
    return statique;
  }

  public void asgStatique(boolean pStatique) {
    statique = pStatique;
  }

  /**
   * Retourne un vecteur nul si l'objet est statique
   */
  public Vecteur reqVitesse() {
    if (statique)
      return new Vecteur();
    return vitesse;
  }

  /**
   * Si le vecteur est null, met la vitesse � 0.
   */
  public void asgVitesse(Vecteur pVitesse) {

    if (pVitesse == null)
      vitesse = new Vecteur();
    else
      vitesse = pVitesse;
  }

  /**
   * Si pDepart est vrai, modifie la vitesse de d�part.
   */
  public void asgVitesse(Vecteur pVitesse, boolean pDepart) {

    if (pDepart)
      if (pVitesse == null)
        vitesseDepart = new Vecteur();
      else
        vitesseDepart = pVitesse.clone();
    asgVitesse(pVitesse);
  }

  public abstract Vecteur reqForceExt();

  /**
   * Remet les corps � leur position et leur vitesse de d�part.
   */
  public void reset() {
    asgPositionX(positionXDepart);
    asgPositionY(positionYDepart);
    asgVitesse(vitesseDepart);
  }

}
