/**
 * Interface d�finissant un objectif d'un niveau.
 * 
 * @author EqBolduc
 * @version 1.0
 */
public interface Objectif {
  /**
   * Retourne la description de l'objectif.
   * 
   * @return Cha�ne de caract�re d�crivant l'objectif.
   */
  String reqDescription();

  /**
   * V�rifie si l'objectif a �t� atteint.
   * 
   * @return Vrai si l'objectif est atteint, faux sinon.
   */
  boolean verifierObjectif();

  /**
   * Assigne un vaisseau � l'objectif.
   * 
   * @param v Vaisseau.
   */
  public void asgVaisseau(Vaisseau v);
}
