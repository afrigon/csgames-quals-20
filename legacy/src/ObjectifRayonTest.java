import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test pour ObjectifRayon.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class ObjectifRayonTest {
  private Vaisseau vaisseau;
  private ObjectifRayon obj;
  private Vecteur posRayon;
  private double rayon;

  @Before
  public void testObjectifRayon() {
    posRayon = new Vecteur(100, 100);
    rayon = 10;
    vaisseau = new Vaisseau(1000, 0, 0, new Vecteur(0, 0));
    obj = new ObjectifRayon(posRayon, rayon);
    obj.asgVaisseau(vaisseau);
  }

  @Test
  public void testGetDescription() {
    assertNotEquals(0, obj.reqDescription().length());
  }

  @Test
  public void testGetPosRayon() {
    assertEquals(posRayon, obj.reqPosRayon());
  }

  @Test
  public void testGetRayon() {
    assertEquals(rayon == obj.reqRayon(), true);
  }

  @Test
  public void testGetVaisseau() {
    assertEquals(vaisseau == obj.reqVaisseau(), true);
  }

  @Test
  public void testSetPosRayon() {
    assertEquals(posRayon, obj.reqPosRayon());
    obj.asgPosRayon(new Vecteur(10, 10));
    assertNotEquals(posRayon, obj.reqPosRayon());
    obj.asgPosRayon(null);
    assertNotEquals(null, obj.reqPosRayon());
  }

  @Test
  public void testSetRayon() {
    assertEquals(rayon == obj.reqRayon(), true);
    obj.asgRayon(100);
    assertNotEquals(rayon == obj.reqRayon(), true);
    obj.asgRayon(-1);
    assertNotEquals(-1 == obj.reqRayon(), true);
  }

  @Test
  public void testSetVaisseau() {
    assertEquals(vaisseau == obj.reqVaisseau(), true);
    obj.asgVaisseau(new Vaisseau(6e12, 10, 10, new Vecteur(10, 10)));
    assertNotEquals(vaisseau == obj.reqVaisseau(), true);
    obj.asgVaisseau(null);
    assertNotEquals(null == obj.reqVaisseau(), true);
  }

  @Test
  public void testVerifierObjectif() {
    assertEquals(false, obj.verifierObjectif());

    vaisseau.asgPosition(new Vecteur(100, 100));
    assertEquals(true, obj.verifierObjectif());

    vaisseau.asgPosition(new Vecteur(95, 95));
    assertEquals(true, obj.verifierObjectif());
  }
}
