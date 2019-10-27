import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test pour Vaisseau.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class VaisseauTest {
  private static final double d = 0.001;
  Vaisseau v1, v2, v3, v4, v5, v6;

  @Before
  public void testVaisseauDoubleVecteurVecteur() {
    v1 = new Vaisseau(0, new Vecteur(), new Vecteur());
    assertTrue(v1.reqMasse() == Vaisseau.MASSE_DEFAUT);
    assertTrue(v1.reqPositionX() == 0 && v1.reqPositionY() == 0);
    assertTrue(v1.reqVitesse().getX() == 0 && v1.reqVitesse().getY() == 0);
    v2 = new Vaisseau(-10, new Vecteur(), new Vecteur());
    assertTrue(v2.reqMasse() == Vaisseau.MASSE_DEFAUT);
    assertTrue(v2.reqPositionX() == 0 && v2.reqPositionY() == 0);
    assertTrue(v2.reqVitesse().getX() == 0 && v2.reqVitesse().getY() == 0);
    v3 = new Vaisseau(100, new Vecteur(150, 125), new Vecteur(10, 10));
    assertTrue(v3.reqMasse() == 100);
    assertTrue(v3.reqPositionX() == 150 && v3.reqPositionY() == 125);
    assertEquals(10, v3.reqVitesse().getX(), d);
    assertEquals(10, v3.reqVitesse().getY(), d);
  }

  @Before
  public void testVaisseauDoubleDoubleDoubleVecteur() {
    v4 = new Vaisseau(0, 0, 0, null);
    assertTrue(v4.reqMasse() == Vaisseau.MASSE_DEFAUT);
    assertTrue(v4.reqPositionX() == 0 && v4.reqPositionY() == 0);
    assertTrue(v4.reqVitesse().getX() == 0 && v4.reqVitesse().getY() == 0);
    v5 = new Vaisseau(-10, -1, -1, new Vecteur());
    assertTrue(v5.reqMasse() == Vaisseau.MASSE_DEFAUT);
    assertTrue(v5.reqPositionX() == -1 && v5.reqPositionY() == -1);
    assertTrue(v5.reqVitesse().getX() == 0 && v5.reqVitesse().getY() == 0);
    v6 = new Vaisseau(100, 150, 125, new Vecteur(10, 10));
    assertTrue(v6.reqMasse() == 100);
    assertTrue(v6.reqPositionX() == 150 && v6.reqPositionY() == 125);
    assertTrue(v6.reqVitesse().getX() == 10 && v6.reqVitesse().getY() == 10);
  }

  @Test
  public void testSetStatique() {
    assertFalse(v1.estStatique());
    assertFalse(v2.estStatique());
    assertFalse(v3.estStatique());
    assertFalse(v4.estStatique());
    assertFalse(v5.estStatique());
    assertFalse(v6.estStatique());
    v1.asgStatique(true);
    assertTrue(v1.estStatique());
  }

  @Test
  public void testGetForceExt() {
    assertEquals(0.0, v1.reqForceExt().getNorme(), 0.0001);
    assertEquals(0.0, v2.reqForceExt().getNorme(), 0.0001);
    assertEquals(0.0, v3.reqForceExt().getNorme(), 0.0001);
    assertEquals(0.0, v4.reqForceExt().getNorme(), 0.0001);
    assertEquals(0.0, v5.reqForceExt().getNorme(), 0.0001);
  }

  @Test
  public void testGetNoeud() {
    // N�cessite un contexte JavaFX.
    /*
     * Node noeud = v1.getNoeud(); assertTrue(noeud instanceof Group);
     * assertTrue(((Group)(noeud)).getChildren().size() != 0);
     * assertEquals(noeud, v1.getNoeud());
     */
  }

  @Test
  public void testMaj() {
    // N�cessite un contexte JavaFX.
  }

  @Test
  public void testGetRayon() {
    assertEquals(20.0, v1.reqRayon(), 0.0001);
    assertEquals(20.0, v2.reqRayon(), 0.0001);
    assertEquals(20.0, v3.reqRayon(), 0.0001);
    assertEquals(20.0, v4.reqRayon(), 0.0001);
    assertEquals(20.0, v5.reqRayon(), 0.0001);
  }

  @Test
  public void testSetVitesse() {
    v1.asgVitesse(new Vecteur(10, 0));
    assertEquals(v1.reqVitesse().getNorme() == 10, true);
    v1.asgVitesse(null);
    assertEquals(v1.reqVitesse().getNorme() == 0, true);
  }

  @Test
  public void testReset() {
    v1.reset();
    assertEquals(0.0, v1.reqPosition().getX(), 0.0001);
    assertEquals(0.0, v1.reqPosition().getY(), 0.0001);
    assertTrue(v1.reqVitesse().getNorme() == 0.0);
  }
}
