import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de tests pour Vecteur.
 * 
 * @author EquBolduc
 */
public class VecteurTest {
  Vecteur v1, v2;

  @Before
  public void testVecteur() {
    v1 = new Vecteur();
    v2 = new Vecteur(5, 5);
  }

  @Test
  public void testSetX() {
    v1.setX(2);
    assertEquals(2, v1.getX(), 0.0001);

    v2.setX(-2);
    assertEquals(-2, v2.getX(), 0.0001);
  }

  @Test
  public void testSetY() {
    v1.setY(2);
    assertEquals(2, v1.getY(), 0.0001);

    v2.setY(-2);
    assertEquals(-2, v2.getY(), 0.0001);
  }

  @Test
  public void testGetX() {
    assertEquals(0, v1.getX(), 0.0001);
    assertEquals(5, v2.getX(), 0.0001);
  }

  @Test
  public void testGetY() {
    assertEquals(0, v1.getY(), 0.0001);
    assertEquals(5, v2.getY(), 0.0001);
  }

  @Test
  public void testGetAngle() {
    assertEquals(0, v1.getAngle(), 0.0001);
    assertEquals(Math.PI / 4, v2.getAngle(), 0.0001);

    Vecteur aPI2 = new Vecteur(0, 1);
    assertEquals(Math.PI / 2, aPI2.getAngle(), 0.0001);

    Vecteur a3PI2 = new Vecteur(0, -1);
    assertEquals(3 * Math.PI / 2, a3PI2.getAngle(), 0.0001);

    Vecteur aPI4 = new Vecteur(1, 1);
    assertEquals(Math.PI / 4, aPI4.getAngle(), 0.0001);

    Vecteur a3PI4 = new Vecteur(-1, 1);
    assertEquals(3 * Math.PI / 4, a3PI4.getAngle(), 0.0001);

    Vecteur a5PI4 = new Vecteur(-1, -1);
    assertEquals(5 * Math.PI / 4, a5PI4.getAngle(), 0.0001);

    Vecteur a7PI4 = new Vecteur(1, -1);
    assertEquals(7 * Math.PI / 4, a7PI4.getAngle(), 0.0001);
  }

  @Test
  public void testGetNorme() {
    assertEquals(0, v1.getNorme(), 0.0001);
    assertEquals(7.0711, v2.getNorme(), 0.0001);
  }

  @Test
  public void testAdditionner() {
    Vecteur resultat = v1.additionner(v2);
    assertEquals(5, resultat.getX(), 0.0001);
    assertEquals(5, resultat.getY(), 0.0001);
  }

  @Test
  public void testSoustraire() {
    Vecteur resultat = v1.soustraire(v2);
    assertEquals(-5, resultat.getX(), 0.0001);
    assertEquals(-5, resultat.getY(), 0.0001);
  }

  @Test
  public void testMultiplication() {
    Vecteur resultat = v1.multiplication(5);
    assertEquals(0, resultat.getX(), 0.0001);
    assertEquals(0, resultat.getY(), 0.0001);

    resultat = v2.multiplication(5);
    assertEquals(25, resultat.getX(), 0.0001);
    assertEquals(25, resultat.getY(), 0.0001);
  }

  @Test
  public void testProduitScalaire() {
    assertEquals(0.0, v1.multiplication(v2), 0.0001);
  }

  @Test
  public void testNormaliser() {
    Vecteur resultat = v1.normaliser();
    assertEquals(0, resultat.getX(), 0.0001);
    assertEquals(0, resultat.getY(), 0.0001);

    resultat = v2.normaliser();
    assertEquals(0.70711, resultat.getX(), 0.0001);
    assertEquals(0.70711, resultat.getY(), 0.0001);
  }

  @Test
  public void testsetAngle() {
    v1.setAngle(Math.PI / 2);
    assertEquals(0, v1.getX(), 0.0001);
    assertEquals(0, v1.getY(), 0.0001);

    v2.setAngle(3 * Math.PI / 4);
    assertEquals(-5, v2.getX(), 0.0001);
    assertEquals(5, v2.getY(), 0.0001);
  }
}
