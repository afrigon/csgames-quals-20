/**
 * Interface utilis� par tous les contr�leurs.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public interface Controleur {
  /**
   * Initialise le contr�leur. Cette m�thode est appel�e qu'une seule fois.
   * C'est g�n�ralement ici que l'on charge la vue.
   */
  void initialiser();

  /**
   * Met � jour le mod�le. Cette m�thode est appel�e � chaque frame. C'est ici
   * que les modifications sur le vaisseau ou autres ont lieu.
   * 
   * @param dt Temps �coul� depuis le dernier frame (en secondes)
   */
  void update(double dt);
}
