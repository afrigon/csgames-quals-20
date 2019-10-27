import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Classe repr�sentant une t�te.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class Tete extends ObjetSpatial {
  private Texture texture;
  private double rayon;
  public static final Texture TEXTURE_DEFAUT = Texture.DIIGS3B;
  public static final double RAYON_DEFAUT = 100.0;

  /**
   * Constructeur de la classe Tete.
   * 
   * @param pMasse Masse de la t�te.
   * @param pRayon Rayon de la t�te.
   * @param pPosition Position initiale de la t�te.
   * @param pVitesse Vitesse initiale de la t�te.
   */
  public Tete(double pMasse, double pRayon, Vecteur pPosition, Vecteur pVitesse) {
    super(pMasse, pPosition, false, pVitesse);
    init(pRayon, TEXTURE_DEFAUT);
  }

  /**
   * Enum des textures possible pour une t�te.
   * 
   * @author EquBolduc
   */
  public enum Texture {
    XEHOS("/res/xehos.png"),
    LOTUS("/res/lotus.png"),
    CREPSER("/res/crepser.png"),
    DIIGS3B("/res/Diig S3B.png");

    private final String texture;

    /**
     * Constructeur de texture.
     * 
     * @param pTexture l'emplacement du fichier contenant la texture.
     */
    Texture(String pTexture) {
      texture = pTexture;
    }

    /**
     * Retourne la texture selon son nom.
     * 
     * @param tex le nom de la texture recherch�.
     * @return la texture.
     */
    public static Texture getTexture(String tex) {
      switch (tex.toLowerCase()) {
      case "xehos":
        return Texture.XEHOS;

      case "lotus":
        return Texture.LOTUS;

      case "crepser":
        return Texture.CREPSER;

      case "diigs3b":
        return Texture.DIIGS3B;

      default:
        return TEXTURE_DEFAUT;
      }
    }

    /**
     * @return l'emplacement du fichier contenant la texture.
     */
    public String getTexture() {
      return texture;
    }
  }

  /**
   * Initialise les attributs de la classe Tete.
   * 
   * @param pRayon Rayon de la t�te.
   * @param textureDefaut Texture de la t�te.
   */
  private void init(double pRayon, Texture textureDefaut) {
    setRayon(pRayon);
    texture = TEXTURE_DEFAUT;
  }

  /**
   * Retourne le rayon de la t�te.
   */
  public double reqRayon() {
    return rayon;
  }

  /**
   * Change le rayon de la t�te. Dois �tre plus grand que 0.
   */
  public void setRayon(double nouvRayon) {
    if (nouvRayon < 0) {
      rayon = RAYON_DEFAUT;
    } else {
      rayon = nouvRayon;
    }
  }

  /**
   * Retourne la force ext�rieure exerc�e sur cette t�te.
   */
  public Vecteur reqForceExt() {
    return new Vecteur();
  }

  /**
   * Met � jour l'apparence de la t�te.
   */
  public void miseAJourGraphique(double dt) {
  }

  /**
   * Change la texture de la t�te.
   * 
   * @param pTexture la nouvelle texture de la t�te.
   */
  public void setTexture(Texture pTexture) {
    if (pTexture == null)
      texture = TEXTURE_DEFAUT;
    else
      texture = pTexture;
  }

  /**
   * @return la texture de la t�te.
   */
  public Texture getTexture() {
    return texture;
  }

  /**
   * Retourne le noeud pour l'affichage.
   */
  public Node reqNoeud() {
    Image img = new Image(texture.getTexture());
    ImageView image = new ImageView(img);
    image.setFitWidth(2 * rayon);
    image.setFitHeight(2 * rayon);
    return image;
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

  /**
   * Cr�e un noeud repr�sentant l'objet.
   */
  public void creeNoeud() {
  }
}
