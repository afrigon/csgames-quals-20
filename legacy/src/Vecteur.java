/**
 * Classe utilitaire repr�sentant un vecteur 2D.
 * 
 * @author �quipe Bolduc
 * @version 1.0
 */
public class Vecteur implements Cloneable {
  private double x;
  private double y;

  /**
   * Constructeur par d�faut. Cr�e un vecteur nul.
   */
  public Vecteur() {
    this.x = 0;
    this.y = 0;
  }

  /**
   * Constructeur du vecteur.
   * 
   * @param x Composante en X.
   * @param y Composante en Y.
   */
  public Vecteur(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Modifie la valeur de la composante en X.
   * 
   * @param x Composante en X.
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * Modifie la valeur de la composante en Y.
   * 
   * @param y Composante en Y.
   */
  public void setY(double y) {
    this.y = y;
  }

  /**
   * Retourne la composante en X.
   * 
   * @return Composante en X.
   */
  public double getX() {
    return this.x;
  }

  /**
   * Retourne la composante en Y.
   * 
   * @return Composante en Y.
   */
  public double getY() {
    return this.y;
  }

  /**
   * Retourne l'angle en radians du vecteur, par rapport � l'horizontale.
   * 
   * @return Angle en radian.
   */
  public double getAngle() {
    double resultat = 0.0;

    if (getX() != 0) {
      resultat = Math.atan(getY() / getX());
    }

    else if (getY() == 0) {
      resultat = 0;
    }

    else if (getY() > 0) {
      resultat = Math.PI / 2;
    }

    else {
      resultat = 3 * Math.PI / 2;
    }

    if (x < 0.0 && y < 0.0) {
      resultat += Math.PI;
    } else if (y < 0.0 && x > 0.0) {
      resultat += Math.PI * 2;
    } else if (x < 0.0) {
      resultat += Math.PI;
    }
    while (resultat > 2 * Math.PI) {
      resultat -= Math.PI;
    }

    return resultat;
  }

  /**
   * Change l'angle du vecteur
   */
  public void setAngle(double a) {
    double norme = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    x = Math.cos(a) * norme;
    y = Math.sin(a) * norme;
  }

  /**
   * Retourne la norme (la grandeur) du vecteur.
   * 
   * @return Norme du vecteur.
   */
  public double getNorme() {
    return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
  }

  public void setGrandeur(double pGrandeur) {
    double angle = getAngle();
    setAngle(0);
    setX(pGrandeur);
    setAngle(angle);
  }

  /**
   * Additionne deux vecteurs ensembles et retourne un nouveau vecteur
   * repr�sentant le r�sultat de la somme.
   * 
   * @param v Vecteur � additionner
   * @return R�sultat de la somme.
   */
  public Vecteur additionner(Vecteur v) {
    Vecteur resultat = new Vecteur();

    resultat.setX(getX() + v.getX());
    resultat.setY(getY() + v.getY());

    return resultat;
  }

  /**
   * Soustrait deux vecteurs ensembles et retourne un nouveau vecteur
   * repr�sentant le r�sultat de la somme.
   * 
   * @param v Vecteur � soustraire.
   * @return R�sultat de la somme.
   */
  public Vecteur soustraire(Vecteur v) {
    Vecteur resultat = new Vecteur();

    resultat.setX(getX() - v.getX());
    resultat.setY(getY() - v.getY());

    return resultat;
  }

  /**
   * Multiplie le vecteur par un scalaire. Retourne un nouveau vecteur
   * repr�sentant le r�sultat.
   * 
   * @param s Scalaire � multiplier.
   * @return Vecteur repr�sentant le r�sultat.
   */
  public Vecteur multiplication(double s) {
    Vecteur resultat = new Vecteur();

    resultat.setX(this.getX() * s);
    resultat.setY(this.getY() * s);

    return resultat;
  }

  /**
   * Calcul le produit scalaire des deux vecteurs.
   * 
   * @param v Vecteur � multiplier
   * @return Produit scalaire du vecteur.
   */
  public double multiplication(Vecteur v) {
    return this.getX() * v.getX() + this.getY() * v.getY();
  }

  /**
   * Retourne un vecteur de m�me direction, mais dont la norme est de 1.
   * 
   * @return Vecteur normalis�.
   */
  public Vecteur normaliser() {
    Vecteur resultat = new Vecteur();

    if (this.getNorme() != 0) {
      resultat.setX(this.getX() / this.getNorme());
      resultat.setY(this.getY() / this.getNorme());
    }

    return resultat;
  }

  public Vecteur clone() {
    return new Vecteur(x, y);

  }
}
