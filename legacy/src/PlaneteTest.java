import static org.junit.Assert.*;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test pour Planete.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class PlaneteTest {
  double d = 0.001;

  Planete p1, p2, p3, p4, p5, p6;

  @Before
  public void beforePlaneteDoubleVecteur() {
    p1 = new Planete(0, null, 0, 0, Color.WHITE);
    assertEquals(Planete.MASSE_DEFAUT, p1.reqMasse(), d);
    assertEquals(0, p1.reqPositionX(), d);
    assertEquals(0, p1.reqPositionY(), d);
    p2 = new Planete(1, new Vecteur(), -1, 0, Color.WHITE);
    assertEquals(1, p2.reqMasse(), d);
    assertEquals(0, p2.reqPositionX(), d);
    assertEquals(0, p2.reqPositionY(), d);
    p4 = new Planete(60, new Vecteur(50, 40), 100, 0, Color.WHITE);
    assertEquals(60, p4.reqMasse(), d);
    assertEquals(50, p4.reqPositionX(), d);
    assertEquals(40, p4.reqPositionY(), d);
  }

  @Before
  public void beforePlaneteDoubleDoubleDouble() {
    p3 = new Planete(60, 50, 40, 30, 0, Color.WHITE);
    assertEquals(60, p3.reqMasse(), d);
    assertEquals(50, p3.reqPositionX(), d);
    assertEquals(40, p3.reqPositionY(), d);
    p5 = new Planete(-1, 0, 0, 0, 0, Color.WHITE);
    assertEquals(Planete.MASSE_DEFAUT, p5.reqMasse(), d);
    assertEquals(0, p5.reqPositionX(), d);
    assertEquals(0, p5.reqPositionY(), d);
    p6 = new Planete(500000, 300, 300, 100, 0, Color.WHITE);
    assertEquals(500000, p6.reqMasse(), d);
    assertEquals(300, p6.reqPositionX(), d);
    assertEquals(300, p6.reqPositionY(), d);
  }

  @Test
  public void testSetRayon() {
    p1.asgRayon(10);
    assertEquals(10, p1.reqRayon(), d);
    p2.asgRayon(100);
    assertEquals(100, p2.reqRayon(), d);
    p3.asgRayon(20);
    assertEquals(20, p3.reqRayon(), d);
    p4.asgRayon(-100);
    assertEquals(Planete.RAYON_DEFAUT, p4.reqRayon(), d);
    p5.asgRayon(-40);
    assertEquals(Planete.RAYON_DEFAUT, p5.reqRayon(), d);
    p6.asgRayon(0);
    assertEquals(0, p6.reqRayon(), d);
  }

  @Test
  public void testGetRayon() {
    assertEquals(0, p1.reqRayon(), d);
    assertEquals(Planete.RAYON_DEFAUT, p2.reqRayon(), d);
    assertEquals(100, p4.reqRayon(), d);
    assertEquals(30, p3.reqRayon(), d);
    assertEquals(0, p5.reqRayon(), d);
    assertEquals(100, p6.reqRayon(), d);
  }

  @Test
  public void testSetTexture() {
    p1.asgTexture(Planete.Texture.ROUGE);
    assertEquals(p1.reqTexture().name(), Planete.Texture.ROUGE.name());
    p2.asgTexture(null);
    assertEquals(p2.reqTexture().name(), Planete.TEXTURE_DEFAUT.name());
  }

  @Test
  public void testGetTexture() {
    p3.asgTexture(Planete.Texture.ROUGE);
    assertEquals(p3.reqTexture().name(), Planete.Texture.ROUGE.name());
    p4.asgTexture(null);
    assertEquals(p4.reqTexture().name(), Planete.TEXTURE_DEFAUT.name());
  }

  @Test
  public void testGetRayonAtmosphere() {
    assertEquals(0, p1.reqRayonAtmosphere(), d);
  }

  @Test
  public void testSetRayonAtmosphere() {
    p1.asgRayonAtmosphere(5);
    assertEquals(5, p1.reqRayonAtmosphere(), d);
    p2.asgRayonAtmosphere(0);
    assertEquals(0, p2.reqRayonAtmosphere(), d);
    p3.asgRayonAtmosphere(-5);
    assertEquals(Planete.RAYON_ATMOSPHERE_DEFAUT, p3.reqRayonAtmosphere(), d);
  }

  @Test
  public void testGetForceExt() {
    assertEquals(0, p1.reqForceExt().getNorme(), d);
  }

  @Test
  public void testTexture() {
    // public static Texture getTexture(String tex)
    assertEquals(Planete.Texture.BLEUE, Planete.Texture.reqTexture("bleue"));
    assertEquals(Planete.Texture.JAUNE, Planete.Texture.reqTexture("jaune"));
    assertEquals(Planete.Texture.MAGENTA, Planete.Texture.reqTexture("MAGENTA"));
    assertEquals(Planete.Texture.ORANGE, Planete.Texture.reqTexture("orange"));
    assertEquals(Planete.Texture.ROUGE, Planete.Texture.reqTexture("rouge"));
    assertEquals(Planete.Texture.VERTE, Planete.Texture.reqTexture("verte"));
    assertEquals(Planete.TEXTURE_DEFAUT, Planete.Texture.reqTexture("oiasdfkhaskjfh"));

    // public String getTexture()
    assertEquals("/res/planeteBleue.png", Planete.Texture.BLEUE.reqTexture());

  }

}
