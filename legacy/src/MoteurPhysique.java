import java.util.List;

/**
 * Classe servant � faire en sorte que les entit�s respectent les lois de la
 * physique.
 * 
 * @author �quipe Bolduc
 * @version 1.0
 */
public class MoteurPhysique {
  private static final double GRAVITE = 6.67384E-11;
  private double tailleEcranX;
  private double tailleEcranY;

  /**
   * Sert � initialiser les variables du moteur physique.
   */
  public MoteurPhysique() {
    tailleEcranX = 600;
    tailleEcranY = 600;
  }

  /**
   * Calcule les forces exerc�es sur les objets � la suite de la collision.
   * 
   * @param corps1 Premier corps en collision.
   * @param corps2 Deuxi�me corps en collision.
   */
  private void calculerCollision(Corps corps1, Corps corps2) {
    // Repousse les corps les uns des autres.
    Vecteur deltaS = corps1.reqPosition().soustraire(corps2.reqPosition());
    double d = deltaS.getNorme();

    // �vite une division par z�ro.
    if (d == 0) {
      d = 0.00001;
    }

    Vecteur transMin = deltaS.multiplication((corps1.reqRayon() + corps2.reqRayon() - d) / d);

    double im1 = 1.0 / corps1.reqMasse();
    double im2 = 1.0 / corps2.reqMasse();

    if (!corps1.estStatique()) {
      corps1.asgPosition(corps1.reqPosition()
                               .additionner(transMin.multiplication(im1 / (im1 + im2))));
    }
    if (!corps2.estStatique()) {
      corps2.asgPosition(corps2.reqPosition()
                               .soustraire(transMin.multiplication(im2 / (im1 + im2))));
    }

    // Calcule la nouvelle vitesse.
    // Ces formules sont tir�es de ce site web :
    // http://www.vobarian.com/collisions/index.html
    double m1 = corps1.reqMasse();
    double m2 = corps2.reqMasse();
    Vecteur n = corps1.reqPosition().soustraire(corps2.reqPosition()).normaliser();
    Vecteur t = new Vecteur(-n.getY(), n.getX());

    double v1ni = n.multiplication(corps1.reqVitesse());
    double v1ti = t.multiplication(corps1.reqVitesse());
    double v2ni = n.multiplication(corps2.reqVitesse());
    double v2ti = t.multiplication(corps2.reqVitesse());

    double v1nf = (v1ni * (m1 - m2) + 2 * m2 * v2ni) / (m1 + m2);
    double v1tf = v1ti;
    double v2nf = (v2ni * (m2 - m1) + 2 * m1 * v1ni) / (m1 + m2);
    double v2tf = v2ti;

    Vecteur n1 = n.multiplication(v1nf);
    Vecteur t1 = t.multiplication(v1tf);
    Vecteur n2 = n.multiplication(v2nf);
    Vecteur t2 = t.multiplication(v2tf);

    Vecteur v1 = n1.additionner(t1);
    Vecteur v2 = n2.additionner(t2);

    if (!corps1.estStatique()) {
      corps1.asgVitesse(v1);
    }
    if (!corps2.estStatique()) {
      corps2.asgVitesse(v2);
    }
  }

  /**
   * D�tecte les collisions.
   * 
   * @param corps La liste des corps pouvant potentiellement entrer en
   *          collision.
   */
  private void gererCollisions(List<Corps> corps) {
    if (corps != null) {
      for (int i = 0; i < corps.size(); i++) {
        if (corps.get(i) != null) {
          if (corps.get(i) instanceof Tete) {
            if (corps.get(i).reqPositionX() < 0) {
              corps.get(i).asgPositionX(0);
              corps.get(i)
                   .asgVitesse(new Vecteur(-corps.get(i).reqVitesse().getX(),
                                           corps.get(i).reqVitesse().getY()));
            }

            if (corps.get(i).reqPositionX() + 2 * corps.get(i).reqRayon() > tailleEcranX) {
              corps.get(i).asgPositionX(tailleEcranX - 2 * corps.get(i).reqRayon());
              corps.get(i)
                   .asgVitesse(new Vecteur(-corps.get(i).reqVitesse().getX(),
                                           corps.get(i).reqVitesse().getY()));
            }

            if (corps.get(i).reqPositionY() < 0) {
              corps.get(i).asgPositionY(0);
              corps.get(i)
                   .asgVitesse(new Vecteur(corps.get(i).reqVitesse().getX(),
                                           -corps.get(i).reqVitesse().getY()));
            }

            if (corps.get(i).reqPositionY() + 2 * corps.get(i).reqRayon() > tailleEcranY) {
              corps.get(i).asgPositionY(tailleEcranY - 2 * corps.get(i).reqRayon());
              corps.get(i)
                   .asgVitesse(new Vecteur(corps.get(i).reqVitesse().getX(),
                                           -corps.get(i).reqVitesse().getY()));
            }
          }

          for (int j = i + 1; j < corps.size(); j++) {
            if (corps.get(j) != null) {
              Vecteur diff = corps.get(i).reqPosition().soustraire(corps.get(j).reqPosition());
              double distance = diff.getNorme();
              double sommeRayons = corps.get(i).reqRayon() + corps.get(j).reqRayon();

              if (distance < sommeRayons) {
                calculerCollision(corps.get(i), corps.get(j));
                corps.get(i).surCollision(corps.get(j));
                corps.get(j).surCollision(corps.get(i));
              }
            }
          }
        }
      }
    }
  }

  /**
   * Retourne la largeur de l'�cran.
   * 
   * @return Largeur de l'�cran.
   */
  public double getTailleEcranX() {
    return tailleEcranX;
  }

  /**
   * Modifie la largeur de l'�cran.
   * 
   * @param nouvelleTailleEcran La nouvelle largeur de l'�cran.
   */
  public void setTailleEcranX(double nouvelleTailleEcran) {
    if (nouvelleTailleEcran > 0) {
      tailleEcranX = nouvelleTailleEcran;
    }
  }

  /**
   * Retourne la hauteur de l'�cran.
   * 
   * @return Hauteur de l'�cran.
   */
  public double getTailleEcranY() {
    return tailleEcranY;
  }

  /**
   * Modifie la hauteur de l'�cran.
   * 
   * @param nouvelleTailleEcran La nouvelle hauteur de l'�cran.
   */
  public void setTailleEcranY(double nouvelleTailleEcran) {
    if (nouvelleTailleEcran > 0) {
      tailleEcranY = nouvelleTailleEcran;
    }
  }

  /**
   * Cette m�thode met � jour la position et la vitesse des diff�rents corps
   * non-statiques.
   * 
   * @param corps Liste des corps qui interagissent ensembles.
   * @param dt Intervalle de temps depuis la derni�re mise � jour.
   */
  public void update(List<Corps> corps, double dt) {
    if (corps != null && dt > 0) {
      for (Corps c1 : corps) {
        if (c1 != null && !c1.estStatique()) {
          c1.asgPosition(c1.reqPosition().additionner(c1.reqVitesse().multiplication(dt)));

          Vecteur force = new Vecteur();

          for (Corps c2 : corps) {
            if (c1 != c2 && c2 != null) {
              double distance = c2.reqPosition().soustraire(c1.reqPosition()).getNorme();
              Vecteur direction = c2.reqPosition().soustraire(c1.reqPosition()).normaliser();
              force = force.additionner(direction.multiplication((GRAVITE * c1.reqMasse()
                                                                  * c2.reqMasse())
                                                                 / Math.pow(distance, 2)));
            }
          }
          force = force.additionner(c1.reqForceExt());
          c1.asgVitesse(c1.reqVitesse()
                          .additionner(force.multiplication(1.0 / c1.reqMasse())
                                            .multiplication(dt)));
        }
      }

      gererCollisions(corps);
    }
  }
}
