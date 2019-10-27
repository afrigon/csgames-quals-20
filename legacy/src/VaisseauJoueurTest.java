import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test de VaisseauJoueur.
 * 
 * @author EquBolduc
 */
public class VaisseauJoueurTest {
  VaisseauJoueur v1, v2, v3, v4;

  @Before
  public void testVaisseauJoueur() {
    v1 = new VaisseauJoueur(0, 0, 100, 100);
    assertTrue(v1.reqPuissance() == VaisseauJoueur.PUISSANCE_DEFAUT);
    assertTrue(v1.reqCarburantMax() == 100);
    assertTrue(v1.reqCarburantDepart() == 100);
    assertTrue(v1.reqMasse() == Vaisseau.MASSE_DEFAUT);
    assertTrue(v1.reqPositionX() == 0 && v1.reqPositionY() == 0);
    assertTrue(v1.reqVitesse().getX() == 1 && v1.reqVitesse().getY() == 1);
    v2 = new VaisseauJoueur(-1, -1, -1, -1);
    assertTrue(v2.reqPuissance() == VaisseauJoueur.PUISSANCE_DEFAUT);
    assertTrue(v2.reqCarburantMax() == VaisseauJoueur.CARBURANT_DEFAUT);
    assertTrue(v2.reqCarburantDepart() == 0);
    assertTrue(v2.reqMasse() == Vaisseau.MASSE_DEFAUT);
    assertTrue(v2.reqPositionX() == 0 && v2.reqPositionY() == 0);
    v3 = new VaisseauJoueur(-1, -1, 100, 100);
    assertTrue(v3.reqPuissance() == VaisseauJoueur.PUISSANCE_DEFAUT);
    assertTrue(v3.reqCarburantMax() == 100);
    assertTrue(v3.reqCarburantDepart() == 100);
    assertTrue(v3.reqMasse() == Vaisseau.MASSE_DEFAUT);
    assertTrue(v3.reqPositionX() == 0 && v3.reqPositionY() == 0);
    v4 = new VaisseauJoueur(0, 0, 0, 0);
    assertTrue(v4.reqPuissance() == VaisseauJoueur.PUISSANCE_DEFAUT);
    assertTrue(v4.reqCarburantMax() == VaisseauJoueur.CARBURANT_DEFAUT);
    assertTrue(v4.reqCarburantDepart() == 0);
    assertTrue(v4.reqMasse() == Vaisseau.MASSE_DEFAUT);
    assertTrue(v4.reqPositionX() == 0 && v4.reqPositionY() == 0);
    assertTrue(v4.reqVitesse().getX() == 1 && v4.reqVitesse().getY() == 1);
  }

  @Test
  public void testSetCarburantMax() {
    v1.asgCarburantMax(76.0);
    assertEquals(76.0, v1.reqCarburantMax(), 0.0001);
    v1.asgCarburantMax(-76);
    assertEquals(20.0, v1.reqCarburantMax(), 0.0001);
    v1.asgCarburantMax(0);
    assertEquals(20.0, v1.reqCarburantMax(), 0.0001);
  }

  @Test
  public void testGetDirection() {
    assertEquals(1.0, v1.reqDirection().getNorme(), 0.0001);
    assertEquals(1.0, v2.reqDirection().getNorme(), 0.0001);
    assertEquals(1.0, v3.reqDirection().getNorme(), 0.0001);
    assertEquals(1.0, v4.reqDirection().getNorme(), 0.0001);
  }

  @Test
  public void testSetDirection() {
    v1.asgDirection(new Vecteur(1, 0));
    assertEquals(0.0, v1.reqDirection().getAngle(), 0.0001);
    v2.asgDirection(null);
    assertEquals(Math.PI / 4, v2.reqDirection().getAngle(), 0.0001);
    v3.asgDirection(new Vecteur(0, 1));
    assertEquals(Math.PI / 2, v3.reqDirection().getAngle(), 0.0001);
    v4.asgDirection(new Vecteur(0, 0));
    assertEquals(0.0, v4.reqDirection().getAngle(), 0.0001);
  }

  @Test
  public void testGetCarburantMax() {
    assertEquals(100.0, v1.reqCarburantMax(), 0.0001);
    assertEquals(20.0, v2.reqCarburantMax(), 0.0001);
    assertEquals(100.0, v3.reqCarburantMax(), 0.0001);
    assertEquals(20.0, v4.reqCarburantMax(), 0.0001);
  }

  @Test
  public void testSetCarburantRestant() {
    v1.asgCarburantRestant(76.0);
    assertEquals(76.0, v1.reqCarburantRestant(), 0.0001);
    v1.asgCarburantRestant(-76);
    assertEquals(0.0, v1.reqCarburantRestant(), 0.0001);
    v1.asgCarburantRestant(0);
    assertEquals(0.0, v1.reqCarburantRestant(), 0.0001);
    v1.asgCarburantRestant(80000);
    assertEquals(100.0, v1.reqCarburantRestant(), 0.0001);
  }

  @Test
  public void testGetCarburantRestant() {
    assertEquals(100.0, v1.reqCarburantRestant(), 0.0001);
    assertEquals(0.0, v2.reqCarburantRestant(), 0.0001);
    assertEquals(100.0, v3.reqCarburantRestant(), 0.0001);
    assertEquals(0.0, v4.reqCarburantRestant(), 0.0001);
  }

  @Test
  public void testSetCarburantDepart() {
    v1.reqCarburantDepart(76.0);
    assertEquals(76.0, v1.reqCarburantDepart(), 0.0001);
    v1.reqCarburantDepart(-76);
    assertEquals(0.0, v1.reqCarburantDepart(), 0.0001);
    v1.reqCarburantDepart(8000);
    assertEquals(8000.0, v1.reqCarburantMax(), 0.0001);
    assertEquals(8000.0, v1.reqCarburantDepart(), 0.0001);
    v1.reqCarburantDepart(0);
    assertEquals(0.0, v1.reqCarburantDepart(), 0.0001);
  }

  @Test
  public void testGetCarburantDepart() {
    assertEquals(100.0, v1.reqCarburantDepart(), 0.0001);
    assertEquals(0.0, v2.reqCarburantDepart(), 0.0001);
    assertEquals(100.0, v3.reqCarburantDepart(), 0.0001);
    assertEquals(0.0, v4.reqCarburantDepart(), 0.0001);
  }

  @Test
  public void testCarburantMaxProperty() {
    assertTrue(v1.carburantMaxPropriété() != null);
    assertTrue(v2.carburantMaxPropriété() != null);
    assertTrue(v3.carburantMaxPropriété() != null);
    assertTrue(v4.carburantMaxPropriété() != null);
  }

  @Test
  public void testCarburantRestantProperty() {
    assertTrue(v1.carburantRestantPropriété() != null);
    assertTrue(v2.carburantRestantPropriété() != null);
    assertTrue(v3.carburantRestantPropriété() != null);
    assertTrue(v4.carburantRestantPropriété() != null);
  }

  @Test
  public void testGetPuissance() {
    assertTrue(v1.reqPuissance() == v1.reqPuissance());
    assertTrue(v2.reqPuissance() == v2.reqPuissance());
    assertTrue(v3.reqPuissance() == v3.reqPuissance());
    assertTrue(v4.reqPuissance() == v4.reqPuissance());
  }

  @Test
  public void testSetPuissance() {
    v1.asgPuissance(50);
    assertTrue(v1.reqPuissance() == 50);
    v2.asgPuissance(-1000);
    assertTrue(v2.reqPuissance() == VaisseauJoueur.PUISSANCE_DEFAUT);
    v3.asgPuissance(0);
    assertTrue(v3.reqPuissance() == VaisseauJoueur.PUISSANCE_DEFAUT);
  }

  @Test
  public void testGetSante() {
    assertEquals(1.0, v1.reqSante(), 0.0001);
    assertEquals(1.0, v2.reqSante(), 0.0001);
    assertEquals(1.0, v3.reqSante(), 0.0001);
    assertEquals(1.0, v4.reqSante(), 0.0001);
  }

  @Test
  public void testSetSante() {
    v1.asgSante(0.5);
    assertEquals(0.5, v1.reqSante(), 0.0001);

    v1.asgSante(100);
    assertEquals(0.5, v1.reqSante(), 0.0001);

    v1.asgSante(-100);
    assertEquals(0.5, v1.reqSante(), 0.0001);
  }

  @Test
  public void testGetForceExt() {
    assertEquals(0, v2.reqForceExt().getX(), 0.0001);
    assertEquals(0, v2.reqForceExt().getY(), 0.0001);
    v2.avancer(true);
    v2.asgCarburantMax(9e9);
    v2.asgCarburantRestant(9e9);
    assertEquals(200000, v2.reqForceExt().getNorme(), 0.0001);
    v2.avancer(false);
    assertEquals(0, v2.reqForceExt().getX(), 0.0001);
    assertEquals(0, v2.reqForceExt().getY(), 0.0001);
    v2.asgCarburantRestant(0);
    v2.avancer(true);
    assertEquals(0, v2.reqForceExt().getNorme(), 0.0001);
  }

  @Test
  public void testUpdate() {
    v1.tournerDroite(true);
    v1.tournerGauche(true);
    v1.avancer(true);
    v1.asgCarburantMax(100);
    v1.asgCarburantRestant(100);
    v1.miseAJourPhysique(1.0);
    assertEquals(99.0, v1.reqCarburantRestant(), 0.0001);
    v1.avancer(false);
    v1.miseAJourPhysique(1.0);
    assertEquals(99.0, v1.reqCarburantRestant(), 0.0001);
  }

  @Test
  public void testOnCollision() {
    v1.surCollision(v2);
    assertEquals(0.5, v1.reqSante(), 0.0001);
    v1.asgSante(1.0);
    v4.asgMasse(v1.reqMasse() * 2 + 1);
    v1.surCollision(v4);
    assertEquals(0.0, v1.reqSante(), 0.0001);
  }

  @Test
  public void testSetAngle() {
    v1.asgAngle(0.0);
    assertEquals(0.0, v1.reqDirection().getAngle(), 0.0001);
  }

  @Test
  public void testReset() {
    v1.reset();
    assertEquals(100.0, v1.reqCarburantRestant(), 0.0001);
    assertEquals(1.0, v1.reqSante(), 0.0001);
    assertEquals(0.0, v1.reqPosition().getX(), 0.0001);
    assertEquals(0.0, v1.reqPosition().getY(), 0.0001);
    assertTrue(v1.reqVitesse().getNorme() == Math.sqrt(2));
  }
}
