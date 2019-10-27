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
   * Assigne un vaisseau � l'objectif.
   * 
   * @param v Vaisseau.
   */
  public void asgVaisseau(Vaisseau v);
}
