import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test pour la cam�ra.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class CameraTest {
  Camera c1, c2;

  @Before
  public void testCamera() {
    c1 = new Camera();
    c2 = new Camera(100, 100);
  }

  @Test
  public void testSetGrandeurs() {
    c1.setGrandeurs(76, 76);
    c2.setGrandeurs(150, 150);
  }

  @Test
  public void testDeplacer() {
    c2.deplacer(50, 50);
    c2.update(1.0);
    assertEquals(0, c2.getTranslation().getX(), 0.00001);
    assertEquals(0, c2.getTranslation().getY(), 0.00001);
  }

  @Test
  public void testGetDeplacement() {
    c2.deplacer(50, 50);
    assertEquals(50, c2.getDeplacement().getX(), 0.00001);
    assertEquals(50, c2.getDeplacement().getY(), 0.00001);

    assertEquals(0, c1.getDeplacement().getX(), 0.00001);
    assertEquals(0, c1.getDeplacement().getY(), 0.00001);
  }

  @Test
  public void testZoomer() {
    c1.zoomer(2.0);
    c1.update(10.0);
    assertEquals(2.0, c1.getFacteur(), 0.0001);

    c1.zoomer(0.0);
    c1.update(10.0);
    assertEquals(2.0, c1.getFacteur(), 0.0001);
  }

  @Test
  public void testLocalToGlobal() {
    c2.deplacer(0, 0);
    Vecteur v = c2.localToGlobal(new Vecteur(50, 50));
    assertEquals(0, v.getX(), 0.00001);
    assertEquals(0, v.getY(), 0.00001);

    c2.zoomer(2.0);
    v = c2.localToGlobal(new Vecteur(50, 50));
    assertEquals(0.0, v.getX(), 0.00001);
    assertEquals(0.0, v.getY(), 0.00001);
  }

  @Test
  public void testGetTranslation() {
    c2.deplacer(50, 50);
    c2.update(1.0);
    Vecteur v = c2.getTranslation();
    assertEquals(0.0, v.getX(), 0.00001);
    assertEquals(0.0, v.getY(), 0.00001);
  }

  @Test
  public void testGetFacteur() {
    assertEquals(1.0, c1.getFacteur(), 0.0001);

    c2.zoomer(2.0);
    c2.update(10.0);
    assertEquals(2.0, c2.getFacteur(), 0.0001);
  }

  @Test
  public void testUpdate() {
    c2.zoomer(2.0);
    c2.update(0.001);
    assertTrue(c2.getFacteur() > 1.0);
  }
}
