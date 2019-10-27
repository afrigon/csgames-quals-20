import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import javafx.scene.paint.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test pour ObjetSpatial.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class ObjetSpatialTest {
  // Incertitude tol�r�
  private static final double d = 0.001;
  ObjetSpatial oS1, oS2, oS3, oS4, oS5, oS6, oS7, oS8;

  @Before
  public void beforeObjetSpatial() {
    oS1 = new Planete(0, null, 0, 0, Color.WHITE);
    oS2 = new Planete(1, new Vecteur(), 0, 0, Color.WHITE);
    oS3 = new Planete(60, 50, 40, 0, 0, Color.WHITE);
    oS4 = new Planete(60, new Vecteur(50, 40), 0, 0, Color.WHITE);
    oS5 = new Planete(-1, 0, 0, 0, 0, Color.WHITE);
    oS6 = new Planete(500000, 300, 300, 0, 0, Color.WHITE);
    // oS7 = new Vaisseau(1, new Vecteur(), 1, 1, new Vecteur(), new
    // Vecteur(
    // 40, 40));
    // oS8 = new Vaisseau(1, new Vecteur(), 1, 1, null, null);
  }

  @Test
  public void testGetMasse() {
    assertEquals(ObjetSpatial.MASSE_DEFAUT, oS1.reqMasse(), d);
    assertTrue(oS1.reqMasse() == ObjetSpatial.MASSE_DEFAUT);
    assertTrue(oS2.reqMasse() == ObjetSpatial.MASSE_DEFAUT);
    assertTrue(oS3.reqMasse() == 60);
    assertTrue(oS4.reqMasse() == 60);
    assertTrue(oS5.reqMasse() == ObjetSpatial.MASSE_DEFAUT);
    assertTrue(oS6.reqMasse() == 500000);
  }

  @Test
  public void testSetMasse() {
    oS6.asgMasse(500001);
    assertTrue(oS6.reqMasse() == 500001);
    oS6.asgMasse(40);
    assertTrue(oS6.reqMasse() == 40);
    oS6.asgMasse(-18);
    assertTrue(oS6.reqMasse() == ObjetSpatial.MASSE_DEFAUT);
    oS6.asgMasse(0);
    assertTrue(oS6.reqMasse() == ObjetSpatial.MASSE_DEFAUT);

  }

  @Test
  public void testGetPositionX() {
    assertTrue(oS1.reqPositionX() == 0);
    assertTrue(oS2.reqPositionX() == 0);
    assertTrue(oS3.reqPositionX() == 50);
    assertTrue(oS4.reqPositionX() == 50);
    assertTrue(oS5.reqPositionX() == 0);
    assertTrue(oS6.reqPositionX() == 300);

  }

  @Test
  public void testGetPositionY() {
    assertTrue(oS1.reqPositionY() == 0);
    assertTrue(oS2.reqPositionY() == 0);
    assertTrue(oS3.reqPositionY() == 40);
    assertTrue(oS4.reqPositionY() == 40);
    assertTrue(oS5.reqPositionY() == 0);
    assertTrue(oS6.reqPositionY() == 300);
  }

  @Test
  public void testSetPositionX() {
    oS1.asgPositionX(10);
    assertTrue(oS1.reqPositionX() == 10);
    oS2.asgPositionX(-10);
    assertTrue(oS2.reqPositionX() == -10);
    oS3.asgPositionX(0);
    assertTrue(oS3.reqPositionX() == 0);
    oS4.asgPositionX(40);
    assertTrue(oS4.reqPositionX() == 40);
  }

  @Test
  public void testSetPositionY() {
    oS1.asgPositionY(10);
    assertTrue(oS1.reqPositionY() == 10);
    oS2.asgPositionY(-10);
    assertTrue(oS2.reqPositionY() == -10);
    oS3.asgPositionY(0);
    assertTrue(oS3.reqPositionY() == 0);
    oS4.asgPositionY(40);
    assertTrue(oS4.reqPositionY() == 40);
  }

  @Test
  public void testGetPositionXProperty() {
    assertTrue(oS1.reqPositionXProperty().get() == 0);
    assertTrue(oS2.reqPositionXProperty().get() == 0);
    assertTrue(oS3.reqPositionXProperty().get() == 50);
    assertTrue(oS4.reqPositionXProperty().get() == 50);
    assertTrue(oS5.reqPositionXProperty().get() == 0);
    assertTrue(oS6.reqPositionXProperty().get() == 300);
  }

  @Test
  public void testGetPositionYProperty() {
    assertTrue(oS1.reqPositionYProperty().get() == 0);
    assertTrue(oS2.reqPositionYProperty().get() == 0);
    assertTrue(oS3.reqPositionYProperty().get() == 40);
    assertTrue(oS4.reqPositionYProperty().get() == 40);
    assertTrue(oS5.reqPositionYProperty().get() == 0);
    assertTrue(oS6.reqPositionYProperty().get() == 300);
  }

  @Test
  public void testGetPosition() {
    assertTrue(oS1.reqPosition().getX() == 0 && oS1.reqPosition().getY() == 0);
    assertTrue(oS2.reqPosition().getX() == 0 && oS2.reqPosition().getY() == 0);
    assertTrue(oS3.reqPosition().getX() == 50 && oS3.reqPosition().getY() == 40);
    assertTrue(oS4.reqPosition().getX() == 50 && oS4.reqPosition().getY() == 40);
    assertTrue(oS5.reqPosition().getX() == 0 && oS5.reqPosition().getY() == 0);
    assertTrue(oS6.reqPosition().getX() == 300 && oS6.reqPosition().getY() == 300);
  }

  @Test
  public void testSetPosition() {
    oS1.asgPosition(new Vecteur(10, 50));
    assertTrue(oS1.reqPositionX() == 10);
    assertTrue(oS1.reqPositionY() == 50);
    oS2.asgPosition(new Vecteur(-10, -7));
    assertTrue(oS2.reqPositionX() == -10);
    assertTrue(oS2.reqPositionY() == -7);
    oS3.asgPosition(new Vecteur(0, 0));
    assertTrue(oS3.reqPositionY() == 0);
    assertTrue(oS3.reqPositionX() == 0);
    oS4.asgPosition(null);
    assertTrue(oS4.reqPositionY() == 0);
    assertTrue(oS4.reqPositionX() == 0);

  }

  @Test
  public void testIsStatique() {
    assertTrue(oS1.estStatique());

  }

  @Test
  public void testSetStatique() {
    oS1.asgStatique(false);
    assertFalse(oS1.estStatique());

  }

  @Test
  public void testGetVitesse() {
    oS1.asgVitesse(new Vecteur(60, 60));
    assertTrue(oS1.reqVitesse().getX() == 0 && oS1.reqVitesse().getY() == 0);

  }

  @Test
  public void testSetVitesse() {
    oS1.asgVitesse(new Vecteur(60, 60));
    oS1.asgStatique(false);
    assertTrue(oS1.reqVitesse().getX() == 60 && oS1.reqVitesse().getY() == 60);
    oS2.asgVitesse(null);
    assertEquals(new Vecteur().getNorme(), oS2.reqVitesse().getNorme(), d);
    oS3.asgVitesse(new Vecteur(1, 0), false);
    assertEquals(oS3.reqVitesse().getNorme() == 1, false);

  }

  @Test
  public void testSetPositionXdoubleboolean() {
    oS1.qsgPositionX(-42.6, true);
    assertEquals(-42.6, oS1.reqPositionX(), d);
    oS1.qsgPositionX(42.6, false);
    assertEquals(42.6, oS1.reqPositionX(), d);
    oS1.reset();
    assertEquals(-42.6, oS1.reqPositionX(), d);
    oS1.qsgPositionX(0, true);
    assertEquals(0, oS1.reqPositionX(), d);
    oS1.qsgPositionX(500, false);
    assertEquals(500, oS1.reqPositionX(), d);
    oS1.reset();
    assertEquals(0, oS1.reqPositionX(), d);
  }

  @Test
  public void testSetPositionYdoubleboolean() {
    oS1.asgPositionY(-42.6, true);
    assertEquals(-42.6, oS1.reqPositionY(), d);
    oS1.asgPositionY(28);
    assertEquals(28, oS1.reqPositionY(), d);
    oS1.reset();
    assertEquals(-42.6, oS1.reqPositionY(), d);
    oS1.asgPositionY(0, true);
    assertEquals(0, oS1.reqPositionY(), d);
    oS1.asgPositionY(500, false);
    assertEquals(500, oS1.reqPositionY(), d);
    oS1.reset();
    assertEquals(0, oS1.reqPositionY(), d);
  }

  @Test
  public void testSetPositionVecteurboolean() {
    oS1.asgPosition(new Vecteur(-42.6, -42.6), true);
    assertEquals(-42.6, oS1.reqPositionY(), d);
    assertEquals(-42.6, oS1.reqPositionX(), d);
    oS1.asgPosition(new Vecteur(42.6, 42.6), false);
    assertEquals(42.6, oS1.reqPositionY(), d);
    assertEquals(42.6, oS1.reqPositionX(), d);
    oS1.reset();
    assertEquals(-42.6, oS1.reqPositionY(), d);
    assertEquals(-42.6, oS1.reqPositionX(), d);
    oS1.asgPosition(new Vecteur(100, 100), true);
    assertEquals(100, oS1.reqPositionY(), d);
    assertEquals(100, oS1.reqPositionX(), d);
    oS1.asgPosition(new Vecteur(500, 500), false);
    assertEquals(500, oS1.reqPositionY(), d);
    assertEquals(500, oS1.reqPositionX(), d);
    oS1.reset();
    assertEquals(100, oS1.reqPositionY(), d);
    assertEquals(100, oS1.reqPositionX(), d);
    oS1.asgPosition(null, true);
    oS1.reset();
    assertEquals(0, oS1.reqPositionY(), d);
    assertEquals(0, oS1.reqPositionX(), d);

  }

  @Test
  public void testReset() {

  }

}
