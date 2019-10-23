import javafx.beans.property.DoubleProperty;

/**
 * Interface utilis� pour tous les corps physiques.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public interface Corps {
  /**
   * Retourne la masse du corps.
   * 
   * @return Masse du corps (en kg)
   */
  double reqMasse();

  /**
   * Retourne la position par rapport � X.
   * 
   * @return Position X (en m)
   */
  double reqPositionX();

  /**
   * Retourne la position par rapport � Y.
   * 
   * @return Position Y (en m)
   */
  double reqPositionY();

  /**
   * Modifie la position en X.
   * 
   * @param pPositionX Position X (en m)
   */
  void asgPositionX(double pPositionX);

  /**
   * Modifie la position en Y.
   * 
   * @param pPositionX Position Y (en m)
   */
  void asgPositionY(double pPositionX);

  /**
   * Retourne la propri�t� pour la position en X. Utile pour le binding.
   * 
   * @return Propri�t� de la position en X.
   */
  DoubleProperty reqPositionXProperty();

  /**
   * Retourne la propri�t� pour la position en Y. Utile pour le binding.
   * 
   * @return Propri�t� de la position en Y.
   */
  DoubleProperty reqPositionYProperty();

  /**
   * Retourne un vecteur repr�sentant la position du corps.
   * 
   * @return Vecteur de position.
   */
  Vecteur reqPosition();

  /**
   * Modifie la position d'un vaisseau � l'aide d'un vecteur.
   * 
   * @param pPosition Vecteur de la position.
   */
  void asgPosition(Vecteur pPosition);

  /**
   * D�termine si un corps doit �tre statique (toujours immobile)
   * 
   * @return Vrai si le corps est statique, faux sinon.
   */
  boolean estStatique();

  /**
   * Modifie le fait qu'un corps est statique (toujours immobile).
   * 
   * @param pStatique Vrai si le corps est statique, faux sinon.
   */
  void asgStatique(boolean pStatique);

  /**
   * Retourne la vitesse actuelle du corps.
   * 
   * @return Vecteur de vitesse du corps.
   */
  Vecteur reqVitesse();

  /**
   * Modifie la vitesse du vaisseau.
   * 
   * @param pVitesse Vecteur repr�sentant la vitesse du vaisseau.
   */
  void asgVitesse(Vecteur pVitesse);

  /**
   * Retourne la force ext�rieure appliqu�e sur le corps. Par exemple, la
   * force peut �tre cr��e par un r�acteur.
   * 
   * @return Vecteur repr�sentant la force (en Newton)
   */
  Vecteur reqForceExt();

  /**
   * Retourne le rayon de la taille d'un objet autour de son point central.
   * 
   * @return Rayon de la taille d'un objet autour de son point central.
   */
  double reqRayon();

  /**
   * Remet les corps � leur position et leur vitesse de d�part.
   */
  void reset();

  /**
   * Callback lorsqu'une collision a lieu.
   * 
   * @param c Autre corps en collision.
   */
  void surCollision(Corps c);

  /**
   * Met � jour les attributs
   * 
   * @param dt Temps �coul� depuis le dernier frame (en secondes).
   */
  void miseAJourPhysique(double dt);

  /**
   * Modifie la masse du corps.
   * 
   * @param pMasse Nouvelle masse (en kg).
   */
  void asgMasse(double pMasse);
}
