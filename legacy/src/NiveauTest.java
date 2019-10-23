import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe de tests pour Niveau.
 * 
 * @author �quipe Bolduc
 */
public class NiveauTest {
  private List<Corps> corps;

  private String descriptionNiveau;

  private Niveau niveau;

  private Objectif objectif;

  private Vecteur pointDepart;

  private String titreNiveau;

  private VaisseauJoueur vaisseau;

  private double vitesseDepart;

  @Before
  public void testNiveau() {
    vaisseau = new VaisseauJoueur(0, 100, 0, 0);

    corps = new ArrayList<>();
    corps.add(vaisseau);
    corps.add(new Planete(1000, 100, 100, 15, 0, Color.WHITE));
    corps.add(new Vaisseau(100, new Vecteur(250, 250), new Vecteur(10, 0)));

    descriptionNiveau = "Niveau tr�s difficile";
    objectif = new ObjectifRayon(new Vecteur(1000, 1000), 20);
    pointDepart = new Vecteur(10, 10);
    titreNiveau = "Niveau 1";
    vitesseDepart = 10;

    niveau = new Niveau(corps,
                        descriptionNiveau,
                        objectif,
                        pointDepart,
                        titreNiveau,
                        vitesseDepart);
  }

  @Test
  public void testAjouterCorps() {
    assertEquals(niveau.reqCorps().size(), 3);
    niveau.ajouterCorps(new Planete(0, 0, 0, 0, 0, Color.WHITE));
    assertEquals(niveau.reqCorps().size(), 4);
    niveau.ajouterCorps(null);
    assertEquals(niveau.reqCorps().size(), 4);
  }

  @Test
  public void testChargerNiveau() {
    File f = new File("/IOTest.txt");
    niveau.sauvegarderNiveau(f);
    niveau.ajouterCorps(new Planete(10, 10, 10, 10, 0, Color.WHITE));
    // assertEquals(niveau.getCorps().size() ==
    // Niveau.chargerNiveau(f).getCorps().size(), false);
    niveau.sauvegarderNiveau(f);
    // assertEquals(Niveau.chargerNiveau(null) == null, true);
  }

  @Test
  public void testGetCorps() {
    assertEquals(niveau.reqCorps(), corps);
  }

  @Test
  public void testGetDescriptionNiveauNiveau() {
    assertEquals(niveau.reqDescriptionNiveau(), descriptionNiveau);
  }

  @Test
  public void testGetObjectif() {
    assertEquals(niveau.reqObjectif(), objectif);
  }

  @Test
  public void testGetPointDepart() {
    assertEquals(niveau.reqPointDepart(), pointDepart);
  }

  @Test
  public void testGetTitreNiveau() {
    assertEquals(niveau.reqTitreNiveau(), titreNiveau);
  }

  @Test
  public void testGetVitesseDepart() {
    assertEquals(niveau.reqVitesseDepart() == vitesseDepart, true);
  }

  @Test
  public void testSauvegarderNiveau() {
    File f = new File("/IOTest.txt");
    niveau.sauvegarderNiveau(f);
    niveau.ajouterCorps(new Planete(10, 10, 10, 10, 0, Color.WHITE));
    // assertEquals(niveau.getCorps().size() ==
    // Niveau.chargerNiveau(f).getCorps().size() + 1, true);
    niveau.sauvegarderNiveau(null);
    niveau.ajouterCorps(new Planete(10, 450, 450, 10, 0, Color.WHITE));
    // assertEquals(niveau.getCorps().size() ==
    // Niveau.chargerNiveau(f).getCorps().size() + 1, false);
  }

  @Test
  public void testSetDescriptionNiveau() {
    assertEquals(niveau.reqDescriptionNiveau(), descriptionNiveau);
    niveau.asgDescriptionNiveau("");
    assertNotEquals(niveau.reqDescriptionNiveau(), descriptionNiveau);
    niveau.asgDescriptionNiveau(null);
    assertNotEquals(niveau.reqDescriptionNiveau(), null);
  }

  @Test
  public void testSetObjectif() {
    assertEquals(niveau.reqObjectif(), objectif);
    niveau.asgObjectif(new ObjectifRayon(new Vecteur(1500, 1500), 20));
    assertNotEquals(niveau.reqObjectif(), objectif);
    niveau.asgObjectif(null);
    assertNotEquals(niveau.reqObjectif(), null);
  }

  @Test
  public void testSetPointDepart() {
    assertEquals(niveau.reqPointDepart(), pointDepart);
    niveau.asgPointDepart(new Vecteur(0, 0));
    assertNotEquals(niveau.reqPointDepart(), pointDepart);
    niveau.asgPointDepart(null);
    assertNotEquals(niveau.reqPointDepart(), null);
  }

  @Test
  public void testSetTitreNiveau() {
    assertEquals(niveau.reqTitreNiveau(), titreNiveau);
    niveau.asgTitreNiveau("");
    assertNotEquals(niveau.reqTitreNiveau(), titreNiveau);
    niveau.asgTitreNiveau(null);
    assertNotEquals(niveau.reqTitreNiveau(), null);
  }

  @Test
  public void testSetVitesseDepart() {
    assertEquals(niveau.reqVitesseDepart() == vitesseDepart, true);
    niveau.asgVitesseDepart(0);
    assertNotEquals(niveau.reqVitesseDepart(), vitesseDepart);
    niveau.asgVitesseDepart(-1);
    assertNotEquals(niveau.reqVitesseDepart(), null);
  }

  @Test
  public void testSupprimerCorps() {
    assertEquals(niveau.reqCorps().size() == 3, true);
    niveau.supprimerCorps(niveau.reqCorps().get(0));
    assertEquals(niveau.reqCorps().size() == 2, true);
    niveau.supprimerCorps(new VaisseauJoueur(0, 0, 0, 0));
    assertEquals(niveau.reqCorps().size() == 2, true);
  }
}
