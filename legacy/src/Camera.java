/**
 * Classe responsable de la cam�ra.
 * 
 * @author EquBoldus
 * @version 1.0
 */
public class Camera {
  /**
   * D�fini la vitesse du zoom.
   */
  public static final double VITESSE_ZOOM = 10.0;

  private double largeur;
  private double hauteur;
  private double targetX;
  private double targetY;
  private double x;
  private double y;
  private double facteur;
  private double targetFacteur;

  /**
   * Constructeur de la cam�ra. La largeur et la hauteur de la fen�tre sont de
   * 0 par d�faut. Elles devraient �tre modifi� afin de faire fonctionner la
   * cam�ra.
   */
  public Camera() {
    largeur = 0.0;
    hauteur = 0.0;
    targetX = 0.0;
    targetY = 0.0;
    x = 0.0;
    y = 0.0;
    facteur = 1.0;
    targetFacteur = 1.0;
  }

  /**
   * Constructeur prenant
   * 
   * @param pLargeur Largeur de la fen�tre.
   * @param pHauteur Hauteur de la fen�tre.
   */
  public Camera(double pLargeur, double pHauteur) {
    this();
    setGrandeurs(pLargeur, pHauteur);
  }

  /**
   * Ajuste la grandeur de la fen�tre.
   * 
   * @param pLargeur Largeur de la fen�tre.
   * @param pHauteur Hauteur de la fen�tre.
   */
  public void setGrandeurs(double pLargeur, double pHauteur) {
    if (pLargeur > 0) {
      largeur = pLargeur;
    }

    if (pHauteur > 0) {
      hauteur = pHauteur;
    }
  }

  /**
   * D�place la cam�ra � l'endroit sp�cifier.
   * 
   * @param pX Position X.
   * @param pY Position Y.
   */
  public void deplacer(double pX, double pY) {
    targetX = pX;
    targetY = pY;
  }

  /**
   * Retourne le vecteur repr�sentant la position actuelle de la cam�ra.
   * 
   * @return Vecteur de la position de la cam�ra.
   */
  public Vecteur getDeplacement() {
    return new Vecteur(targetX, targetY);
  }

  /**
   * Zoom la cam�ra selon la facteur choisi. 1.0 signifie 1px vaut 1m. 2.0
   * signifie 1px vaut 2m. 0.5 signifie 1px vaut 0.5m.
   * 
   * @param pFacteur Facteur de zoom.
   */
  public void zoomer(double pFacteur) {
    if (pFacteur > 0) {
      targetFacteur = pFacteur;
    }
  }

  /**
   * Transforme un point de la fen�tre dans le syst�me de r�f�rence des corps.
   * 
   * @param vec Point dans l'espace de la fen�tre.
   * @return Vecteur repr�sentant le point de le syst�me de r�f�rence des
   *         corps.
   */
  public Vecteur localToGlobal(Vecteur vec) {
    Vecteur resultat = new Vecteur();

    double posX = (vec.getX() / facteur) + x - (largeur / (2 * facteur));
    double posY = (vec.getY() / facteur) + y - (hauteur / (2 * facteur));

    resultat.setX(posX);
    resultat.setY(posY);

    return resultat;
  }

  /**
   * Retourne la translation qui doit �tre appliqu�e sur les corps.
   * 
   * @return Vecteur de transformation.
   */
  public Vecteur getTranslation() {
    Vecteur resultat = new Vecteur();

    double posX = (1.0 / facteur) * largeur / 2 - x;
    double posY = (1.0 / facteur) * hauteur / 2 - y;

    resultat.setX(posX);
    resultat.setY(posY);

    return resultat;
  }

  /**
   * Retourne le facteur de zoom.
   * 
   * @return Facteur de zoom.
   */
  public double getFacteur() {
    return facteur;
  }

  /**
   * Met � jour la cam�ra. Sert � cr�er des transitions fluides.
   * 
   * @param dt Temps �coul� depuis le dernier frame.
   */
  public void update(double dt) {
    facteur = bezier(dt, facteur, targetFacteur, VITESSE_ZOOM);
    x = bezier(dt, x, targetX, VITESSE_ZOOM);
    y = bezier(dt, y, targetY, VITESSE_ZOOM);
  }

  /**
   * Cr�e une courbe pseudo-b�zier pour les transitions.
   * 
   * @param dt Temps �coul� depuis le dernier frame.
   * @param valeur Valeur actuelle.
   * @param cible Valeur cible.
   * @param vitesse Vitesse � laquelle il faut atteindre la valeur.
   * @return Nouvelle valeur actuelle.
   */
  private double bezier(double dt, double valeur, double cible, double vitesse) {
    double resultat = valeur;

    double diff = cible - valeur;
    if (diff > 0) {
      resultat += Math.min(diff, vitesse * diff * dt);
    }

    else if (diff < 0) {
      resultat -= Math.max(diff, vitesse * -diff * dt);
    }

    return resultat;
  }
}
