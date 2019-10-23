import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe de tests pour Tete.
 * 
 * @author �quipe Bolduc
 */
public class TeteTest {
  double d = 0.001;

  Tete t1, t2, t3, t4;

  @Before
  public void testTete() {
    t1 = new Tete(1, 0, new Vecteur(), new Vecteur());
    assertEquals(Planete.MASSE_DEFAUT, t1.reqMasse(), d);
    assertEquals(0, t1.reqPositionX(), d);
    assertEquals(0, t1.reqPositionY(), d);
    t2 = new Tete(1, 100, new Vecteur(), new Vecteur());
    assertEquals(1, t2.reqMasse(), d);
    assertEquals(0, t2.reqPositionX(), d);
    assertEquals(0, t2.reqPositionY(), d);
    t3 = new Tete(10, 30, new Vecteur(), new Vecteur());
    assertEquals(10, t3.reqMasse(), d);
    assertEquals(0, t3.reqPositionX(), d);
    assertEquals(0, t3.reqPositionY(), d);
    t4 = new Tete(60, 100, new Vecteur(50, 40), new Vecteur());
    assertEquals(60, t4.reqMasse(), d);
    assertEquals(50, t4.reqPositionX(), d);
    assertEquals(40, t4.reqPositionY(), d);
  }

  @Test
  public void testSetRayon() {
    t1.setRayon(10);
    assertEquals(10, t1.reqRayon(), d);
    t2.setRayon(100);
    assertEquals(100, t2.reqRayon(), d);
    t3.setRayon(20);
    assertEquals(20, t3.reqRayon(), d);
    t4.setRayon(-100);
    assertEquals(Planete.RAYON_DEFAUT, t4.reqRayon(), d);
  }

  @Test
  public void testGetRayon() {
    assertEquals(0, t1.reqRayon(), d);
    assertEquals(Tete.RAYON_DEFAUT, t2.reqRayon(), d);
    assertEquals(100, t4.reqRayon(), d);
    assertEquals(30, t3.reqRayon(), d);
  }

  @Test
  public void testSetTexture() {
    t1.setTexture(Tete.Texture.XEHOS);
    assertEquals(t1.getTexture().name(), Tete.Texture.XEHOS.name());
    t2.setTexture(null);
    assertEquals(t2.getTexture().name(), Tete.TEXTURE_DEFAUT.name());
  }

  @Test
  public void testGetTexture() {
    t3.setTexture(Tete.Texture.LOTUS);
    assertEquals(t3.getTexture().name(), Tete.Texture.LOTUS.name());
    t4.setTexture(null);
    assertEquals(t4.getTexture().name(), Tete.TEXTURE_DEFAUT.name());
  }

  @Test
  public void testGetForceExt() {
    assertEquals(0, t1.reqForceExt().getNorme(), d);
  }

  @Test
  public void testTexture() {
    // public static Texture getTexture(String tex)
    assertEquals(Tete.Texture.CREPSER, Tete.Texture.getTexture("crepser"));
    assertEquals(Tete.Texture.XEHOS, Tete.Texture.getTexture("xehos"));
    assertEquals(Tete.Texture.DIIGS3B, Tete.Texture.getTexture("diigs3b"));
    assertEquals(Tete.Texture.LOTUS, Tete.Texture.getTexture("lotus"));
    assertEquals(Tete.TEXTURE_DEFAUT, Tete.Texture.getTexture("oiasdfkhaskjfh"));

    // public String getTexture()
    assertEquals("/res/xehos.png", Tete.Texture.XEHOS.getTexture());

  }
}
