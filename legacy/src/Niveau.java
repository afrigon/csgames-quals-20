import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javafx.scene.paint.Color;

/**
 * Classe servant � contenir les �l�ments constituant un niveau.
 * 
 * @author �quipe Bolduc
 * @version 1.0
 */
public class Niveau {
  private List<Corps> corps;

  private String descriptionNiveau;

  private Objectif objectif;

  private Vecteur pointDepart;

  private String titreNiveau;

  private double vitesseDepart;

  public Niveau() {
    this(new ArrayList<Corps>(), "", null, new Vecteur(), "", 0);
  }

  /**
   * Constructeur servant � initialiser le niveau.
   * 
   * @param nouveauxCorps Les corps � mettre dans le niveau.
   * @param nouvelleDescriptionNiveau La description du niveau.
   * @param nouvelObjectif L'objectif � mettre dans le niveau.
   * @param nouveauPointDepart Le point de d�part du vaisseau.
   * @param nouveauTitreNiveau Le titre du niveau.
   * @param nouvelleVitesseDepart La vitesse du vaisseau au point de d�part.
   */
  public Niveau(List<Corps> nouveauxCorps,
                String nouvelleDescriptionNiveau,
                Objectif nouvelObjectif,
                Vecteur nouveauPointDepart,
                String nouveauTitreNiveau,
                double nouvelleVitesseDepart)
  {
    corps = new ArrayList<>();

    if (nouveauxCorps != null) {
      for (Corps c : nouveauxCorps) {
        ajouterCorps(c);
      }
    }

    asgDescriptionNiveau(nouvelleDescriptionNiveau);

    asgObjectif(nouvelObjectif);

    asgPointDepart(nouveauPointDepart);

    asgTitreNiveau(nouveauTitreNiveau);

    asgVitesseDepart(nouvelleVitesseDepart);
  }

  /**
   * Sert � ajouter un corps dans le niveau.
   * 
   * @param nouveauCorps Un nouveau corps.
   */
  public void ajouterCorps(Corps nouveauCorps) {
    if (nouveauCorps != null) {
      corps.add(nouveauCorps);
    }
  }

  /**
   * Enl�ve un corps du niveau.
   * 
   * @param c
   */
  public void supprimerCorps(Corps c) {
    if (corps.contains(c)) {
      corps.remove(c);
    }
  }

  /**
   * Charge le niveau pass� en param�tre.
   * 
   * @param fichier Le niveau � charger.
   * @return Le niveau charg�.
   */
  public static Niveau chargerNiveau(InputStream fichier) {
    ArrayList<Corps> corps = new ArrayList<>();
    String descriptionNiveau = null;
    Objectif objectif = null;
    Vecteur pointDepart = null;
    String titreNiveau = null;
    double vitesseDepart = 0;
    Niveau niveau = null;

    if (fichier != null) {
      try {
        InputStreamReader isr = new InputStreamReader(fichier);
        BufferedReader bw = new BufferedReader(isr);
        String ligne;
        StringTokenizer st;

        while ((ligne = bw.readLine()) != null) {
          st = new StringTokenizer(ligne, ";");

          if (st.hasMoreTokens()) {
            switch (st.nextToken().trim().toLowerCase()) {
            case "planete": {
              double masse = Double.parseDouble(st.nextToken().trim());
              double positionX = Double.parseDouble(st.nextToken().trim());
              double positionY = Double.parseDouble(st.nextToken().trim());
              double rayon = Double.parseDouble(st.nextToken().trim());
              String texture = st.nextToken().trim();
              double rayonAtmosphere = Double.parseDouble(st.nextToken().trim());
              String couleurAtmosphere = st.nextToken().trim();

              Planete p = new Planete(masse,
                                      positionX,
                                      positionY,
                                      rayon,
                                      rayonAtmosphere,
                                      Color.web(couleurAtmosphere));
              p.asgTexture(Planete.Texture.reqTexture(texture));
              corps.add(p);
              break;
            }
            case "vaisseau": {
              double masse = Double.parseDouble(st.nextToken().trim());
              double positionX = Double.parseDouble(st.nextToken().trim());
              double positionY = Double.parseDouble(st.nextToken().trim());
              double vitesseX = Double.parseDouble(st.nextToken().trim());
              double vitesseY = Double.parseDouble(st.nextToken().trim());
              Vecteur position = new Vecteur(positionX, positionY);
              Vecteur vitesse = new Vecteur(vitesseX, vitesseY);

              corps.add(new Vaisseau(masse, position, vitesse));
              break;
            }
            case "vaisseaujoueur": {
              double puissance = Double.parseDouble(st.nextToken().trim());
              double masse = Double.parseDouble(st.nextToken().trim());
              double capaciteCarburant = Double.parseDouble(st.nextToken().trim());
              double carburantDepart = Double.parseDouble(st.nextToken().trim());

              corps.add(new VaisseauJoueur(puissance, masse, capaciteCarburant, carburantDepart));
              break;
            }
            case "descriptionniveau": {
              descriptionNiveau = st.nextToken().trim();
              break;
            }
            case "objectifrayon": {
              double posRayonX = Double.parseDouble(st.nextToken().trim());
              double posRayonY = Double.parseDouble(st.nextToken().trim());
              double rayon = Double.parseDouble(st.nextToken().trim());
              Vecteur posRayon = new Vecteur(posRayonX, posRayonY);

              objectif = new ObjectifRayon(posRayon, rayon);
              break;
            }
            case "pointdepart": {
              double pointDepartX = Double.parseDouble(st.nextToken().trim());
              double pointDepartY = Double.parseDouble(st.nextToken().trim());

              pointDepart = new Vecteur(pointDepartX, pointDepartY);
              break;
            }
            case "titreniveau": {
              titreNiveau = st.nextToken().trim();
              break;
            }
            case "vitessedepart": {
              vitesseDepart = Double.parseDouble(st.nextToken().trim());
              break;
            }
            }
          }
        }
        bw.close();
        isr.close();

        niveau = new Niveau(corps,
                            descriptionNiveau,
                            objectif,
                            pointDepart,
                            titreNiveau,
                            vitesseDepart);
      } catch (Exception e) {
      }
    }
    return niveau;
  }

  /**
   * Retourne la liste de corps.
   * 
   * @return La liste de corps.
   */
  public List<Corps> reqCorps() {
    return corps;
  }

  /**
   * Retourne la description du niveau.
   * 
   * @return La description du niveau.
   */
  public String reqDescriptionNiveau() {
    return descriptionNiveau;
  }

  /**
   * Retourne l'objectif.
   * 
   * @return L'objectif.
   */
  public Objectif reqObjectif() {
    return objectif;
  }

  /**
   * Retourne le point de d�part du vaisseau.
   * 
   * @return Le point de d�part du vaisseau.
   */
  public Vecteur reqPointDepart() {
    return pointDepart;
  }

  /**
   * Retourne le titre du niveau.
   * 
   * @return Le titre du niveau.
   */
  public String reqTitreNiveau() {
    return titreNiveau;
  }

  /**
   * Retourne la vitesse de d�part du vaisseau.
   * 
   * @return La vitesse de d�part du vaisseau.
   */
  public double reqVitesseDepart() {
    return vitesseDepart;
  }

  /**
   * Sauvegarde le niveau dans le fichier pass� en param�tre.
   * 
   * @param fichier Fichier dans lequel le niveau est � sauvegarder.
   */
  public void sauvegarderNiveau(File fichier) {
    try {
      FileWriter fw = new FileWriter(fichier);
      BufferedWriter bw = new BufferedWriter(fw);

      for (Corps c : corps) {
        switch (c.getClass().getName().toLowerCase()) {
        case "objets.planete": {
          Planete p = (Planete) c;
          bw.write("Planete ; " + p.reqMasse() + " ; " + p.reqPositionX() + " ; " + p.reqPositionY()
                   + " ; " + p.reqRayon() + " ; " + p.reqTexture().toString() + " ; "
                   + p.reqRayonAtmosphere() + " ; " + p.reqCouleurAtmosphere().toString());
          break;
        }
        case "objets.vaisseau": {
          Vaisseau v = (Vaisseau) c;
          bw.write("Vaisseau ; " + v.reqMasse() + " ; " + v.reqPositionX() + " ; "
                   + v.reqPositionY() + " ; " + v.reqVitesse().getX() + " ; "
                   + v.reqVitesse().getY());
          break;
        }
        case "objets.vaisseaujoueur": {
          VaisseauJoueur vj = (VaisseauJoueur) c;
          bw.write("VaisseauJoueur ; " + vj.reqPuissance() + " ; " + vj.reqMasse() + " ; "
                   + vj.reqCarburantMax() + " ; " + vj.reqCarburantDepart());
          break;
        }
        }
        bw.newLine();
      }

      bw.write("DescriptionNiveau ; " + descriptionNiveau);
      bw.newLine();

      if (objectif != null) {
        switch (objectif.getClass().getName().toLowerCase()) {
        case "modele.objectifrayon": {
          ObjectifRayon or = (ObjectifRayon) objectif;
          bw.write("ObjectifRayon ; " + or.reqPosRayon().getX() + " ; " + or.reqPosRayon().getY()
                   + " ; " + or.reqRayon());
          break;
        }
        }
        bw.newLine();
      }

      bw.write("PointDepart ; " + pointDepart.getX() + " ; " + pointDepart.getY());
      bw.newLine();

      bw.write("TitreNiveau ; " + titreNiveau);
      bw.newLine();

      bw.write("VitesseDepart ; " + vitesseDepart);
      bw.newLine();

      bw.close();
      fw.close();
    } catch (Exception e) {
    }
  }

  public void asgDescriptionNiveau(String nouvelleDescriptionNiveau) {
    if (nouvelleDescriptionNiveau != null) {
      descriptionNiveau = nouvelleDescriptionNiveau;
    }
  }

  /**
   * Sert � modifier l'objectif.
   * 
   * @param nouvelObjectif Le nouvel objectif.
   */
  public void asgObjectif(Objectif nouvelObjectif) {
    if (nouvelObjectif != null) {
      objectif = nouvelObjectif;
    }
  }

  /**
   * Sert � modifier le point de d�part du vaisseau.
   * 
   * @param nouveauPointDepart Le nouveau point de d�part du vaisseau.
   */
  public void asgPointDepart(Vecteur nouveauPointDepart) {
    if (nouveauPointDepart != null) {
      pointDepart = nouveauPointDepart;
    }
  }

  /**
   * Sert � modifier le titre du niveau.
   * 
   * @param nouveauTitreNiveau Le nouveau titre du niveau.
   */
  public void asgTitreNiveau(String nouveauTitreNiveau) {
    if (nouveauTitreNiveau != null) {
      titreNiveau = nouveauTitreNiveau;
    }
  }

  /**
   * Sert � modifier la vitesse de d�part du vaisseau.
   * 
   * @param nouvelleVitesseDepart La nouvelle vitesse de d�part du vaisseau.
   */
  public void asgVitesseDepart(double nouvelleVitesseDepart) {
    if (nouvelleVitesseDepart >= 0) {
      vitesseDepart = nouvelleVitesseDepart;
    }
  }
}
